package org.survivalcraft.zombie.server;

import java.io.File;

import org.survivalcraft.zombie.utils.Logger;

import net.minecraftforge.common.config.Configuration;

public class ServerConfiguration {

	public static Configuration config;
		
	public static void init(File configFile) {
		if(config == null) {
			config = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	public static void loadConfiguration() {
		try {
			config.load();
			
			//------ CUSTOM FOG
			ServerVariables.enableWeather = config.getBoolean("FogEnabled", "CustomFog", true, "Is the custom fog enabled?");
			ServerVariables.customFogRainDensity = config.getFloat("RainDensity", "CustomFog", 0.02f, 0.0f, 1.0f, "Custom fog density when it's raining.");
			ServerVariables.customFogStormDensity = config.getFloat("StormDensity", "CustomFog", 0.05f, 0.0f, 1.0f, "Custom fog density when thunderstorm.");
			ServerVariables.customFogFoggyDensity = config.getFloat("FoggyDensity", "CustomFog", 0.1f, 0.0f, 1.0f, "Custom fog density when it's foggy.");
			ServerVariables.customFogNormalDensity = config.getFloat("NormalDensity", "CustomFog", 0.01f, 0.0f, 1.0f, "Custom fog density when the weather is normal.");

			//------ GAMEPLAY FEATURES
			ServerVariables.enableThirst = config.getBoolean("EnableThirst", "GameplayFeatures", true, "Is the thirst system enabled?");
			
		} catch(Exception e) {
			Logger.error("Failed to read Server Configuration: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(config.hasChanged()) config.save();
		}
	}
	
}