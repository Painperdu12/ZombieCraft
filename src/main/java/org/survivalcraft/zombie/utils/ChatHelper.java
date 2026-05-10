package org.survivalcraft.zombie.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChatHelper {

	public static final String MOD_PREFIX = "&7[&cZombieCraft&7] ";
	public static final String ERROR_PREFIX = "&4[&cError&4]&c ";
	
	public static String getFormattedMessage(String message) {
		return message.replace("&", "§");
	}
	
	public static IChatComponent getFormattedComponent(String message) {
		return new ChatComponentText(getFormattedMessage(message));
	}
	
	public static void sendMessage(EntityPlayer player, String message) {
		player.addChatMessage(new ChatComponentText(getFormattedMessage(message)));
	}
	
	public static void sendModMessage(EntityPlayer player, String message) {
		sendMessage(player, MOD_PREFIX + message);
	}
	
	public static void sendError(EntityPlayer player, String message) {
		sendMessage(player, ERROR_PREFIX + message);
	}
}