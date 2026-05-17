package org.survivalcraft.zombie.common;

import org.survivalcraft.zombie.ZombieCraft;
import org.survivalcraft.zombie.common.blocks.ModBlocks;
import org.survivalcraft.zombie.common.entities.ModEntities;
import org.survivalcraft.zombie.common.events.EventCommonHandler;
import org.survivalcraft.zombie.common.items.ModItems;
import org.survivalcraft.zombie.common.network.NetworkHandler;
import org.survivalcraft.zombie.common.tiles.ModTiles;
import org.survivalcraft.zombie.utils.Logger;
import org.survivalcraft.zombie.utils.SharedConstants;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		Logger.infoInit("Pre-Initialization of ZombieCraft version " + SharedConstants.MOD_VERSION);
		Logger.infoInit("Registering all ZombieCraft game elements...");
		
		ModBlocks.register();
		ModTiles.register();
		ModEntities.register();
		ModItems.register();
		
		Logger.infoInit("Initializing Messages Channel");
		NetworkHandler.init();		
	} 
	
	public void init(FMLInitializationEvent event) {
		
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}