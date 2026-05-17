package org.survivalcraft.zombie.common.tiles;

import org.survivalcraft.zombie.common.tiles.props.TileEntityHazardBarrier;
import org.survivalcraft.zombie.common.tiles.props.TileEntityTable;
import org.survivalcraft.zombie.common.tiles.props.TileEntityTrashcan;
import org.survivalcraft.zombie.common.tiles.props.TileEntityWaterFountain;
import org.survivalcraft.zombie.common.tiles.props.TileEntityWaterPump;
import org.survivalcraft.zombie.common.tiles.props.TileEntityWoodenChair;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles {

	public static void register() {
		Logger.infoInit("- Registering TileEntities...");
		
		GameRegistry.registerTileEntity(TileEntityWaterPump.class, "waterPumpProps");
		GameRegistry.registerTileEntity(TileEntityWaterFountain.class, "waterFountainProps");
		GameRegistry.registerTileEntity(TileEntityHazardBarrier.class, "hazardBarrierProps");
		GameRegistry.registerTileEntity(TileEntityTable.class, "tableProps");
		GameRegistry.registerTileEntity(TileEntityWoodenChair.class, "woodenChairProps");
		
		GameRegistry.registerTileEntity(TileEntityTrashcan.class, "trashcan");
	}
}