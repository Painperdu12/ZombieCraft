package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {

	public static DefaultModBlock brownBricks;
	public static BlockWaterPump waterPump;
	
	private static void init() {
		Logger.infoInit("- Registering blocks");

		brownBricks = (DefaultModBlock)new DefaultModBlock().setBlockName("BrownBricks").setBlockTextureName("BrownBricks").setCreativeTab(TabsZombieCraft.BLOCKS);
		waterPump = new BlockWaterPump();
	}
	
	public static void register() {
		init();
		
		registerBlock(brownBricks);
		registerBlock(waterPump);
	}
	
	private static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5).replace(".name", ""));
	}
}