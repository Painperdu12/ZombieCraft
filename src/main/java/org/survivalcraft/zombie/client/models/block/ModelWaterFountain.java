package org.survivalcraft.zombie.client.models.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWaterFountain extends ModelBase {

    /**
     * Model used for TESR "RenderWaterFountain", made with Blockbench.
     * 
     * @author Painperdu12.
     */

    private final ModelRenderer details;
    private final ModelRenderer spigot_r1;
    private final ModelRenderer base;

    public ModelWaterFountain() {
	textureWidth = 128;
	textureHeight = 128;

	details = new ModelRenderer(this);
	details.setRotationPoint(5.0F, 8.0F, -3.0F);
	details.cubeList.add(new ModelBox(details, 0, 14, -12.0F, -2.0F, -1.0F, 13, 2, 8, 0.0F));
	details.cubeList.add(new ModelBox(details, 42, 14, -8.0F, -4.0F, 8.0F, 6, 1, 3, 0.0F));

	spigot_r1 = new ModelRenderer(this);
	spigot_r1.setRotationPoint(-5.0F, -5.0F, 9.0F);
	details.addChild(spigot_r1);
	setRotationAngle(spigot_r1, 0.0F, -0.7854F, 0.0F);
	spigot_r1.cubeList.add(new ModelBox(spigot_r1, 42, 18, -4.0F, -1.0F, -1.0F, 4, 1, 2, 0.0F));
	spigot_r1.cubeList.add(new ModelBox(spigot_r1, 18, 46, -1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F));

	base = new ModelRenderer(this);
	base.setRotationPoint(0.0F, 24.0F, 0.0F);
	base.cubeList.add(new ModelBox(base, 0, 46, -2.0F, -13.0F, 3.0F, 4, 13, 5, 0.0F));
	base.cubeList.add(new ModelBox(base, 0, 0, -6.0F, -15.0F, -4.0F, 12, 2, 12, 0.0F));
	base.cubeList.add(new ModelBox(base, 0, 40, -8.0F, -19.0F, -6.0F, 16, 4, 2, 0.0F));
	base.cubeList.add(new ModelBox(base, 36, 40, -6.0F, -19.0F, 4.0F, 12, 4, 4, 0.0F));
	base.cubeList.add(new ModelBox(base, 0, 24, 6.0F, -19.0F, -4.0F, 2, 4, 12, 0.0F));
	base.cubeList.add(new ModelBox(base, 28, 24, -8.0F, -19.0F, -4.0F, 2, 4, 12, 0.0F));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	details.render(f5);
	base.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
	modelRenderer.rotateAngleX = x;
	modelRenderer.rotateAngleY = y;
	modelRenderer.rotateAngleZ = z;
    }
}