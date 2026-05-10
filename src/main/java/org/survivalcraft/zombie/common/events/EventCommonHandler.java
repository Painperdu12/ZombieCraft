package org.survivalcraft.zombie.common.events;

import org.survivalcraft.zombie.common.ExtendedPlayerData;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EventCommonHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityEvent.EntityConstructing event) {
		if(event.entity instanceof EntityPlayer && ExtendedPlayerData.get((EntityPlayer)event.entity) == null) {
			ExtendedPlayerData.register((EntityPlayer)event.entity);
		}
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		EntityPlayer original = event.original;
		EntityPlayer latest = event.entityPlayer;
		
		ExtendedPlayerData.get(latest).copy(ExtendedPlayerData.get(original));
	}
	
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event) {
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entity;
			ExtendedPlayerData.get(player).onDeath();
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		ExtendedPlayerData data = ExtendedPlayerData.get(event.player);
		
		if(event.phase == TickEvent.Phase.START) {
			data.onUpdate();
		}
	}
	
	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event) {
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entity;
			ExtendedPlayerData.get(player).setHurted();
		}
	}
}