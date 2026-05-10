package org.survivalcraft.zombie.common.items;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class ModItems {

	public static ItemBandage bandage;
	public static ItemDrinkable waterBottle;
	public static Item emptyBottle;
	public static ItemEnergyBar blueEnergyBar;
	public static ItemEnergyBar greenEnergyBar;
	public static ItemEnergyBar redEnergyBar;

	private static void init() {
		Logger.infoInit("- Registering items");

		bandage = new ItemBandage();		
		emptyBottle = new Item().setCreativeTab(TabsZombieCraft.ITEMS).setUnlocalizedName("emptyBottle").setTextureName("zombiecraft:emptyBottle");
		waterBottle = new ItemDrinkable("waterBottle", 5, emptyBottle);
		blueEnergyBar = new ItemEnergyBar("blueEnergyBar", 6, Potion.moveSpeed.id);
		greenEnergyBar = new ItemEnergyBar("greenEnergyBar", 6, Potion.resistance.id);
		redEnergyBar = new ItemEnergyBar("redEnergyBar", 6, Potion.fireResistance.id);
	}
	
	public static void register() {
		init();
		
		registerItem(bandage);
		registerItem(waterBottle);
		registerItem(emptyBottle);
		registerItem(blueEnergyBar);
		registerItem(greenEnergyBar);
		registerItem(redEnergyBar);
	}
	
	private static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5).replace(".name", ""));
	}
}