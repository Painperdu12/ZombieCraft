package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.TabsZombieCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockProps extends Block {

	public BlockProps() {
		super(Material.rock);
		this.setResistance(4.5f);
		this.setHardness(12f);
		this.setCreativeTab(TabsZombieCraft.PROPS);
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}