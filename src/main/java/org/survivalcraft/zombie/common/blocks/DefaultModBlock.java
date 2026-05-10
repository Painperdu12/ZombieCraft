package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.utils.SharedConstants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DefaultModBlock extends Block {

	public DefaultModBlock() {
		super(Material.rock);
	}
	
	@Override
	public Block setBlockTextureName(String name) {
		this.textureName = SharedConstants.MODID + ":" + name;
		return this;
	}
	
}