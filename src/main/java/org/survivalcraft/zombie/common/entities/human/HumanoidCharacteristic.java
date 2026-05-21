package org.survivalcraft.zombie.common.entities.human;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;

public class HumanoidCharacteristic {

    public static final String[] NAMES = new String[] { "Anatola", "Eudes", "John", "Friedrich", "Sam", "Greg", "Hervé", "Michel", "Tom" };
    public static final String[] LAST_NAMES = new String[] { "McHelen", "Dupuis", "Schirach", "Brown", "Ferrand", "Campbell", "Adelson", "Petit", "Prevost" };

    public String name;
    public String textureName;
    public int eyes;
    public int body;
    public int pants;
    public int shirt;
    public int hair;

    public HumanoidCharacteristic(String name, String textureName, int eyes, int body, int pants, int shirt, int hair) {
	this.name = name;
	this.textureName = textureName;
	this.eyes = eyes;
	this.body = body;
	this.pants = pants;
	this.shirt = shirt;
	this.hair = hair;
    }

    public static HumanoidCharacteristic generateRandomCharacteristic(Random rand, String texture, int maxEyes,
	    int maxBody, int maxShirt, int maxPants, int maxHair) {
	String name = NAMES[rand.nextInt(NAMES.length)] + " " + LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
	int eyes = rand.nextInt(maxEyes) + 1;
	int body = rand.nextInt(maxBody) + 1;
	int shirt = rand.nextInt(maxShirt) + 1;
	int pants = rand.nextInt(maxPants) + 1;
	int hair = rand.nextInt(maxHair) + 1;

	return new HumanoidCharacteristic(name, texture, eyes, body, shirt, pants, hair);
    }

    public NBTTagCompound saveToNBT() {
	NBTTagCompound nbt = new NBTTagCompound();
	nbt.setString("Name", this.name);
	nbt.setString("TextureNale", this.textureName);
	nbt.setInteger("Hair", this.hair);
	nbt.setInteger("Eyes", this.eyes);
	nbt.setInteger("Body", this.body);
	nbt.setInteger("Shirt", this.shirt);
	nbt.setInteger("Pants", this.pants);

	return nbt;
    }

    public static HumanoidCharacteristic readFromNBT(NBTTagCompound nbt) {
	String name = nbt.getString("Name");
	String textureName = nbt.getString("TextureName");
	int hair = nbt.getInteger("Hair");
	int eyes = nbt.getInteger("Eyes");
	int body = nbt.getInteger("Body");
	int shirt = nbt.getInteger("Shirt");
	int pants = nbt.getInteger("Pants");

	return new HumanoidCharacteristic(name, textureName, eyes, body, pants, shirt, hair);
    }
}