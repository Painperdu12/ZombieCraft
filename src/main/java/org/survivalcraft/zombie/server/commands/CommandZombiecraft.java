package org.survivalcraft.zombie.server.commands;

import org.survivalcraft.zombie.common.ExtendedPlayerData;
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
		
	}
}