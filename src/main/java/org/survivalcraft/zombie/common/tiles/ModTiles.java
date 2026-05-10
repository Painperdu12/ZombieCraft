package org.survivalcraft.zombie.common.tiles;

import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTiles {

	public static void registerTiles() {
		Logger.infoInit("- Registering TileEntities...");
		GameRegistry.registerTileEntity(TileEntityWaterPump.class, "waterPumpProps");
	}
}