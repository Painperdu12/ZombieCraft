package org.survivalcraft.zombie.common;

import java.util.Random;

import org.survivalcraft.zombie.common.network.NetworkHandler;
import org.survivalcraft.zombie.common.network.messages.MessageS2CPlayerData;
import org.survivalcraft.zombie.server.ServerVariables;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayerData implements IExtendedEntityProperties {

    public static final String PROPERTIES_NAME = "ZombieCraftPlayerData";
    public static final String LEG_BROKEN_NAME = "LegBroken";
    public static final String BLEEDING_NAME = "Bleeding";
    public static final String WATER_NAME = "WaterAmount";
    public static final String DEATHS_NAME = "Deaths";
    public static final String DAYS_PASSED_NAME = "DaysPassed";
    public static final String LAST_HURT_NAME = "LastHurtTime";
    public static final String INFECTED_NAME = "Infected";

    public static void register(EntityPlayer player) {
	player.registerExtendedProperties(PROPERTIES_NAME, new ExtendedPlayerData(player));
    }

    public static ExtendedPlayerData get(EntityPlayer player) {
	return (ExtendedPlayerData) player.getExtendedProperties(PROPERTIES_NAME);
    }

    private final EntityPlayer player;
    private final Random rand;
    private boolean syncScheduled;

    private InventoryBasic lootingInventory;
    
    private boolean legBroken;
    private boolean bleeding;
    private boolean infected;

    private int waterAmount;
    private int waterCountdown;
    private final int maxWaterAmount = 20;

    private long lastHurtTime;
    private int deaths;
    private int daysPassed;

    public ExtendedPlayerData(EntityPlayer player) {
	this.player = player;
	this.rand = new Random();
	this.lootingInventory = null;
	this.legBroken = false;
	this.bleeding = false;
	this.infected = false;
	this.waterCountdown = 1600;
	this.waterAmount = maxWaterAmount;
	this.deaths = 0;
	this.daysPassed = 0;
	this.lastHurtTime = 0l;
    }

    private void sync() {
	if(!this.player.worldObj.isRemote) {
	    MessageS2CPlayerData message = new MessageS2CPlayerData(this);
	    EntityPlayerMP onlinePlayer = (EntityPlayerMP) player;
	    NetworkHandler.sendToPlayer(message, onlinePlayer);
	}
    }

    private void scheduleSync() {
	if(!this.syncScheduled)
	    this.syncScheduled = true;
    }

    public void onUpdate() {
	if(!player.worldObj.isRemote) {
	    if(this.waterCountdown <= 0 && ServerVariables.enableThirst) {
		if(player.getHealth() < 5) this.waterCountdown = 480;
		else if(player.getHealth() < 10) this.waterCountdown = 780;
		else if(player.getHealth() < 15) this.waterCountdown = 980;
		else this.waterCountdown = 1080;

		this.subWater(1);

		if(this.waterAmount <= 0) {
		    this.waterCountdown = 40;
		    player.attackEntityFrom(ModDamageSources.THIRST, 1f);
		}
	    }

	    if(rand.nextFloat() < 0.90f) { // 90% chance
		this.waterCountdown--;
	    }

	    if(this.lastHurtTime != 0 && this.lastHurtTime + 5000l >= System.currentTimeMillis()) {
		this.lastHurtTime = 0l;
		this.scheduleSync();
	    }

	    if(this.syncScheduled) {
		this.sync();
		this.syncScheduled = false;
	    }
	}
    }

    public void onDeath() {
	this.waterAmount = this.maxWaterAmount;
	this.deaths += 1;
	this.daysPassed = 0;
	this.lastHurtTime = 0l;
	this.infected = false;
	this.bleeding = false;
	this.legBroken = false;
	this.lootingInventory = null;

	this.scheduleSync();
    }

    public void copy(ExtendedPlayerData source) {
	this.legBroken = source.legBroken;
	this.bleeding = source.bleeding;
	this.waterAmount = source.waterAmount;
	this.deaths = source.deaths;
	this.daysPassed = source.daysPassed;
	this.infected = source.infected;
	this.lootingInventory = source.lootingInventory;
    }

    public void handleMesage(MessageS2CPlayerData msg) {
	this.bleeding = msg.bleeding;
	this.legBroken = msg.legBroken;
	this.waterAmount = msg.waterAmount;
	this.deaths = msg.deaths;
	this.daysPassed = msg.daysPassed;
	this.lastHurtTime = msg.lastHurtTime;
	this.infected = msg.infected;
    }

    @Override
    public void saveNBTData(NBTTagCompound tag) {
	NBTTagCompound nbt = new NBTTagCompound();

	nbt.setBoolean(LEG_BROKEN_NAME, legBroken);
	nbt.setBoolean(BLEEDING_NAME, bleeding);
	nbt.setInteger(WATER_NAME, waterAmount);
	nbt.setInteger(DAYS_PASSED_NAME, daysPassed);
	nbt.setInteger(DEATHS_NAME, deaths);
	nbt.setLong(LAST_HURT_NAME, lastHurtTime);
	nbt.setBoolean(INFECTED_NAME, infected);

	tag.setTag(PROPERTIES_NAME, nbt);
	this.scheduleSync();
    }

    @Override
    public void loadNBTData(NBTTagCompound tag) {
	NBTTagCompound nbt = tag.getCompoundTag(PROPERTIES_NAME);

	this.legBroken = nbt.getBoolean(LEG_BROKEN_NAME);
	this.bleeding = nbt.getBoolean(BLEEDING_NAME);
	this.waterAmount = nbt.getInteger(WATER_NAME);
	this.deaths = nbt.getInteger(DEATHS_NAME);
	this.daysPassed = nbt.getInteger(DAYS_PASSED_NAME);
	this.lastHurtTime = nbt.getLong(LAST_HURT_NAME);
	this.infected = nbt.getBoolean(INFECTED_NAME);

	this.scheduleSync();
    }

    @Override
    public void init(Entity entity, World world) {
    }

    // --------------- DATA MANAGEMENT

    public int getWaterAmount() {
	return waterAmount;
    }

    public int getMaxWaterAmount() {
	return maxWaterAmount;
    }

    public void addWater(int water) {
	int newWater = this.waterAmount + water;
	int clamped = MathHelper.clamp_int(newWater, 0, maxWaterAmount);

	this.waterAmount = clamped;
	this.scheduleSync();
    }

    public void subWater(int water) {
	int newWater = this.waterAmount - water;
	int clamped = MathHelper.clamp_int(newWater, 0, maxWaterAmount);

	this.waterAmount = clamped;
	this.scheduleSync();
    }

    public void setWater(int water) {
	if(this.waterAmount != water) {
	    int clamped = MathHelper.clamp_int(water, 0, maxWaterAmount);
	    this.waterAmount = clamped;
	    this.scheduleSync();
	}
    }

    public boolean canDrink() {
	return this.waterAmount < 20;
    }

    public boolean isLegBroken() {
	return legBroken;
    }

    public void setLegBroken(boolean broken) {
	if(this.legBroken != broken) {
	    this.legBroken = broken;
	    this.scheduleSync();
	}
    }

    public boolean isBleeding() {
	return bleeding;
    }

    public void setBleeding(boolean bleeding) {
	if(this.bleeding != bleeding) {
	    this.bleeding = bleeding;
	    this.scheduleSync();
	}
    }

    public int getDeaths() {
	return deaths;
    }

    public void addDeath() {
	this.deaths += 1;
	this.scheduleSync();
    }

    public int getDaysPassed() {
	return this.daysPassed;
    }

    public void addDayPassed() {
	this.daysPassed += 1;
	this.scheduleSync();
    }

    public void resetDaysPassed() {
	this.daysPassed = 0;
	this.scheduleSync();
    }

    public long getLastHurtTime() {
	return lastHurtTime;
    }

    public void setHurted() {
	this.lastHurtTime = System.currentTimeMillis();
	this.scheduleSync();
    }

    public boolean isInfected() {
	return infected;
    }

    public void setInfected(boolean infected) {
	if(this.infected != infected) {
	    this.infected = infected;
	    this.scheduleSync();
	}
    }
    
    public InventoryBasic getLootingInventory() {
	return lootingInventory;
    }
    
    public void setLootingInventory(InventoryBasic lootingInv) {
	this.lootingInventory = lootingInv;
	this.scheduleSync();
    }
}