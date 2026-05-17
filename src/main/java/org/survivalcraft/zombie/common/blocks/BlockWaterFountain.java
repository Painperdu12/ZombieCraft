package org.survivalcraft.zombie.common.blocks;

import java.util.Random;

import org.survivalcraft.zombie.common.tiles.props.TileEntityWaterFountain;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWaterFountain extends BlockWaterPump {

	public BlockWaterFountain() {
		this.setBlockName("waterFountain");
		this.setBlockTextureName("zombiecraft:props/waterFountain_icon");
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		this.setBlockBounds(0, 0, 0, 1, 1.25f, 1);
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) { 
		if(rand.nextFloat() < 0.05f) {
			world.playSound(x, y, z, "zombiecraft:block.waterPump.drip", 0.25f, 1f, false);
		}
	}
	
	@Override
	protected void playUseSound(World world, EntityPlayer player) {
		world.playSoundAtEntity(player, "zombiecraft:block.waterPump.sink", 1f, 1f);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityWaterFountain();
	}
}