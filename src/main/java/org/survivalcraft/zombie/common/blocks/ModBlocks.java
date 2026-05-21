package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;

public class ModBlocks {

    public static BlockBuildingBase brownBricks;
    public static BlockBuildingBase checkerboard;
    public static BlockBuildingBase road;
    public static BlockRoad roadWhiteLine;
    public static BlockRoad roadWhiteLineSide;
    public static BlockRoad roadOrangeLine;
    public static BlockRoad roadOrangeLineSide;

    public static BlockWaterPump waterPump;
    public static BlockWaterFountain waterFountain;
    public static BlockHazardBarrier hazardBarrier;
    public static BlockTable table;
    public static BlockWoodenChair woodenChair;
    public static BlockTrashcan trashcan;

    private static void init() {
	Logger.initInfo("- Registering Blocks");

	brownBricks = (BlockBuildingBase) new BlockBuildingBase("brownBricks", "brownBricks");
	checkerboard = (BlockBuildingBase) new BlockBuildingBase("checkerboard", "checkerboard");
	road = (BlockBuildingBase) new BlockBuildingBase("road", "road");
	roadWhiteLine = (BlockRoad) new BlockRoad("roadWhiteLine", "roadWhiteLine");
	roadWhiteLineSide = (BlockRoad) new BlockRoad("roadWhiteLineSide", "roadWhiteLineSide");
	roadOrangeLine = (BlockRoad) new BlockRoad("roadOrangeLine", "roadOrangeLine");
	roadOrangeLineSide = (BlockRoad) new BlockRoad("roadOrangeLineSide", "roadOrangeLineSide");

	waterPump = new BlockWaterPump();
	waterFountain = new BlockWaterFountain();
	hazardBarrier = new BlockHazardBarrier();
	table = new BlockTable();
	woodenChair = new BlockWoodenChair();
	trashcan = new BlockTrashcan();
    }

    public static void register() {
	init();

	registerBlock(brownBricks);
	registerBlock(checkerboard);
	registerBlock(road);
	registerBlock(roadWhiteLine);
	registerBlock(roadWhiteLineSide);
	registerBlock(roadOrangeLine);
	registerBlock(roadOrangeLineSide);
	registerBlock(waterPump);
	registerBlock(waterFountain);
	registerBlock(hazardBarrier);
	registerBlock(table);
	registerBlock(woodenChair);
	registerBlock(trashcan);
    }

    private static void registerBlock(Block block) {
	GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5).replace(".name", ""));
    }
}