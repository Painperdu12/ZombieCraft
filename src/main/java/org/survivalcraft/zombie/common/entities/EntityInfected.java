package org.survivalcraft.zombie.common.entities;

import org.survivalcraft.zombie.common.entities.human.EntityHumanoid;
import org.survivalcraft.zombie.common.entities.human.HumanoidCharacteristic;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityInfected extends EntityHumanoid {

    public EntityInfected(World world) {
	super(world);
	this.tasks.addTask(1, new EntityAISwimming(this));
	this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0d, true));
	this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.0d, 20.0f));
	this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
	this.tasks.addTask(4, new EntityAIWander(this, 1.0d));
	this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0f));
	this.tasks.addTask(6, new EntityAILookIdle(this));
	this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
	this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    public HumanoidCharacteristic createCharacteristics() {
	return HumanoidCharacteristic.generateRandomCharacteristic(this.rand, "zombie", 1, 1, 1, 1, 1);
    }

    @Override
    protected void applyEntityAttributes() {
	super.applyEntityAttributes();
	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25d);
	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6d);
	this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.5d);
    }
}