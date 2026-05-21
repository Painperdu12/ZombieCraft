package org.survivalcraft.zombie;

import java.io.File;

import org.survivalcraft.zombie.client.ClientProxy;
import org.survivalcraft.zombie.client.events.EventRenderHandler;
import org.survivalcraft.zombie.common.CommonProxy;
import org.survivalcraft.zombie.common.events.EventCommonHandler;
import org.survivalcraft.zombie.common.loot.LootManager;
import org.survivalcraft.zombie.common.loot.LootTable;
import org.survivalcraft.zombie.server.ServerProxy;
import org.survivalcraft.zombie.server.ServerVariables;
import org.survivalcraft.zombie.server.commands.CommandLoot;
import org.survivalcraft.zombie.server.commands.CommandZombiecraft;
import org.survivalcraft.zombie.server.events.EventServerHandler;
import org.survivalcraft.zombie.utils.Logger;
import org.survivalcraft.zombie.utils.SharedConstants;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = SharedConstants.MODID, name = SharedConstants.MOD_NAME, version = SharedConstants.MOD_VERSION)
public class ZombieCraft {

    @Instance(SharedConstants.MODID)
    private static ZombieCraft instance;
    private CommonProxy proxy;

    private final LootManager lootManager;

    public ZombieCraft() {
	this.lootManager = new LootManager();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
	this.proxy = (CommonProxy) (FMLCommonHandler.instance().getSide().isClient() ? new ClientProxy() : new ServerProxy());

	proxy.preInit(event);
	FMLCommonHandler.instance().bus().register(new EventCommonHandler());
	MinecraftForge.EVENT_BUS.register(new EventCommonHandler());

	if(event.getSide().isServer()) {
	    FMLCommonHandler.instance().bus().register(new EventServerHandler());
	    MinecraftForge.EVENT_BUS.register(new EventServerHandler());

	    if(ServerVariables.enableLoot) {
		LootTable.initLootTables();
		this.lootManager.init(event);
	    } else Logger.initInfo("Looting is disabled in Server configuration!");

	} else {
	    FMLCommonHandler.instance().bus().register(new EventRenderHandler());
	    MinecraftForge.EVENT_BUS.register(new EventRenderHandler());
	    Logger.initInfo("Looting is disabled on Client!");
	}
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event) {
	proxy.init(event);
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
	proxy.postInit(event);
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
	if(event.getSide().isServer()) {
	    Logger.initInfo("Registring ZombieCraft Server-side Commands...");
	    ((CommandHandler) event.getServer().getCommandManager()).registerCommand(new CommandZombiecraft());
	    ((CommandHandler) event.getServer().getCommandManager()).registerCommand(new CommandLoot());
	}
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
	if(event.getSide().isServer()) {
	}
    }

    public static ZombieCraft instance() {
	return instance;
    }

    public CommonProxy getProxy() {
	return proxy;
    }

    public LootManager getLootManager() {
	return lootManager;
    }

    public ClientProxy getClientProxy() {
	return (ClientProxy) this.proxy;
    }

    public ServerProxy getServerProxy() {
	return (ServerProxy) this.proxy;
    }

}