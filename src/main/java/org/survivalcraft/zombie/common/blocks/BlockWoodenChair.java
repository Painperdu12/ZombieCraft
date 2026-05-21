package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.blocks.tiles.props.TileEntityWoodenChair;
import org.survivalcraft.zombie.common.entities.EntitySeat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWoodenChair extends BlockProps {

    public BlockWoodenChair() {
	this.setBlockName("woodenChair");
	this.setBlockTextureName("zombiecraft:props/woodenChair_icon");
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
	return new TileEntityWoodenChair();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
	return true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX,
	    float subY, float subZ) {
	super.onBlockActivated(world, x, y, z, player, side, subX, subY, subZ);
	if(world.isRemote)
	    return true;

	EntitySeat.sitOnBlock(world, player, x, y, z, 0.8d);

	return true;
    }
}