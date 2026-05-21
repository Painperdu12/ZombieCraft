package org.survivalcraft.zombie.server.events;

import java.util.ArrayList;
import java.util.List;

import org.survivalcraft.zombie.ZombieCraft;
import org.survivalcraft.zombie.common.EnumWeatherType;
import org.survivalcraft.zombie.common.loot.LootManager;
import org.survivalcraft.zombie.common.loot.LootTable;
import org.survivalcraft.zombie.common.network.NetworkHandler;
import org.survivalcraft.zombie.common.network.messages.MessageS2CLooting;
import org.survivalcraft.zombie.common.network.messages.MessageS2CWeather;
import org.survivalcraft.zombie.server.ServerVariables;
import org.survivalcraft.zombie.utils.ChatHelper;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventServerHandler {

    private int weatherCheckCounter = 0;

    @SubscribeEvent
    public void onPlayerPlaceBlock(BlockEvent.PlaceEvent event) {
	if(event.world.isRemote) return;
	
	EntityPlayerMP player = (EntityPlayerMP) event.player;
	int x = event.x;
	int y = event.y;
	int z = event.z;
	
	//--- TERRAIN ACCESS CONTROL ---
	if(!player.capabilities.isCreativeMode && !ServerVariables.enableBlockPlacing) {
	    event.setCanceled(true);
	    ChatHelper.sendMessage(player, "&cYou can't place blocks on this server!");
	}
    }
    
    @SubscribeEvent
    public void onPlayerBreakBlock(BlockEvent.BreakEvent event) {
	if(event.world.isRemote) return;
	
	EntityPlayerMP player = (EntityPlayerMP) event.getPlayer();
	int x = event.x;
	int y = event.y;
	int z = event.z;
	
	//--- TERRAIN ACCESS CONTROL ---
	if(!player.capabilities.isCreativeMode && !ServerVariables.enableBlockBreaking) {
	    event.setCanceled(true);
	    ChatHelper.sendMessage(player, "&cYou can't break blocks on this server!");
	}
    }
    
    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
	if(!event.world.isRemote) {
	    if(event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return;
	    
	    EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
	    World world = event.world;
	    int x = event.x;
	    int y = event.y;
	    int z = event.z;
	    
	    //--- TERRAIN ACCESS CONTROL ---
	    if(!player.capabilities.isCreativeMode && !ServerVariables.enableBlockUsing) {
		if(world.blockExists(x, y, z)) {
		    Block block = world.getBlock(x, y, z);
		    
		    if(block == Blocks.chest || block == Blocks.furnace || block == Blocks.hopper || block == Blocks.crafting_table || block == Blocks.dropper || block == Blocks.dispenser || block == Blocks.anvil || block == Blocks.ender_chest || block == Blocks.enchanting_table) {
			event.setCanceled(true);
		    }
		}
	    }
	    
	    //--- LOOTING SYSTEM ---
	    if(ServerVariables.enableLoot) {
		LootManager lootManager = ZombieCraft.instance().getLootManager();
		
		if(world.blockExists(x, y, z) && !player.capabilities.isCreativeMode) {
		    Block block = world.getBlock(x, y, z);
		    
		    if(lootManager.isLootable(block, x, y, z)) {
			if(!lootManager.isReloading(x, y, z)) {
			    lootManager.addInReloadingList(x, y, z, System.currentTimeMillis());

			    InventoryBasic lootingInventory = new InventoryBasic("Looting", true, 16);
			    LootTable.generateFor(lootingInventory, block, 8, 16);

			    MessageS2CLooting message = new MessageS2CLooting(lootingInventory);
			    NetworkHandler.sendToPlayer(message, player);
			} else {
			    ChatHelper.sendMessage(player, "&7This " + block.getLocalizedName() + " is empty...");
			}
		    }
		}
	    }
	}
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
	if(event.player instanceof EntityPlayerMP) {
	    EntityPlayerMP player = (EntityPlayerMP) event.player;

	    float normalDensity = ServerVariables.customFogNormalDensity;
	    float rainDensity = ServerVariables.customFogRainDensity;
	    float stormDensity = ServerVariables.customFogStormDensity;
	    float foggyDensity = ServerVariables.customFogFoggyDensity;

	    MessageS2CWeather weatherMessage = new MessageS2CWeather(ServerVariables.weatherTypeIndex, rainDensity, stormDensity, foggyDensity, normalDensity, ServerVariables.enableWeather);
	    NetworkHandler.sendToPlayer(weatherMessage, player);
	}
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
	if(event.phase == TickEvent.Phase.END) {
	    if(ServerVariables.enableWeather) {
		if(this.weatherCheckCounter <= 0) {
		    this.weatherCheckCounter = 20 * 300; // 5 minutes

		    if(Math.random() < 0.1) { // 10% de chances
			if(Math.random() < 0.1d)
			    this.changeWeatherTo(EnumWeatherType.STORM);
			else if(Math.random() < 0.3d)
			    this.changeWeatherTo(EnumWeatherType.FOGGY);
			else if(Math.random() < 0.7d)
			    this.changeWeatherTo(EnumWeatherType.RAIN);
			else if(Math.random() < 0.9d)
			    this.changeWeatherTo(EnumWeatherType.NORMAL);
		    }
		} else
		    this.weatherCheckCounter--;
	    }

	    if(ServerVariables.enableLoot) {
		ZombieCraft.instance().getLootManager().onUpdate(MinecraftServer.getServer().getEntityWorld());
	    }
	}
    }

    private void changeWeatherTo(EnumWeatherType type) {
	Logger.info("Changing weather to " + type.name + " (" + type.index + ").");
	MessageS2CWeather message = new MessageS2CWeather(type.index, ServerVariables.customFogRainDensity, ServerVariables.customFogStormDensity, ServerVariables.customFogFoggyDensity, ServerVariables.customFogNormalDensity, ServerVariables.enableWeather);
	NetworkHandler.sendToAll(message);
    }
}