package org.survivalcraft.zombie.client;

import org.survivalcraft.zombie.common.EnumWeatherType;

public class ClientVariables {

    // ------ CLIENT VARIABLES

    public static boolean customFogEnabled;
    public static float fogNormalDensity;
    public static float fogRainDensity;
    public static float fogStormDensity;
    public static float fogFoggyDensity;
    public static int weatherTypeIndex;

    // Set on default values
    static {
	customFogEnabled = true;
	fogNormalDensity = 0.01f;
	fogRainDensity = 0.02f;
	fogStormDensity = 0.05f;
	fogFoggyDensity = 0.1f;
	weatherTypeIndex = 0;
    }

    // Utilities fonctions
    public static float getCustomFogDensityByIndex(int index) {
	if(index == EnumWeatherType.NORMAL.index)
	    return fogNormalDensity;
	else if(index == EnumWeatherType.RAIN.index)
	    return fogRainDensity;
	else if(index == EnumWeatherType.STORM.index)
	    return fogStormDensity;
	else if(index == EnumWeatherType.FOGGY.index)
	    return fogFoggyDensity;

	return fogNormalDensity;
    }
}