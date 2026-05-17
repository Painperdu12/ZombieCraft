package org.survivalcraft.zombie.common.items;

import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.PlayerHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFoodCan extends Item {

	private final int foodGiven;
	private final float saturationGiven;
	private final Item itemGiven;
	private final boolean shouldGivePoison;
	
	public ItemFoodCan(String name, int foodGiven, float saturationGiven, Item itemGiven, boolean shouldGivePoison) {
		this.foodGiven = foodGiven;
		this.saturationGiven = saturationGiven;
		this.itemGiven = itemGiven;
		this.shouldGivePoison = shouldGivePoison;
		
		this.setCreativeTab(TabsZombieCraft.ITEMS);
		this.setTextureName("zombiecraft:" + name);
		this.setUnlocalizedName(name);
	}
	
	public ItemFoodCan(String name, int foodGiven, float saturationGiven, Item itemGiven) {
		this(name, foodGiven, saturationGiven, itemGiven, false);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(player.getFoodStats().needFood()) {
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			world.playSoundAtEntity(player, "zombiecraft:item.can.open", 0.3f, 1f);
		}
		
		return stack;
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			stack.stackSize--;
			player.inventory.addItemStackToInventory(new ItemStack(this.itemGiven, 1));
			
			PlayerHelper.addFood(player, this.foodGiven, this.saturationGiven);
			
			if(this.shouldGivePoison) {
				player.addPotionEffect(new PotionEffect(Potion.poison.getId(), 20 * 8, 1, true));
				world.playSoundAtEntity(player, "zombiecraft:player.cough", 4f, 1f);
			}
		}
		
		return stack;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.eat;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
}