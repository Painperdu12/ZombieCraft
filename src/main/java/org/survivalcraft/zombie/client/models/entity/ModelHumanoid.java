package org.survivalcraft.zombie.client.models.entity;

import org.survivalcraft.zombie.common.entities.human.EntityHumanoid;
import org.survivalcraft.zombie.common.entities.human.EnumHumanoidPose;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.util.MathHelper;

public class ModelHumanoid extends ModelBiped {

	@Override
	public void setRotationAngles(float limb, float limbAmount, float ageTicks, float headYaw, float headPitch, float scale, Entity entity) {
		EntityHumanoid human = (EntityHumanoid)entity;
		EnumHumanoidPose pose = human.getPose();
		
	
		if(pose == EnumHumanoidPose.HAPPY) {
			this.bipedRightArm.rotateAngleX = -2.7f;
			this.bipedLeftArm.rotateAngleX = -2.7f;
		} else if(pose == EnumHumanoidPose.CROUNCH) {
			this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
            this.bipedHeadwear.rotationPointY = 1.0F;
		} else if(pose == EnumHumanoidPose.SIT) {
			this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2.5F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2.5F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
		} else super.setRotationAngles(limb, limbAmount, ageTicks, headYaw, headPitch, scale, entity);
	}
}