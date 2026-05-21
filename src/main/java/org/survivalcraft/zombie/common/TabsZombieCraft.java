package org.survivalcraft.zombie.common;

import org.survivalcraft.zombie.common.blocks.ModBlocks;
import org.survivalcraft.zombie.common.items.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TabsZombieCraft {

    public static final CreativeTabs BLOCKS = new CreativeTabs("zombiecraftBlocks") {

	@Override
	public Item getTabIconItem() {
	    return Item.getItemFromBlock(ModBlocks.brownBricks);
	}
    };

    public static final CreativeTabs PROPS = new CreativeTabs("zombiecraftProps") {

	@Override
	public Item getTabIconItem() {
	    return Item.getItemFromBlock(ModBlocks.waterPump);
	}
    };

    public static final CreativeTabs ITEMS = new CreativeTabs("zombiecraftItems") {

	@Override
	public Item getTabIconItem() {
	    return ModItems.bandage;
	}
    };
}