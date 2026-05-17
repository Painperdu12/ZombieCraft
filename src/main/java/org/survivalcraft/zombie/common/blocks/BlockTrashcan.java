package org.survivalcraft.zombie.common.blocks;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.common.tiles.props.TileEntityTrashcan;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTrashcan extends BlockContainer {

	/* TODO
	 *  - Ajouter un inventaire
	 *  - Ajouter la gestion du loot côté serveur (bind sur blocs avec commande)
	 */
	
	public BlockTrashcan() 
		super(Material.rock);
		this.setBlockTextureName("zombiecraft:props/trashcan_icon");
		this.setBlockName("trashcan");
		this.setResistance(4.5f);
		this.setHardness(12f);
		this.setCreativeTab(TabsZombieCraft.PROPS);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTrashcan();
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
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