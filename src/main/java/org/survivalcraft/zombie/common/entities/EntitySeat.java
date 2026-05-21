package org.survivalcraft.zombie.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class EntitySeat extends Entity {

    public static void sitOnBlock(World world, EntityPlayer player, double x, double y, double z, double yOffset) {
	EntitySeat seat = new EntitySeat(world, x, y, z, yOffset);
	world.spawnEntityInWorld(seat);
	player.mountEntity(seat);
    }

    public static void sitOnBlockWithRotation(World world, EntityPlayer player, double x, double y, double z, double yOffset, int rotationMeta, double rotationOffset) {
	EntitySeat seat = new EntitySeat(world, x, y, z, yOffset, rotationMeta, rotationOffset);
	world.spawnEntityInWorld(seat);
	player.mountEntity(seat);
    }

    private int updatesCounter;

    public EntitySeat(World world) {
	super(world);
	this.updatesCounter = 0;
	this.noClip = true;
	this.height = 0.0f;
	this.width = 0.0f;
    }

    public EntitySeat(World world, double x, double y, double z, double yOffset) {
	this(world);
	this.setPosition(x + 0.5d, y + yOffset, z + 0.5d);
    }

    public EntitySeat(World world, double x, double y, double z, double yOffset, int rotation, double rotationOffset) {
	this(world);
	this.setPostionConsideringRotation(x + 0.5d, y + yOffset, z + 0.5d, rotation, rotationOffset);
    }

    public void setPostionConsideringRotation(double x, double y, double z, int rotation, double rotationOffset) {
	switch (rotation) {
	    case 2:
		z += rotationOffset;
		break;
	    case 0:
		z -= rotationOffset;
		break;
	    case 3:
		x -= rotationOffset;
		break;
	    case 1:
		x += rotationOffset;
		break;
	}

	this.setPosition(x, y, z);
    }

    @Override
    public double getMountedYOffset() {
	return this.height * 0.0D;
    }

    @Override
    protected boolean shouldSetPosAfterLoading() {
	return false;
    }

    @Override
    public void applyEntityCollision(Entity entity) {
    }

    @Override
    public boolean canBePushed() {
	return false;
    }

    @Override
    public boolean canBeCollidedWith() {
	return false;
    }

    @Override
    public void onUpdate() {
	this.noClip = true;
	super.onUpdate();
	this.updatesCounter++;

	if(!this.worldObj.isRemote && this.updatesCounter > 30) {
	    if(this.riddenByEntity == null)
		this.setDead();
	}
    }

    @Override
    protected void entityInit() {
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
    }
}