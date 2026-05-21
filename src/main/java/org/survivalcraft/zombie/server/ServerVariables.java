package org.survivalcraft.zombie.server;

public class ServerVariables {

    public static float customFogNormalDensity;
    public static float customFogRainDensity;
    public static float customFogStormDensity;
    public static float customFogFoggyDensity;
    public static int weatherTypeIndex;
    public static boolean enableWeather;

    public static boolean enableThirst;
    public static boolean enableLoot;
    public static int lootReloadTime;
    
    public static boolean enableBlockBreaking;
    public static boolean enableBlockPlacing;
    public static boolean enableBlockUsing;

    // Set on default values
    static {
	customFogNormalDensity = -1;
	customFogRainDensity = -1;
	customFogStormDensity = -1;
	customFogFoggyDensity = -1;
	weatherTypeIndex = 0;
	lootReloadTime = 0;
	enableWeather = true;
	enableThirst = true;
	enableLoot = true;
	enableBlockBreaking = false;
	enableBlockPlacing = false;
	enableBlockUsing = false;
    }
}