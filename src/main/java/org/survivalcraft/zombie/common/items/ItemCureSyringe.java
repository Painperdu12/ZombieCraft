package org.survivalcraft.zombie.common.items;

import java.util.List;

import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.ChatHelper;
import org.survivalcraft.zombie.utils.PlayerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCureSyringe extends Item {

    public ItemCureSyringe() {
	this.setTextureName("zombiecraft:cureSyringe");
	this.setUnlocalizedName("cureSyringe");
	this.setCreativeTab(TabsZombieCraft.ITEMS);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
	return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
	return EnumAction.bow;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
	player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
	world.playSoundAtEntity(player, "zombiecraft:item.syringe.use", 1f, 1f);

	return stack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
	ExtendedPlayerData data = ExtendedPlayerData.get(player);

	if(!world.isRemote) {
	    if(data.isInfected()) {
		data.setInfected(false);

		PlayerHelper.addSafeItem(player, new ItemStack(ModItems.emptySyringe, 1));

		stack.stackSize--;
		if(stack.stackSize <= 0)
		    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
	    }
	} else {
	    if(data.isInfected())
		ChatHelper.sendMessage(player, "&aYou cured your Infection!");
	    else
		ChatHelper.sendMessage(player, "&cYou aren't infected!");
	}

	return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List strings, boolean advanced) {
	strings.add(ChatHelper.getFormattedMessage("&cMedical Item - For Curing Infection"));
    }

}