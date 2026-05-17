package org.survivalcraft.zombie.server.events;

import org.survivalcraft.zombie.common.EnumWeatherType;
import org.survivalcraft.zombie.common.network.NetworkHandler;
import org.survivalcraft.zombie.common.network.messages.MessageS2CWeather;
import org.survivalcraft.zombie.server.ServerConfiguration;
import org.survivalcraft.zombie.server.ServerVariables;
import org.survivalcraft.zombie.server.commands.CommandZombiecraft;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.command.CommandHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class EventServerHandler {

	private int weatherCheckCounter = 0;
	
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if(event.player instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)event.player;
			
			float normalDensity = ServerVariables.customFogNormalDensity;
			float rainDensity = ServerVariables.customFogRainDensity;
			float stormDensity = ServerVariables.customFogStormDensity;
			float foggyDensity = ServerVariables.customFogFoggyDensity;
						
			MessageS2CWeather weatherMessage = new MessageS2CWeather(ServerVariables.weatherTypeIndex, rainDensity, stormDensity, foggyDensity, normalDensity, ServerVariables.enableWeather);
			NetworkHandler.sendToPlayer(weatherMessage, player);
		}
	}
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
		if(event.phase == TickEvent.Phase.END) {
			if(ServerVariables.enableWeather) {
				if(this.weatherCheckCounter <= 0) {
					this.weatherCheckCounter = 20 * 120; //2 minutes
					
					if(Math.random() < 0.1) { //10% de chances
						if(Math.random() < 0.1d) this.changeWeatherTo(EnumWeatherType.STORM);
						else if(Math.random() < 0.3d) this.changeWeatherTo(EnumWeatherType.FOGGY);
						else if(Math.random() < 0.7d) this.changeWeatherTo(EnumWeatherType.RAIN);
						else if(Math.random() < 0.9d) this.changeWeatherTo(EnumWeatherType.NORMAL);					
					}
				} else this.weatherCheckCounter--;
			}
		}
	}
	
	private void changeWeatherTo(EnumWeatherType type) {
		Logger.info("Changing weather to " + type.name + " (" + type.index + ").");
		MessageS2CWeather message = new MessageS2CWeather(type.index, ServerVariables.customFogRainDensity, ServerVariables.customFogStormDensity, ServerVariables.customFogFoggyDensity, ServerVariables.customFogNormalDensity, ServerVariables.enableWeather);
		NetworkHandler.sendToAll(message);
	} 
}