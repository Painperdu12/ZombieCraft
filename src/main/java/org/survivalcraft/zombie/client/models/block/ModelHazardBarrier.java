package org.survivalcraft.zombie.client.models.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHazardBarrier extends ModelBase {

    /**
     * Model used for TESR "RenderHazardBarrier", made with Blockbench.
     * 
     * @author Painperdu12.
     */

    public ModelRenderer panels;
    public ModelRenderer panel_r1;
    public ModelRenderer panel_r2;
    public ModelRenderer supports;
    public ModelRenderer support_r1;
    public ModelRenderer support_r2;
    public ModelRenderer support_r3;
    public ModelRenderer support_r4;
    public ModelRenderer support_r5;

    public ModelHazardBarrier() {
	this.textureWidth = 64;
	this.textureHeight = 64;

	// --- PANELS ---
	this.panels = new ModelRenderer(this);
	this.panels.setRotationPoint(0.0F, 24.0F, 0.0F);

	this.panel_r1 = new ModelRenderer(this, 0, 8);
	this.panel_r1.setRotationPoint(0.0F, -2.0F, -4.9F);
	this.panel_r1.addBox(-7.0F, -7.0F, -1.0F, 14, 7, 1);
	this.setRotation(this.panel_r1, -0.6976F, 0.0F, 0.0F);
	this.panels.addChild(this.panel_r1);

	this.panel_r2 = new ModelRenderer(this, 0, 0);
	this.panel_r2.setRotationPoint(0.0F, -2.1F, 4.9F);
	this.panel_r2.addBox(-7.0F, -7.0F, 0.0F, 14, 7, 1);
	this.setRotation(this.panel_r2, 0.6976F, 0.0F, 0.0F);
	this.panels.addChild(this.panel_r2);

	// --- SUPPORTS ---
	this.supports = new ModelRenderer(this);
	this.supports.setRotationPoint(0.0F, 24.0F, 0.0F);

	this.support_r1 = new ModelRenderer(this, 0, 16);
	this.support_r1.setRotationPoint(0.1F, -7.0F, 0.0F);
	this.support_r1.addBox(-8.0F, -1.0F, -1.0F, 16, 1, 1);
	this.setRotation(this.support_r1, -0.7854F, 0.0F, 0.0F);
	this.supports.addChild(this.support_r1);

	this.support_r2 = new ModelRenderer(this, 8, 18);
	this.support_r2.setRotationPoint(7.9F, 0.0F, 7.0F);
	this.support_r2.addBox(-1.0F, -10.0F, 0.0F, 1, 10, 1);
	this.setRotation(this.support_r2, 0.6976F, 0.008F, 0.0203F);
	this.supports.addChild(this.support_r2);

	this.support_r3 = new ModelRenderer(this, 12, 18);
	this.support_r3.setRotationPoint(-8.0F, 0.0F, 7.0F);
	this.support_r3.addBox(0.0F, -10.0F, 0.0F, 1, 10, 1);
	this.setRotation(this.support_r3, 0.6976F, 0.008F, 0.0203F);
	this.supports.addChild(this.support_r3);

	this.support_r4 = new ModelRenderer(this, 4, 18);
	this.support_r4.setRotationPoint(7.7F, 0.0F, -7.0F);
	this.support_r4.addBox(-1.0F, -10.0F, -1.0F, 1, 10, 1);
	this.setRotation(this.support_r4, -0.6976F, 0.008F, 0.0203F);
	this.supports.addChild(this.support_r4);

	this.support_r5 = new ModelRenderer(this, 0, 18);
	this.support_r5.setRotationPoint(-8.0F, 0.0F, -7.0F);
	this.support_r5.addBox(0.0F, -10.0F, -1.0F, 1, 10, 1);
	this.setRotation(this.support_r5, -0.6976F, 0.008F, 0.0203F);
	this.supports.addChild(this.support_r5);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	this.panels.render(f5);
	this.supports.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
    }
}