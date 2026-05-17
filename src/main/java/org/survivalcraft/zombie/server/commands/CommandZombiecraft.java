package org.survivalcraft.zombie.server.commands;

import org.survivalcraft.zombie.common.EnumWeatherType;
import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.common.network.NetworkHandler;
import org.survivalcraft.zombie.common.network.messages.MessageS2CWeather;
import org.survivalcraft.zombie.server.ServerConfiguration;
import org.survivalcraft.zombie.server.ServerVariables;
import org.survivalcraft.zombie.utils.ChatHelper;
import org.survivalcraft.zombie.utils.SharedConstants;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandZombiecraft extends CommandBase {

	@Override
	public String getCommandName() {
		return "zombiecraft";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "zombiecraft.command.help";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length <= 0) {
			sender.addChatMessage(ChatHelper.getFormattedComponent("&cUse &4/zombiecraft help&c for more informations."));
			return;
		}
		
		if(args[0].equalsIgnoreCase("help")) {
			sender.addChatMessage(ChatHelper.getFormattedComponent("------------ &aZombieCraft Help&f ------------"));
			sender.addChatMessage(ChatHelper.getFormattedComponent("&c/zombiecraft help &8- &7Shows this help message."));
			sender.addChatMessage(ChatHelper.getFormattedComponent("&c/zombiecraft version &8- &7Returns current mod version."));
			sender.addChatMessage(ChatHelper.getFormattedComponent("&c/zombiecraft water get <player> &8- &7Returns player water amount."));
			sender.addChatMessage(ChatHelper.getFormattedComponent("&c/zombiecraft water set <player> <amount> &8- &7Sets the player amount of water."));
			sender.addChatMessage(ChatHelper.getFormattedComponent("&c/zombiecraft weather get &8- &7Returns the current weather."));
			sender.addChatMessage(ChatHelper.getFormattedComponent("&c/zombiecraft weather set <0|1|2|3> &8- &7Sets the weather."));
			sender.addChatMessage(ChatHelper.getFormattedComponent("----------------------------------------"));
			
			return;
		}
		
		if(args[0].equalsIgnoreCase("version")) {
			sender.addChatMessage(ChatHelper.getFormattedComponent(ChatHelper.MOD_PREFIX + "ZombieCraft version " + SharedConstants.MOD_VERSION));
			return;
		}
		
		if(args[0].equalsIgnoreCase("water")) {
			if(args.length < 2) {
				sender.addChatMessage(ChatHelper.getFormattedComponent("&cUse &4/zombiecraft help&c for more informations."));
				return;
			}
			
			if(args[1].equalsIgnoreCase("get")) {
				if(args.length < 3) {
					sender.addChatMessage(ChatHelper.getFormattedComponent("&cUse &4/zombiecraft help&c for more informations."));
					return;
				}
				
				EntityPlayer targetPlayer = this.getPlayer(sender, args[2]);
				int waterAmount = ExtendedPlayerData.get(targetPlayer).getWaterAmount();
				
				sender.addChatMessage(ChatHelper.getFormattedComponent(ChatHelper.MOD_PREFIX + "&c" + targetPlayer.getCommandSenderName() + "&7 water level is &c" + waterAmount + "&7/&c20&7."));
			}
			
			if(args[1].equalsIgnoreCase("set")) {
				if(args.length < 4) {
					sender.addChatMessage(ChatHelper.getFormattedComponent("&cUse &4/zombiecraft help&c for more informations."));
					return;
				}
				
				EntityPlayer targetPlayer = getPlayer(sender, args[2]);
				int waterAmount = parseInt(sender, args[3]);
				
				ExtendedPlayerData.get(targetPlayer).setWater(waterAmount);
				sender.addChatMessage(ChatHelper.getFormattedComponent(ChatHelper.MOD_PREFIX + "You setted &c" + targetPlayer.getCommandSenderName() + "&7 water level to &c" + waterAmount + "&7/&c20&7."));
			}
		}
		
		if(args[0].equalsIgnoreCase("weather")) {
			if(args.length < 2) {
				sender.addChatMessage(ChatHelper.getFormattedComponent("&cUse &4/zombiecraft help&c for more informations."));
				return;
			}
			
			if(args[1].equalsIgnoreCase("get")) {
				EnumWeatherType type = EnumWeatherType.values()[ServerVariables.weatherTypeIndex];
				
				sender.addChatMessage(ChatHelper.getFormattedComponent(ChatHelper.MOD_PREFIX + "The weather is actualy &c" + type.name + " &8(&7" + type.index  + "&8)&7."));
			}
			
			if(args[1].equalsIgnoreCase("set")) {
				if(args.length < 3) {
					sender.addChatMessage(ChatHelper.getFormattedComponent("&cUse &4/zombiecraft help&c for more informations."));
					return;
				}
				
				String typeName = args[2];
				EnumWeatherType type = EnumWeatherType.valueOf(typeName.toUpperCase());
				float normalDensity = ServerVariables.customFogNormalDensity;
				float rainDensity = ServerVariables.customFogRainDensity;
				float stormDensity = ServerVariables.customFogStormDensity;
				float foggyDensity = ServerVariables.customFogFoggyDensity;
				
				ServerVariables.weatherTypeIndex = type.index;
				MessageS2CWeather message = new MessageS2CWeather(type.index, rainDensity, stormDensity, foggyDensity, normalDensity, ServerVariables.enableWeather);
				NetworkHandler.sendToAll(message);				
				
				sender.addChatMessage(ChatHelper.getFormattedComponent(ChatHelper.MOD_PREFIX + "Changing the weather to &c" + type.name + " &8(&7" + type.index + "&8)&7."));
			}
		}
		
	}
}