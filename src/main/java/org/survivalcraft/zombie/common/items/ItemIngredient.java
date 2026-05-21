package org.survivalcraft.zombie.common.items;

import java.util.List;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.ChatHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIngredient extends Item {

    private final String description;

    public ItemIngredient(String name, String description) {
	this.description = description;

	this.setCreativeTab(TabsZombieCraft.ITEMS);
	this.setTextureName("zombiecraft:ingredients/" + name);
	this.setUnlocalizedName(name);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List strings, boolean advanced) {
	if(!this.description.isEmpty())
	    strings.add(ChatHelper.getFormattedMessage("&7" + this.description));
    }
}