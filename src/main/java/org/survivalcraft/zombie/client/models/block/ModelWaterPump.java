package org.survivalcraft.zombie.client.models.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWaterPump extends ModelBase {
	
	/**
	 * Model used for TESR "RenderWaterPump", made with Blockbench. 
	 * @author Painperdu12.
	 */
	
	public ModelRenderer base;
    public ModelRenderer side_r1;
    public ModelRenderer side_r2;
    public ModelRenderer plate;
    public ModelRenderer pump;
    public ModelRenderer pumpArm_r1;

    public ModelWaterPump() {
        this.textureWidth = 128;
        this.textureHeight = 128;

        // --- BASE ---
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.base.setTextureOffset(26, 46).addBox(6.0F, -7.0F, -7.0F, 1, 7, 1, 0.0F);
        this.base.setTextureOffset(30, 46).addBox(6.0F, -7.0F, 6.0F, 1, 7, 1, 0.0F);
        this.base.setTextureOffset(34, 46).addBox(-7.0F, -7.0F, 6.0F, 1, 7, 1, 0.0F);
        this.base.setTextureOffset(38, 46).addBox(-7.0F, -7.0F, -7.0F, 1, 7, 1, 0.0F);
        this.base.setTextureOffset(16, 30).addBox(-6.0F, -7.0F, -8.0F, 12, 7, 1, 0.0F);
        this.base.setTextureOffset(16, 38).addBox(-6.0F, -7.0F, 7.0F, 12, 7, 1, 0.0F);

        this.side_r1 = new ModelRenderer(this, 42, 38);
        this.side_r1.setRotationPoint(-7.0F, 0.0F, -6.0F);
        this.side_r1.addBox(-12.0F, -7.0F, -1.0F, 12, 7, 1, 0.0F);
        this.setRotateAngle(side_r1, 0.0F, 1.5708F, 0.0F);
        this.base.addChild(this.side_r1);

        this.side_r2 = new ModelRenderer(this, 42, 30);
        this.side_r2.setRotationPoint(8.0F, 0.0F, -6.0F);
        this.side_r2.addBox(-12.0F, -7.0F, -1.0F, 12, 7, 1, 0.0F);
        this.setRotateAngle(side_r2, 0.0F, 1.5708F, 0.0F);
        this.base.addChild(this.side_r2);

        // --- PLATE ---
        this.plate = new ModelRenderer(this, 0, 0);
        this.plate.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.plate.setTextureOffset(0, 0).addBox(-9.0F, -8.0F, -9.0F, 18, 1, 18, 0.0F);
        this.plate.setTextureOffset(0, 19).addBox(-6.0F, -9.0F, -6.0F, 12, 1, 10, 0.0F);

        // --- PUMP ---
        this.pump = new ModelRenderer(this, 0, 30);
        this.pump.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pump.setTextureOffset(0, 30).addBox(-2.0F, -31.0F, 4.0F, 4, 23, 4, 0.0F);
        this.pump.setTextureOffset(44, 19).addBox(-1.0F, -26.0F, -1.0F, 2, 2, 5, 0.0F);
        this.pump.setTextureOffset(44, 26).addBox(-1.0F, -24.0F, -2.0F, 2, 2, 2, 0.0F);

        this.pumpArm_r1 = new ModelRenderer(this, 16, 46);
        this.pumpArm_r1.setRotationPoint(1.0F, -23.0F, 17.0F);
        this.pumpArm_r1.addBox(-2.0F, -15.0F, -1.0F, 2, 15, 3, 0.0F);
        this.setRotateAngle(pumpArm_r1, 0.8727F, 0.0F, 0.0F);
        this.pump.addChild(this.pumpArm_r1);
    }

    @Override
    public void render(Entity entity, float x, float y, float z, float yaw, float pitch, float scale) { 
        this.base.render(scale);
        this.plate.render(scale);
        this.pump.render(scale);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}