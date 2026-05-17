package org.survivalcraft.zombie.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import static org.lwjgl.opengl.GL11.*;

public class RenderItem implements IItemRenderer {

	private final Minecraft mc;
	private final ModelBase itemModel;
	private final ResourceLocation itemTextureLocation;
	
	public RenderItem(ModelBase itemModel, ResourceLocation itemTextureLocation) {
		this.mc = Minecraft.getMinecraft();
		this.itemModel = itemModel;
		this.itemTextureLocation = itemTextureLocation;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		glPushMatrix();
		
		this.mc.getTextureManager().bindTexture(itemTextureLocation);
		
		switch(type) {            
        	case EQUIPPED:
        		glScalef(1.0f, -1.0f, -1.0f);
        		glRotatef(-30.0f, 0, 0, 1);
        		glTranslatef(0.7f, -0.8f, 0.0f);
        		
        		break;
            
        	case ENTITY:
        		glScalef(1.6f, -1.6f, -1.6f);
        		glRotatef(90.0f, 1, 0, 0);
        		glTranslatef(0.0f, -1.1f, -0.1f);
        		
        		break;
        	default:
        		break;
		}
		
		this.itemModel.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
		glPopMatrix();
	}
}