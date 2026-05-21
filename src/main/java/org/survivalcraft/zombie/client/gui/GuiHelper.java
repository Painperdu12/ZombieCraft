package org.survivalcraft.zombie.client.gui;

import java.awt.Color;

import static org.lwjgl.opengl.GL11.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiHelper {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void renderTexturedModalRect(double x, double y, double u, double v, double w, double h, double texWidth, double texHeight, ResourceLocation texture) {
	mc.getTextureManager().bindTexture(texture);

	double minU = u / texWidth;
	double minV = v / texHeight;
	double maxU = (u + w) / texWidth;
	double maxV = (v + h) / texHeight;

	Tessellator t = Tessellator.instance;
	t.startDrawingQuads();
	t.addVertexWithUV(x, y + h, 0.0, minU, maxV);
	t.addVertexWithUV(x + w, y + h, 0.0, maxU, maxV);
	t.addVertexWithUV(x + w, y, 0.0, maxU, minV);
	t.addVertexWithUV(x, y, 0.0, minU, minV);
	t.draw();
    }

    public static void renderGradientRect(double x, double y, double w, double h, Color from, Color to) {
	Tessellator t = Tessellator.instance;
	glEnable(GL_BLEND);
	glDisable(GL_TEXTURE_2D);
	glShadeModel(GL_SMOOTH);

	t.startDrawingQuads();
	t.setColorRGBA(from.getRed(), from.getGreen(), from.getBlue(), from.getAlpha());
	t.addVertex(x, y + h, 0);
	t.addVertex(x + w, y + h, 0);
	t.setColorRGBA(to.getRed(), to.getGreen(), to.getBlue(), to.getAlpha());
	t.addVertex(x + w, y, 0);
	t.addVertex(x, y, 0);
	t.draw();

	glEnable(GL_TEXTURE_2D);
	glDisable(GL_BLEND);
	glShadeModel(GL_FLAT);
	glColor4f(1, 1, 1, 1);
    }

    public static void renderRect(double x, double y, double w, double h, int r, int g, int b, int a) {
	glDisable(GL_TEXTURE_2D);
	glEnable(GL_BLEND);

	Tessellator t = Tessellator.instance;
	t.startDrawingQuads();
	t.setColorRGBA(r, g, b, a);
	t.addVertex(x, y + h, 0);
	t.addVertex(x + w, y + h, 0);
	t.addVertex(x + w, y, 0);
	t.addVertex(x, y, 0);
	t.draw();

	glDisable(GL_BLEND);
	glEnable(GL_TEXTURE_2D);
	glColor4f(1, 1, 1, 1);
    }

    public static void drawCenteredString(FontRenderer font, String string, int centeredX, int y, int color, boolean shadow) {
	int strWidth = font.getStringWidth(string);

	if(shadow)
	    font.drawStringWithShadow(string, centeredX - (strWidth / 2), y, color);
	else
	    font.drawString(string, centeredX - (strWidth / 2), y, color);

    }

    public static void drawStringWithOutline(FontRenderer font, String string, int x, int y, int color) {
	string = EnumChatFormatting.BOLD + string;

	font.drawString(string, x + 1, y, 0);
	font.drawString(string, x - 1, y, 0);
	font.drawString(string, x, y + 1, 0);
	font.drawString(string, x, y - 1, 0);
	font.drawString(string, x, y, color);
    }
}