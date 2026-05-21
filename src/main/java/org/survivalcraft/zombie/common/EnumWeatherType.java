package org.survivalcraft.zombie.common;

public enum EnumWeatherType {

    NORMAL(0, "Normal"), 
    RAIN(1, "Rain"), 
    STORM(2, "Storm"), 
    FOGGY(3, "Foggy");

    public int index;
    public String name;

    private EnumWeatherType(int index, String name) {
	this.index = index;
	this.name = name;
    }
}