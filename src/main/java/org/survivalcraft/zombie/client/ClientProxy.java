package org.survivalcraft.zombie.client;

import org.survivalcraft.zombie.client.events.EventKeyboardHandler;
import org.survivalcraft.zombie.client.models.entity.ModelHumanoid;
import org.survivalcraft.zombie.client.render.block.RenderWaterPump;
import org.survivalcraft.zombie.client.render.entity.RenderHumanoid;
import org.survivalcraft.zombie.common.CommonProxy;
import org.survivalcraft.zombie.common.entities.EntityInfected;
import org.survivalcraft.zombie.common.tiles.TileEntityWaterPump;
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

public class ClientProxy extends CommonProxy {

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		Logger.infoInit("Client-side Initializiation of ZombieCraft.");
		
		this.registerEntityRenderer();
		this.registerKeyBindings();
		this.registerBlockRenderer();
	}
	
	public void registerEntityRenderer() {
		Logger.infoInit("- Registering Entity Renderers...");
		RenderingRegistry.registerEntityRenderingHandler(EntityInfected.class, new RenderHumanoid(new ModelHumanoid(), RenderHumanoid.BIG_SCALE));
	}
	
	public void registerBlockRenderer() {
		Logger.infoInit("- Registering Blocks Renderers...");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWaterPump.class, new RenderWaterPump());
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