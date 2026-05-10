package org.survivalcraft.zombie.common.entities.human;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityHumanoid extends EntityMob {

	private HumanoidCharacteristic properties;
	private EnumHumanoidPose pose;
	
	public EntityHumanoid(World world) {
		super(world);
		this.pose = EnumHumanoidPose.NONE;
		this.properties = createCharacteristics();
		
		this.setCurrentItemOrArmor(0, new ItemStack(Items.apple));
	}
	
	public abstract HumanoidCharacteristic createCharacteristics();
	
	public HumanoidCharacteristic getCharateristics() {
		return properties;
	}
	
	public EnumHumanoidPose getPose() {
		return pose;
	}
	
	public void setPose(EnumHumanoidPose pose) {
		this.pose = pose;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setString("Pose", this.getPose().name());
		nbt.setTag("Characteristic", this.properties.saveToNBT());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		
		if(nbt.hasKey("Characteristic")) this.properties = HumanoidCharacteristic.readFromNBT(nbt.getCompoundTag("Characteristic"));
		else this.properties = createCharacteristics();
	}
}