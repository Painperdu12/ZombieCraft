package org.survivalcraft.zombie.common.loot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.survivalcraft.zombie.common.blocks.ModBlocks;
import org.survivalcraft.zombie.common.items.ModItems;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class LootTable {

    public static final HashMap<Block, List<ItemStack>> blockByLootMapping = new HashMap<>();

    public static void initLootTables() {
	Logger.initInfo("Loading Loot tables...");

	List<ItemStack> commonLoot = new ArrayList<>();
	commonLoot.add(new ItemStack(ModItems.cannedApple, 3));
	commonLoot.add(new ItemStack(ModItems.cannedApricot, 3));
	commonLoot.add(new ItemStack(ModItems.cannedCarrot, 2));
	commonLoot.add(new ItemStack(ModItems.cannedCheese, 1));
	commonLoot.add(new ItemStack(ModItems.cannedCherries, 2));
	commonLoot.add(new ItemStack(ModItems.cannedChocolate, 1));
	commonLoot.add(new ItemStack(ModItems.cannedInsects, 3));
	commonLoot.add(new ItemStack(ModItems.cannedKiwi, 2));
	commonLoot.add(new ItemStack(ModItems.cannedLemon, 2));
	commonLoot.add(new ItemStack(ModItems.cannedPork, 1));
	commonLoot.add(new ItemStack(ModItems.cannedRottenFlesh, 3));
	commonLoot.add(new ItemStack(ModItems.emptyBottle, 2));
	commonLoot.add(new ItemStack(ModItems.almondWaterBottle, 1));
	commonLoot.add(new ItemStack(ModItems.cokeBottle, 2));
	commonLoot.add(new ItemStack(ModItems.cherryadeBottle, 2));
	commonLoot.add(new ItemStack(ModItems.limeadBottle, 1));
	commonLoot.add(new ItemStack(ModItems.waterBottle, 3));
	commonLoot.add(new ItemStack(ModItems.wires, 2));
	commonLoot.add(new ItemStack(Items.rotten_flesh, 6));
	commonLoot.add(new ItemStack(Items.apple, 3));
	
	List<ItemStack> trashcanLoot = new ArrayList<>();
	trashcanLoot.add(new ItemStack(Items.paper, 2));
	trashcanLoot.add(new ItemStack(ModItems.wires, 4));

	blockByLootMapping.put(ModBlocks.trashcan, trashcanLoot);
	blockByLootMapping.put(Blocks.chest, commonLoot);
    }

    public static void generateFor(InventoryBasic inv, Block block, int maxItems, int size) {
	if(!blockByLootMapping.containsKey(block)) return;

	List<ItemStack> stacks = blockByLootMapping.get(block);
	Random rand = new Random();

	for(int i = 0; i < maxItems; i++) {
	    if(rand.nextInt(maxItems) == 0) break;
	    
	    do {
	    } while(!tryPutStackInSlot(inv, stacks, rand, size));
	}
    }

    private static boolean tryPutStackInSlot(InventoryBasic inv, List<ItemStack> stacks, Random rand, int size) {
	int slot = rand.nextInt(size);
	if(inv.getStackInSlot(slot) != null) return false;

	ItemStack stack = stacks.get(rand.nextInt(stacks.size())).copy();
	int maxStackSize = stack.stackSize;
	int stackSize = rand.nextInt(maxStackSize) + 1;

	inv.setInventorySlotContents(slot, new ItemStack(stack.getItem(), stackSize));
	return true;
    }
}