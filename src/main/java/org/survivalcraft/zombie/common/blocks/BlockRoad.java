package org.survivalcraft.zombie.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockRoad extends BlockBuildingBase {

	private IIcon iconNorth;
	private IIcon iconEast;
	private IIcon iconSouth;
	private IIcon iconWest;
	
	public BlockRoad(String unlocalizedName, String textureName) {
		super(unlocalizedName, textureName);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		int dir = MathHelper.floor_double((double)(living.rotationYaw * 4.0f / 360.0f) + 0.5d) & 3; //Ok....
		int flagSendUpdateToClient = 2;
		world.setBlockMetadataWithNotify(x, y, z, dir, flagSendUpdateToClient);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
	    this.iconNorth = register.registerIcon(this.getTextureName() + "_north");
	    this.iconEast  = register.registerIcon(this.getTextureName() + "_east");
	    this.iconSouth = register.registerIcon(this.getTextureName() + "_south");
	    this.iconWest  = register.registerIcon(this.getTextureName() + "_west");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
	    switch(meta) {
	        case 0: return this.iconNorth;
	        case 1: return this.iconEast;
	        case 2: return this.iconSouth;
	        case 3: return this.iconWest;
	    }

	    return this.iconNorth;
	}
}