package org.survivalcraft.zombie.common.items;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.common.items.tools.ItemMeleeWeapon;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

public class ModItems {

	public static ItemBandage bandage;
	public static ItemDirtyBandage dirtyBandage;
	public static ItemInfectedSyringe infectedSyringe;
	public static ItemCureSyringe cureSyringe;
	public static Item emptySyringe;
	
	public static ItemDrinkable waterBottle;
	public static ItemDrinkable almondWaterBottle;
	public static ItemDrinkable cokeBottle;
	public static ItemDrinkable limeadBottle;
	public static ItemDrinkable cherryadeBottle;
	public static Item emptyBottle;
	
	public static ItemFoodCan cannedApple;
	public static ItemFoodCan cannedLemon;
	public static ItemFoodCan cannedCheese;
	public static ItemFoodCan cannedCarrot;
	public static ItemFoodCan cannedPork;
	public static ItemFoodCan cannedChocolate;
	public static ItemFoodCan cannedRottenFlesh;
	public static ItemFoodCan cannedApricot;
	public static ItemFoodCan cannedCherries;
	public static ItemFoodCan cannedKiwi;
	public static ItemFoodCan cannedInsects;
	public static Item emptyCan;
	
	public static ItemEnergyBar blueEnergyBar;
	public static ItemEnergyBar greenEnergyBar;
	public static ItemEnergyBar redEnergyBar;
	
	public static ItemMeleeWeapon fireAxe;

	private static void init() {
		Logger.infoInit("- Registering items");

		bandage = new ItemBandage();		
		dirtyBandage = new ItemDirtyBandage();
		emptySyringe = new Item().setCreativeTab(TabsZombieCraft.ITEMS).setUnlocalizedName("emptySyringe").setTextureName("zombiecraft:emptySyringe");
		infectedSyringe = new ItemInfectedSyringe();
		cureSyringe = new ItemCureSyringe();
		
		emptyBottle = new Item().setCreativeTab(TabsZombieCraft.ITEMS).setUnlocalizedName("emptyBottle").setTextureName("zombiecraft:emptyBottle");
		waterBottle = new ItemDrinkable("waterBottle", 5, emptyBottle);
		almondWaterBottle = new ItemDrinkable("almondWaterBottle", 6, emptyBottle);
		cokeBottle = new ItemDrinkable("cokeBottle", 3, emptyBottle);
		limeadBottle = new ItemDrinkable("limeadBottle", 6, emptyBottle);
		cherryadeBottle = new ItemDrinkable("cherryadeBottle", 5, emptyBottle);
		
		emptyCan = new Item().setCreativeTab(TabsZombieCraft.ITEMS).setUnlocalizedName("emptyCan").setTextureName("zombiecraft:emptyCan");
		cannedApple = new ItemFoodCan("cannedApple", 4, 2.5f, emptyCan);
		cannedLemon = new ItemFoodCan("cannedLemon", 5, 3, emptyCan);
		cannedCheese = new ItemFoodCan("cannedCheese", 4, 7, emptyCan);
		cannedCarrot = new ItemFoodCan("cannedCarrot", 4, 2, emptyCan);
		cannedPork = new ItemFoodCan("cannedPork", 7, 7, emptyCan);
		cannedChocolate = new ItemFoodCan("cannedChocolate", 5, 8.5f, emptyCan);
		cannedRottenFlesh = new ItemFoodCan("cannedRottenFlesh", 6, 3, emptyCan, true);
		cannedApricot = new ItemFoodCan("cannedApricot", 5, 4.5f, emptyCan);
		cannedCherries = new ItemFoodCan("cannedCherries", 5, 3, emptyCan);
		cannedKiwi = new ItemFoodCan("cannedKiwi", 5, 3.5f, emptyCan);
		cannedInsects = new ItemFoodCan("cannedInsects", 6, 10f, emptyCan, true);
		
		blueEnergyBar = new ItemEnergyBar("blueEnergyBar", 6, Potion.moveSpeed.id);
		greenEnergyBar = new ItemEnergyBar("greenEnergyBar", 6, Potion.resistance.id);
		redEnergyBar = new ItemEnergyBar("redEnergyBar", 6, Potion.fireResistance.id);
		
		fireAxe = new ItemMeleeWeapon("fireAxe", 48, 5);
	}
	
	public static void register() {
		init();
		
		registerItem(bandage);
		registerItem(dirtyBandage);
		registerItem(emptySyringe);
		registerItem(infectedSyringe);
		registerItem(cureSyringe);
		registerItem(waterBottle);
		registerItem(almondWaterBottle);
		registerItem(cokeBottle);
		registerItem(limeadBottle);
		registerItem(cherryadeBottle);
		registerItem(emptyBottle);
		registerItem(emptyCan);
		registerItem(cannedApple);
		registerItem(cannedLemon);
		registerItem(cannedCheese);
		registerItem(cannedCarrot);
		registerItem(cannedPork);
		registerItem(cannedChocolate);
		registerItem(cannedRottenFlesh);
		registerItem(cannedApricot);
		registerItem(cannedCherries);
		registerItem(cannedKiwi);
		registerItem(cannedInsects);
		registerItem(blueEnergyBar);
		registerItem(greenEnergyBar);
		registerItem(redEnergyBar);
		registerItem(fireAxe);
	}
	
	private static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5).replace(".name", ""));
	}
}