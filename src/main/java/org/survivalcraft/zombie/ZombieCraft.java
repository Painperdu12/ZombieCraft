package org.survivalcraft.zombie;

import org.survivalcraft.zombie.client.ClientProxy;
import org.survivalcraft.zombie.client.events.EventRenderHandler;
import org.survivalcraft.zombie.common.CommonProxy;
import org.survivalcraft.zombie.common.events.EventCommonHandler;
import org.survivalcraft.zombie.server.ServerProxy;
import org.survivalcraft.zombie.server.commands.CommandZombiecraft;
import org.survivalcraft.zombie.server.events.EventServerHandler;
import org.survivalcraft.zombie.utils.Logger;
import org.survivalcraft.zombie.utils.SharedConstants;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = SharedConstants.MODID, name = SharedConstants.MOD_NAME, version = SharedConstants.MOD_VERSION)
public class ZombieCraft {

	@Instance(SharedConstants.MODID)
	private static ZombieCraft instance;
	private CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		this.proxy = (CommonProxy)(FMLCommonHandler.instance().getSide().isClient() ? new ClientProxy() : new ServerProxy());
		
		proxy.preInit(event);
		FMLCommonHandler.instance().bus().register(new EventCommonHandler());
		MinecraftForge.EVENT_BUS.register(new EventCommonHandler());
		
		if(event.getSide().isServer()) {
			FMLCommonHandler.instance().bus().register(new EventServerHandler());
			MinecraftForge.EVENT_BUS.register(new EventServerHandler());
		} else {
			FMLCommonHandler.instance().bus().register(new EventRenderHandler());
			MinecraftForge.EVENT_BUS.register(new EventRenderHandler());
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
		Logger.infoInit("Registring ZombieCraft Server-side Commands...");
		((CommandHandler)event.getServer().getCommandManager()).registerCommand(new CommandZombiecraft());
	}
	
	public static ZombieCraft instance() {
		return instance;
	}
	
	public CommonProxy getProxy() {
		return proxy;
	}
	
	public ClientProxy getClientProxy() {
		return (ClientProxy)this.proxy;
	}
	
	public ServerProxy getServerProxy() {
		return (ServerProxy)this.proxy;
	}
	
}