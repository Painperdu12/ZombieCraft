package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.SharedConstants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBuildingBase extends Block {

    public BlockBuildingBase(String unlocalizedName, String textureName) {
	super(Material.rock);
	this.setHardness(12f);
	this.setResistance(4.5f);
	this.setCreativeTab(TabsZombieCraft.BLOCKS);
	this.setBlockTextureName(textureName);
	this.setBlockName(unlocalizedName);
    }

    @Override
    public Block setBlockTextureName(String name) {
	this.textureName = SharedConstants.MODID + ":" + name;
	return this;
    }

}