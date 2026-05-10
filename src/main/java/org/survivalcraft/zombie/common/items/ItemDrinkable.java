package org.survivalcraft.zombie.common.items;

import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.common.TabsZombieCraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDrinkable extends Item {

	private final int waterGiven;
	private final Item returnedItem;
	
	public ItemDrinkable(String name, int waterGiven, Item returnedItem) {
		this.waterGiven = waterGiven;
		this.returnedItem = returnedItem;
		this.setTextureName("zombiecraft:" + name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(TabsZombieCraft.ITEMS);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(ExtendedPlayerData.get(player).canDrink()) {
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			world.playSoundAtEntity(player, "zombiecraft:item.can.open", 0.3f, 1f);
		}
		
		return stack;
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			stack.stackSize--;
			player.inventory.addItemStackToInventory(new ItemStack(this.returnedItem, 1));
			
			ExtendedPlayerData.get(player).addWater(this.waterGiven);
		}
		
		return stack;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.drink;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
}