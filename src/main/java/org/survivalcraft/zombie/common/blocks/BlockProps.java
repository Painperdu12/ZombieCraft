package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.TabsZombieCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockProps extends Block {

    public BlockProps() {
	super(Material.rock);
	this.setResistance(4.5f);
	this.setHardness(12f);
	this.setCreativeTab(TabsZombieCraft.PROPS);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
	int dir = MathHelper.floor_double((entity.rotationYaw * 4.0f / 360.0f) + 0.5d) & 3;
	world.setBlockMetadataWithNotify(x, y, z, dir, 2 /* Flag pour mettre à jour sur le client un fois posé. */);
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