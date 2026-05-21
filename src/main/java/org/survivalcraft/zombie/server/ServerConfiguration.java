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

	    // ------ CUSTOM FOG
	    ServerVariables.enableWeather = config.getBoolean("FogEnabled", "CustomFog", true, "Is the custom fog enabled?");
	    ServerVariables.customFogRainDensity = config.getFloat("RainDensity", "CustomFog", 0.02f, 0.0f, 1.0f, "Custom fog density when it's raining.");
	    ServerVariables.customFogStormDensity = config.getFloat("StormDensity", "CustomFog", 0.05f, 0.0f, 1.0f, "Custom fog density when thunderstorm.");
	    ServerVariables.customFogFoggyDensity = config.getFloat("FoggyDensity", "CustomFog", 0.1f, 0.0f, 1.0f, "Custom fog density when it's foggy.");
	    ServerVariables.customFogNormalDensity = config.getFloat("NormalDensity", "CustomFog", 0.01f, 0.0f, 1.0f, "Custom fog density when the weather is normal.");

	    // ------ GAMEPLAY FEATURES
	    ServerVariables.enableThirst = config.getBoolean("EnableThirst", "GameplayFeatures", true, "Is the thirst system enabled?");
	    ServerVariables.enableLoot = config.getBoolean("EnableLoot", "GameplayFeatures", true, "Is the looting system enabled?");
	    ServerVariables.lootReloadTime = config.getInt("LootReloadTime", "GameplayFeatures", 900, 0, Integer.MAX_VALUE, "Time needed for a lootable block to reload in seconds.");

	    // ------ TERRAIN ACCESS
	    ServerVariables.enableBlockBreaking = config.getBoolean("EnableBlockBreaking", "TerrainAccess", false, "Are the players able to break blocks?");
	    ServerVariables.enableBlockPlacing = config.getBoolean("EnableBlockPlacing", "TerrainAccess", false, "Are the players able to place blocks?");
	    ServerVariables.enableBlockUsing = config.getBoolean("EnableBlockUsing", "TerrainAccess", false, "Are the players able to use blocks?");
	    
	} catch(Exception e) {
	    Logger.error("Failed to read Server Configuration: " + e.getMessage());
	    e.printStackTrace();
	} finally {
	    if(config.hasChanged()) config.save();
	}
    }

}