package org.survivalcraft.zombie.client;

import org.survivalcraft.zombie.client.events.EventKeyboardHandler;
import org.survivalcraft.zombie.client.models.ModelHumanoid;
import org.survivalcraft.zombie.client.models.item.ModelFireAxe;
import org.survivalcraft.zombie.client.render.RenderHumanoid;
import org.survivalcraft.zombie.client.render.block.RenderHazardBarrier;
import org.survivalcraft.zombie.client.render.block.RenderTable;
import org.survivalcraft.zombie.client.render.block.RenderTrashcan;
import org.survivalcraft.zombie.client.render.block.RenderWaterFountain;
import org.survivalcraft.zombie.client.render.block.RenderWaterPump;
import org.survivalcraft.zombie.client.render.block.RenderWoodenChair;
import org.survivalcraft.zombie.client.render.item.RenderItem;
import org.survivalcraft.zombie.common.CommonProxy;
import org.survivalcraft.zombie.common.entities.EntityInfected;
import org.survivalcraft.zombie.common.items.ModItems;
import org.survivalcraft.zombie.common.tiles.props.TileEntityHazardBarrier;
import org.survivalcraft.zombie.common.tiles.props.TileEntityTable;
import org.survivalcraft.zombie.common.tiles.props.TileEntityTrashcan;
import org.survivalcraft.zombie.common.tiles.props.TileEntityWaterFountain;
import org.survivalcraft.zombie.common.tiles.props.TileEntityWaterPump;
import org.survivalcraft.zombie.common.tiles.props.TileEntityWoodenChair;
import org.survivalcraft.zombie.utils.Logger;
import org.survivalcraft.zombie.utils.SharedConstants;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		Logger.infoInit("Client-side Initializiation of ZombieCraft.");
		
		this.registerEntityRenderers();
		this.registerKeyBindings();
		this.registerBlockRenderers();
		this.registerItemRenderers();
	}
	
	public void registerEntityRenderers() {
		Logger.infoInit("- Registering Entity Renderers...");
		
		RenderingRegistry.registerEntityRenderingHandler(EntityInfected.class, new RenderHumanoid(new ModelHumanoid(), RenderHumanoid.BIG_SCALE));
	}
	
	public void registerBlockRenderers() {
		Logger.infoInit("- Registering Blocks Renderers...");
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWaterPump.class, new RenderWaterPump());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWaterFountain.class, new RenderWaterFountain());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHazardBarrier.class, new RenderHazardBarrier());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new RenderTable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWoodenChair.class, new RenderWoodenChair());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrashcan.class, new RenderTrashcan());
	}
	
	public void registerItemRenderers() {
		Logger.infoInit("- Registering Items Renderers...");
		
		MinecraftForgeClient.registerItemRenderer(ModItems.fireAxe, new RenderItem(new ModelFireAxe(), new ResourceLocation(SharedConstants.MODID, "textures/items/models/fireAxe.png")));
	}
	
	public void registerKeyBindings() {
		Logger.infoInit("- Registering KeyBindings...");
		FMLCommonHandler.instance().bus().register(new EventKeyboardHandler());
		
		for(ModKeybindings key : ModKeybindings.values()) ClientRegistry.registerKeyBinding(key.getKeybinding());
	}
	
	public ResourceLocation getResource(String value) {
		return new ResourceLocation(SharedConstants.MODID, value);
	}
	
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}	
}