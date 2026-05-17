package org.survivalcraft.zombie.common.network;

import org.survivalcraft.zombie.common.network.messages.MessageS2CPlayerData;
import org.survivalcraft.zombie.common.network.messages.MessageS2CWeather;
import org.survivalcraft.zombie.utils.SharedConstants;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;

public class NetworkHandler {

	private static SimpleNetworkWrapper INSTANCE;
	
	public static void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(SharedConstants.MODID);
		
		INSTANCE.registerMessage(MessageS2CPlayerData.class, MessageS2CPlayerData.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageS2CWeather.class, MessageS2CWeather.class, 1, Side.CLIENT);
	}
	
	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}
	
	public static void sendToPlayer(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}
	
	public static void sendToAll(IMessage message) {
		INSTANCE.sendToAll(message);
	}
}