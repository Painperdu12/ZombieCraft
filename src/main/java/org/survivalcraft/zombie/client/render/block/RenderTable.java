package org.survivalcraft.zombie.client.render.block;

import org.survivalcraft.zombie.client.models.block.ModelTable;
import org.survivalcraft.zombie.utils.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import static org.lwjgl.opengl.GL11.*;

public class RenderTable extends TileEntitySpecialRenderer {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SharedConstants.MODID, "textures/blocks/props/table.png");
    private final ModelTable tableModel;

    public RenderTable() {
	this.tableModel = new ModelTable();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float pTicks) {
	int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);

	glPushMatrix();
	glTranslated(x + 0.5d, y + 1.5d, z + 0.5d);

	glRotatef(180.0F, 1, 0, 0);
	glRotatef(meta * 90.0F, 0, 1, 0); // Rotation Y (0-3 -> 0°, 90°, 180°, 270°)

	Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
	this.tableModel.render(null, 0, 0, 0, 0, 0, 0.0625f);

	glPopMatrix();
    }
}