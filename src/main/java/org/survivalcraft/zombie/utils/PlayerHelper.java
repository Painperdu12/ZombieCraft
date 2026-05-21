package org.survivalcraft.zombie.utils;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class PlayerHelper {

    public static void addSafeItem(EntityPlayer player, ItemStack stack) {
	if(!player.inventory.addItemStackToInventory(stack)) {
	    spawnItemAtPlayer(player, stack);
	} else if(player instanceof EntityPlayerMP) {
	    ((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
	}
    }

    public static void addItem(EntityPlayer player, ItemStack stack) {
	if(player instanceof EntityPlayerMP) {
	    player.inventory.addItemStackToInventory(stack);
	    ((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
	}
    }

    public static void spawnItemAtPlayer(EntityPlayer player, ItemStack stack) {
	EntityItem itemEntity = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, stack);
	player.worldObj.spawnEntityInWorld(itemEntity);
    }

    public static void spawnItemListAtPlayer(EntityPlayer player, List<ItemStack> stacks) {
	for(ItemStack is : stacks)
	    spawnItemAtPlayer(player, is);
    }

    public static boolean isInvetoryFull(EntityPlayer player, ItemStack stack) {
	return player.inventory.getFirstEmptyStack() == -1;
    }

    public static int getFirstFreeSlot(EntityPlayer player, ItemStack stack) {
	return player.inventory.getFirstEmptyStack();
    }

    public static boolean hasItem(EntityPlayer player, ItemStack stack) {
	return player.inventory.hasItemStack(stack);
    }

    public static void removeHeldItem(EntityPlayer player) {
	player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    }

    public static void addFood(EntityPlayer player, int food, float saturation) {
	player.getFoodStats().addStats(food, saturation);
    }
}