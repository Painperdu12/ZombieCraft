package org.survivalcraft.zombie.client.models.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFireAxe extends ModelBase {
	
	private final ModelRenderer bb_main;

	public ModelFireAxe() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, -32.0F, -1.0F, 2, 32, 2, 0.0F));
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 11, -4.0F, -31.0F, -1.0F, 2, 1, 2, 0.0F));
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 5, 0.0F, -31.0F, -1.0F, 6, 1, 2, 0.0F));
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 0, 0.0F, -30.0F, -1.0F, 7, 3, 2, 0.0F));
		bb_main.cubeList.add(new ModelBox(bb_main, 8, 8, 0.0F, -27.0F, -1.0F, 6, 1, 2, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}