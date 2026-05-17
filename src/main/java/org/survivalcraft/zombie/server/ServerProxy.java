package org.survivalcraft.zombie.server;

import org.survivalcraft.zombie.common.CommonProxy;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		
		Logger.infoInit("Loading Server-side configuration...");
		ServerConfiguration.init(event.getSuggestedConfigurationFile());
	}
}