package org.survivalcraft.zombie.common.items;

import java.util.List;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.ChatHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDirtyBandage extends Item {

    public ItemDirtyBandage() {
	this.setCreativeTab(TabsZombieCraft.ITEMS);
	this.setUnlocalizedName("dirtyBandage");
	this.setTextureName("zombiecraft:dirtyBandage");
    }

    @Override
    public void addInformation(ItemStack stacl, EntityPlayer player, List strings, boolean advanced) {
	strings.add(ChatHelper.getFormattedMessage("&7Can be cleaned using water"));
    }
}