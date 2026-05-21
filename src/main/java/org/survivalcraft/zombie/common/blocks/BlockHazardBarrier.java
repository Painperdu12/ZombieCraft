package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.blocks.tiles.props.TileEntityHazardBarrier;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHazardBarrier extends BlockProps {

    public BlockHazardBarrier() {
	this.setBlockName("hazardBarrier");
	this.setBlockTextureName("zombiecraft:props/hazardBarrier_icon");
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
	this.setBlockBounds(0, 0, 0, 1, 0.65f, 1);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
	return new TileEntityHazardBarrier();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
	return true;
    }
}