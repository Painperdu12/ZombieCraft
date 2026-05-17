package org.survivalcraft.zombie.common.items.tools;

import java.util.List;
import java.util.Set;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.ChatHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;

public class ItemMeleeWeapon extends Item {

	private final int damages;
	
	public ItemMeleeWeapon(String name, int maxUses, int damages) {
		this.damages = damages;
		
		this.setFull3D();
		this.isDamageable();
		this.setMaxDamage(maxUses);
		this.setCreativeTab(TabsZombieCraft.ITEMS);
		this.setTextureName("zombiecraft:" + name);
		this.setUnlocalizedName(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase living, EntityLivingBase user) {
		if(user.worldObj.isRemote) return true;
		
		living.attackEntityFrom(DamageSource.generic, this.damages);
		stack.damageItem(1, user);
		
		return true;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List strings, boolean advanced) {
		strings.add(ChatHelper.getFormattedMessage("&7Melee Weapon"));
		strings.add(ChatHelper.getFormattedMessage("&7Damage &c" + this.damages));
	}
}