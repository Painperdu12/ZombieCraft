package org.survivalcraft.zombie.common.items;

import java.util.List;

import org.survivalcraft.zombie.common.TabsZombieCraft;
import org.survivalcraft.zombie.utils.ChatHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemEnergyBar extends ItemFood {
	
	private final int givenPotionId;
	
	public ItemEnergyBar(String name, int foodGiven, int givenPotionId) {
		super(foodGiven, false);
		this.givenPotionId = givenPotionId;
		
		this.setAlwaysEdible();
		this.setTextureName("zombiecraft:" + name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(TabsZombieCraft.ITEMS);
		this.setPotionEffect(givenPotionId, 8, 1, 1.0f);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List strings, boolean advaanced) {
		strings.add(ChatHelper.getFormattedMessage("&7Give &c" + I18n.format(Potion.potionTypes[this.givenPotionId].getName(), new Object[0]) + " II&7 for &c8 seconds"));
	}
}