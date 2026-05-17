package org.survivalcraft.zombie.client.models.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrashcan extends ModelBase {
	
	private final ModelRenderer can;
	private final ModelRenderer sides_r1;
	private final ModelRenderer sides_r2;
	private final ModelRenderer top;
	private final ModelRenderer borders_r1;
	private final ModelRenderer borders_r2;

	public ModelTrashcan() {
		textureWidth = 128;
		textureHeight = 128;

		can = new ModelRenderer(this);
		can.setRotationPoint(0.0F, 24.0F, 0.0F);
		can.cubeList.add(new ModelBox(can, 0, 43, -5.0F, -1.0F, -5.0F, 10, 1, 10, 0.0F));
		can.cubeList.add(new ModelBox(can, 0, 13, -6.0F, -18.0F, -6.0F, 1, 18, 12, 0.0F));
		can.cubeList.add(new ModelBox(can, 26, 13, 5.0F, -18.0F, -6.0F, 1, 18, 12, 0.0F));

		sides_r1 = new ModelRenderer(this);
		sides_r1.setRotationPoint(-6.0F, 0.0F, -6.0F);
		can.addChild(sides_r1);
		setRotationAngle(sides_r1, 0.0F, 1.5708F, 0.0F);
		sides_r1.cubeList.add(new ModelBox(sides_r1, 52, 0, -1.0F, -18.0F, 1.0F, 1, 18, 10, 0.0F));

		sides_r2 = new ModelRenderer(this);
		sides_r2.setRotationPoint(-6.0F, 0.0F, 5.0F);
		can.addChild(sides_r2);
		setRotationAngle(sides_r2, 0.0F, 1.5708F, 0.0F);
		sides_r2.cubeList.add(new ModelBox(sides_r2, 40, 43, -1.0F, -18.0F, 1.0F, 1, 18, 10, 0.0F));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 24.0F, 0.0F);
		top.cubeList.add(new ModelBox(top, 0, 0, -6.0F, -21.0F, -6.0F, 12, 1, 12, 0.0F));
		top.cubeList.add(new ModelBox(top, 52, 28, -7.0F, -20.0F, 5.0F, 14, 2, 2, 0.0F));
		top.cubeList.add(new ModelBox(top, 52, 32, -7.0F, -20.0F, -7.0F, 14, 2, 2, 0.0F));

		borders_r1 = new ModelRenderer(this);
		borders_r1.setRotationPoint(6.0F, -18.0F, 0.0F);
		top.addChild(borders_r1);
		setRotationAngle(borders_r1, 0.0F, -1.5708F, 0.0F);
		borders_r1.cubeList.add(new ModelBox(borders_r1, 0, 54, -5.0F, -2.0F, -1.0F, 10, 2, 2, 0.0F));

		borders_r2 = new ModelRenderer(this);
		borders_r2.setRotationPoint(-6.0F, -18.0F, 0.0F);
		top.addChild(borders_r2);
		setRotationAngle(borders_r2, 0.0F, -1.5708F, 0.0F);
		borders_r2.cubeList.add(new ModelBox(borders_r2, 52, 36, -5.0F, -2.0F, -1.0F, 10, 2, 2, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		can.render(f5);
		top.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}