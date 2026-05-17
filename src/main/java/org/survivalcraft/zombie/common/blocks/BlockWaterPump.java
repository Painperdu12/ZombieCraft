package org.survivalcraft.zombie.common.blocks;

import java.util.Random;

import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.common.items.ModItems;
import org.survivalcraft.zombie.common.tiles.props.TileEntityWaterPump;
import org.survivalcraft.zombie.utils.ChatHelper;
import org.survivalcraft.zombie.utils.PlayerHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWaterPump extends BlockProps {

	public BlockWaterPump() {
		this.setBlockName("waterPump");
		this.setBlockTextureName("zombiecraft:props/waterPump_icon");
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		this.setBlockBounds(0, 0, 0, 1, 2, 1);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityWaterPump();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if(rand.nextFloat() < 0.25f) {
			float fx = x + 0.57f;
			float fy = y + 1.35f;
			float fz = z + 0.65f;
			
			world.spawnParticle("dripWater", fx, fy, fz, 0.0D, 0.0D, 0.0D);
		}
		
		if(rand.nextInt(50) == 0) {
			world.playSound(x, y, z, "zombiecraft:block.waterPump.drip", 0.4f, 1f, false);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float subX, float subY, float subZ) {
		super.onBlockActivated(world, x, y, z, player, side, subX, subY, subZ);
		if(world.isRemote) return true;
		
		ItemStack heldItem = player.getHeldItem();
		
		if(heldItem == null) {
			world.playSoundAtEntity(player, "random.drink", 0.5f, 1.0f);
			ExtendedPlayerData.get(player).addWater(1);
			
			return true;
		} else if(heldItem.getItem() == Items.glass_bottle || heldItem.getItem() == ModItems.emptyBottle) {
			ChatHelper.sendMessage(player, "&7You have filled your empty Bottle with Water.");
			PlayerHelper.addSafeItem(player, new ItemStack(ModItems.waterBottle, 1));
			
			this.playUseSound(world, player);
			
			heldItem.stackSize--;
			if(heldItem.stackSize <= 0) PlayerHelper.removeHeldItem(player);
		
			return true;
		} else if(heldItem.getItem() == ModItems.dirtyBandage) {
			ChatHelper.sendMessage(player, "&7You have cleaned a dirty Bandage.");
			PlayerHelper.addSafeItem(player, new ItemStack(ModItems.bandage, 1));
			
			this.playUseSound(world, player);
			
			heldItem.stackSize--;
			if(heldItem.stackSize <= 0) PlayerHelper.removeHeldItem(player);
			
			return true;
		}
		
		return false; 
	}
	
	protected void playUseSound(World world, EntityPlayer player) {
		world.playSoundAtEntity(player, "zombiecraft:block.waterPump.use", 0.5f, 1.0f);
	}
	
	@Override
	public boolean hasTileEntity(int metadata) {
	    return true;
	}
}