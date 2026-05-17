package org.survivalcraft.zombie.client.render;

import org.lwjgl.opengl.GL11;
import org.survivalcraft.zombie.client.models.ModelHumanoid;
import org.survivalcraft.zombie.common.entities.human.EntityHumanoid;
import org.survivalcraft.zombie.common.entities.human.EnumHumanoidPose;
import org.survivalcraft.zombie.common.entities.human.HumanoidCharacteristic;
import org.survivalcraft.zombie.utils.SharedConstants;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHumanoid extends RenderBiped {

	public static final float DEFAULT_SCALE = 0.0625f; // 1/6
	public static final float MEDIUM_SCALE = 1.4f;
	public static final float BIG_SCALE = 1.6f;
	
	private final float scale;

	public RenderHumanoid(ModelBiped model, float scale) {
		super(model, 0.8f);
		this.renderPassModel = model;
		this.scale = scale;
	}
	
	public RenderHumanoid(ModelHumanoid model) {
		this(model, DEFAULT_SCALE);
	}
	
	@Override
	protected void preRenderCallback(EntityLivingBase living, float oui) {
		GL11.glScalef(scale, scale, scale);
	}
	
	@Override
	protected int shouldRenderPass(EntityLiving entity, int pass, float pTicks) {
		EntityHumanoid human = (EntityHumanoid)entity;
		HumanoidCharacteristic c = human.getCharateristics();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		if(pass == 0) {
			this.bindTexture(new ResourceLocation(SharedConstants.MODID, "textures/entities/human/" + c.textureName + "/eyes_" + c.eyes + ".png"));
			return 1;
		} else if(pass == 1) {
			this.bindTexture(new ResourceLocation(SharedConstants.MODID, "textures/entities/human/" + c.textureName + "/hair_" + c.hair + ".png"));
			return 1;
		} else if(pass == 2) {
			this.bindTexture(new ResourceLocation(SharedConstants.MODID, "textures/entities/human/" + c.textureName + "/shirt_" + c.shirt + ".png"));
			return 1;
		} else if(pass == 3) {
			this.bindTexture(new ResourceLocation(SharedConstants.MODID, "textures/entities/human/" + c.textureName + "/pants_" + c.pants + ".png"));
			return 1;
		}
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		return -1;
	}
	
	@Override
	public void doRender(Entity entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
		EntityHumanoid human = (EntityHumanoid)entity;
		EnumHumanoidPose pose = human.getPose();
		
		double d3 = p_76986_4_ - (double)entity.yOffset;

        if(pose == EnumHumanoidPose.CROUNCH) d3 -= 0.3d;
        if(pose == EnumHumanoidPose.SIT) d3 -= 0.9d;
        
		super.doRender(entity, p_76986_2_, d3, p_76986_6_, p_76986_8_, p_76986_9_);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityHumanoid human = (EntityHumanoid)entity;
		return new ResourceLocation(SharedConstants.MODID, "textures/entities/human/" + human.getCharateristics().textureName + "/body_" + human.getCharateristics().body + ".png");
	}
}