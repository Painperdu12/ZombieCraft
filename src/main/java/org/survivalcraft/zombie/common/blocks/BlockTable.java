package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.tiles.props.TileEntityTable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTable extends BlockProps {

	public BlockTable() {
		this.setBlockName("table");
		this.setBlockTextureName("zombiecraft:props/table_icon");
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityTable();
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
}