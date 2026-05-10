package org.survivalcraft.zombie.client.events;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.survivalcraft.zombie.client.ClientReferences;
import org.survivalcraft.zombie.client.gui.GuiHelper;
import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.utils.ChatHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class EventRenderHandler extends Gui {
		
	@SubscribeEvent
	public void onRenderGameOverlay(RenderGameOverlayEvent event) {
		Minecraft minecraft = Minecraft.getMinecraft();
		ScaledResolution res = event.resolution;

		if(event.type == ElementType.FOOD) {
			this.renderWaterBar(res, minecraft, ExtendedPlayerData.get(minecraft.thePlayer));
		}
		
		if(event.type == ElementType.ALL) {
			this.renderPanicOverlay(res, minecraft);
			this.renderInformationsOverlay(res, minecraft);
		}
		
		if(event.type == ElementType.DEBUG) {
			if(!minecraft.thePlayer.capabilities.isCreativeMode) {
				ChatHelper.sendError(minecraft.thePlayer, "You can't open the debug screen while playing!");
				event.setCanceled(true);
			}
		}
	}
	
	private void renderWaterBar(ScaledResolution res, Minecraft minecraft, ExtendedPlayerData data) {		
		int left = (res.getScaledWidth() / 2) + 82;
		int top = res.getScaledHeight() - 69;
		
		int water = data.getWaterAmount();
		int maxWater = data.getMaxWaterAmount();
		
		for(int i = 0; i < 10; i++) {
			int index = i * 2 + 1; //Pour passer de 1 à 20 et pouvoir rendre les icones à moitier vides.
			int x = left - i * 8;
			int y = top + 20;

			GuiHelper.renderTexturedModalRect(x, y, 14, 0, 7, 9, 21, 9, ClientReferences.WATER_ICONS_TEX);
		
			if(index < water) GuiHelper.renderTexturedModalRect(x, y, 0, 0, 7, 9, 21, 9, ClientReferences.WATER_ICONS_TEX);
			else if(index == water) GuiHelper.renderTexturedModalRect(x, y, 7, 0, 7, 9, 21, 9, ClientReferences.WATER_ICONS_TEX);
		}
						
		minecraft.getTextureManager().bindTexture(Gui.icons);
	} 
	
	private void renderPanicOverlay(ScaledResolution res, Minecraft minecraft) {
		if(minecraft.thePlayer.getHealth() < 7 && !minecraft.thePlayer.capabilities.isCreativeMode) {
			GuiHelper.renderRect(0, 0, minecraft.displayWidth, minecraft.displayHeight, 255, 15, 15, 40);
		}
	}
	
	private void renderInformationsOverlay(ScaledResolution res, Minecraft minecraft) {
		FontRenderer font = minecraft.fontRenderer;
		ExtendedPlayerData data = ExtendedPlayerData.get(minecraft.thePlayer);
		
		if(minecraft.thePlayer.capabilities.isCreativeMode) {
			GuiHelper.drawCenteredString(font, "Creative Mode", res.getScaledWidth() / 2, 5, 0xFFFFFF, false);
		}
	}
}