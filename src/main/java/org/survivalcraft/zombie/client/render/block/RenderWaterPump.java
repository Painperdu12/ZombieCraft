package org.survivalcraft.zombie.client.render.block;

import org.survivalcraft.zombie.client.models.block.ModelWaterPump;
import org.survivalcraft.zombie.utils.SharedConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import static org.lwjgl.opengl.GL11.*;

public class RenderWaterPump extends TileEntitySpecialRenderer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(SharedConstants.MODID, "textures/blocks/props/waterPump.png");
	private final ModelWaterPump waterPumpModel;
	
	public RenderWaterPump() {
		this.waterPumpModel = new ModelWaterPump();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float pTicks) {
		glPushMatrix();
		glTranslated(x + 0.5d, y + 1.5d, z + 0.5d);
		glRotatef(180.0f, 1, 0, 0);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		this.waterPumpModel.render(null, 0, 0, 0, 0, 0, 0.0625f);
		
		glPopMatrix();
	}
}