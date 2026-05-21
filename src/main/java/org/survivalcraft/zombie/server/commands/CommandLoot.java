package org.survivalcraft.zombie.server.commands;

import org.survivalcraft.zombie.ZombieCraft;
import org.survivalcraft.zombie.common.loot.LootManager;
import org.survivalcraft.zombie.common.loot.LootableBlockPos;
import org.survivalcraft.zombie.server.ServerVariables;
import org.survivalcraft.zombie.utils.ChatHelper;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandLoot extends CommandBase {

    @Override
    public String getCommandName() {
	return "loot";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
	return "zombiecraft.command.loot.help";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
	if(!ServerVariables.enableLoot) {
	    sender.addChatMessage(ChatHelper.getFormattedComponent(ChatHelper.ERROR_PREFIX + "Looting is disabled in Server configuration!"));
	    return;
	}

	if(args.length <= 0) {
	    sender.addChatMessage(ChatHelper.getFormattedComponent("&cUse &4/loot help&c for more information."));
	    return;
	}

	if(args[0].equalsIgnoreCase("help")) {
	    sender.addChatMessage(ChatHelper.getFormattedComponent("------------ &aLooting Help&f ------------"));
	    sender.addChatMessage(ChatHelper.getFormattedComponent("&c/loot help &8- &7Sends this help message"));
	    sender.addChatMessage(ChatHelper.getFormattedComponent("&c/loot add <x,y,z>&8- &7Adds a lootable position"));
	    sender.addChatMessage(ChatHelper.getFormattedComponent("&c/loot remove <x,y,z> &8- &7Removes a lootable position"));
	    sender.addChatMessage(ChatHelper.getFormattedComponent("&c/loot list &8- &7Enumerates all lootables positions"));
	    sender.addChatMessage(ChatHelper.getFormattedComponent("&c/loot refill <x,y,z> &8- &7Refills a lootable position"));
	    sender.addChatMessage(ChatHelper.getFormattedComponent("&c/loot refillAll &8- &7Refills all lootables positions"));
	    sender.addChatMessage(ChatHelper.getFormattedComponent("-------------------------------------"));
	}

    }
}