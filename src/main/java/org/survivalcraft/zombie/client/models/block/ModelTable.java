package org.survivalcraft.zombie.client.models.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTable extends ModelBase {

    /**
     * Model used for TESR "RenderTable", made with Blockbench.
     * 
     * @author Painperdu12.
     */

    private final ModelRenderer pillars;
    private final ModelRenderer table;

    public ModelTable() {
	textureWidth = 128;
	textureHeight = 128;

	pillars = new ModelRenderer(this);
	pillars.setRotationPoint(0.0F, 24.0F, 0.0F);
	pillars.cubeList.add(new ModelBox(pillars, 8, 17, -16.0F, -15.0F, 5.0F, 2, 15, 2, 0.0F));
	pillars.cubeList.add(new ModelBox(pillars, 0, 17, -16.0F, -15.0F, -7.0F, 2, 15, 2, 0.0F));
	pillars.cubeList.add(new ModelBox(pillars, 16, 17, 14.0F, -15.0F, -7.0F, 2, 15, 2, 0.0F));
	pillars.cubeList.add(new ModelBox(pillars, 24, 17, 14.0F, -15.0F, 5.0F, 2, 15, 2, 0.0F));

	table = new ModelRenderer(this);
	table.setRotationPoint(0.0F, 24.0F, 0.0F);
	table.cubeList.add(new ModelBox(table, 0, 0, -17.0F, -16.0F, -8.0F, 34, 1, 16, 0.0F));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	pillars.render(f5);
	table.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
	modelRenderer.rotateAngleX = x;
	modelRenderer.rotateAngleY = y;
	modelRenderer.rotateAngleZ = z;
    }
}