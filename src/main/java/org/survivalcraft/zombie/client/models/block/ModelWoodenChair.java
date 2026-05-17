package org.survivalcraft.zombie.client.models.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWoodenChair extends ModelBase {

    public ModelRenderer supports;
    public ModelRenderer back;

    public ModelWoodenChair() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.supports = new ModelRenderer(this);
        this.supports.setRotationPoint(0.0F, 24.0F, 1.0F);

        this.supports.setTextureOffset(16, 16).addBox(-6.0F, -11.0F, -7.0F, 2, 11, 2);
        this.supports.setTextureOffset(24, 16).addBox(4.0F, -11.0F, -7.0F, 2, 11, 2);
        this.supports.setTextureOffset(16, 29).addBox(4.0F, -11.0F, 3.0F, 2, 11, 2);
        this.supports.setTextureOffset(24, 29).addBox(-6.0F, -11.0F, 3.0F, 2, 11, 2);
        
        this.supports.setTextureOffset(0, 0).addBox(-7.0F, -13.0F, -8.0F, 14, 2, 14);

        this.back = new ModelRenderer(this);
        this.back.setRotationPoint(0.0F, 24.0F, 1.0F);

        this.back.setTextureOffset(0, 16).addBox(-6.0F, -26.0F, 3.0F, 2, 14, 2);
        this.back.setTextureOffset(8, 16).addBox(4.0F, -26.0F, 3.0F, 2, 14, 2);
        
        this.back.setTextureOffset(32, 26).addBox(-2.0F, -27.0F, 4.0F, 1, 14, 1);
        this.back.setTextureOffset(36, 26).addBox(1.0F, -27.0F, 4.0F, 1, 14, 1);
        
        this.back.setTextureOffset(0, 32).addBox(-7.0F, -27.0F, 2.0F, 4, 1, 4);
        this.back.setTextureOffset(0, 37).addBox(-4.0F, -28.0F, 2.0F, 3, 1, 4);
        this.back.setTextureOffset(32, 16).addBox(3.0F, -27.0F, 2.0F, 4, 1, 4);
        this.back.setTextureOffset(40, 26).addBox(1.0F, -28.0F, 2.0F, 3, 1, 4);
        this.back.setTextureOffset(32, 21).addBox(-2.0F, -29.0F, 2.0F, 4, 1, 4);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.supports.render(f5);
        this.back.render(f5);
    }
}