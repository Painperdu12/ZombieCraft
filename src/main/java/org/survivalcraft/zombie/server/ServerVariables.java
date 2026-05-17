package org.survivalcraft.zombie.server;

public class ServerVariables {

	public static float customFogNormalDensity;
	public static float customFogRainDensity;
	public static float customFogStormDensity;
	public static float customFogFoggyDensity;
	public static int weatherTypeIndex;
	public static boolean enableWeather;
	
	public static boolean enableThirst;
	
	//Set on default values
	static {
		customFogNormalDensity = -1;
		customFogRainDensity = -1;
		customFogStormDensity = -1;
		customFogFoggyDensity = -1;
		weatherTypeIndex = 0;
		enableWeather = true;
		enableThirst = true;
	}
}