package org.survivalcraft.zombie.common.items;

import java.util.List;

import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.ChatHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBandage extends Item {

	public ItemBandage() {
		this.setUnlocalizedName("bandage");
		this.setTextureName("zombiecraft:bandage");
		this.setCreativeTab(TabsZombieCraft.ITEMS);
		this.setMaxStackSize(4);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List strings, boolean advanced) {
		strings.add(ChatHelper.getFormattedMessage("&cMedical Item - For Stopping Bleding"));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        //TODO Sound effect
        return stack;
    }
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		if(world.isRemote) {
			if(ExtendedPlayerData.get(player).isBleeding()) ChatHelper.sendMessage(player, "&aYou treated your wound.");
			else ChatHelper.sendMessage(player, "&cYou are not bleding!");
		} else {
			if(ExtendedPlayerData.get(player).isBleeding()) {
				stack.stackSize--;
				ExtendedPlayerData.get(player).setBleeding(false);
			}
		}
		
		return stack;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.block;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
}