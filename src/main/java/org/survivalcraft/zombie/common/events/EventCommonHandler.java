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

	private long lastWorldTime = 0l;
	
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
		EntityPlayer player = event.player;
		ExtendedPlayerData data = ExtendedPlayerData.get(player);
		
		if(event.phase == TickEvent.Phase.START) {
			data.onUpdate();	
		}
	}
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if(!event.world.isRemote && event.phase == TickEvent.Phase.START) {

			if(event.world.provider.dimensionId == 0) {
				long currentWorldTime = event.world.getWorldTime();

				if(lastWorldTime == -1) {
					lastWorldTime = currentWorldTime;
					return;
				}

				if(currentWorldTime < lastWorldTime || (currentWorldTime / 24000 > lastWorldTime / 24000)) {
					for(Object obj : event.world.playerEntities) {
						if(obj instanceof EntityPlayer) {
							EntityPlayer player = (EntityPlayer)obj;
							ExtendedPlayerData data = ExtendedPlayerData.get(player);

							if(data != null) {
								data.addDayPassed();
							}
						}
					}
				}

				this.lastWorldTime = currentWorldTime;
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event) {		
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entity;
			ExtendedPlayerData.get(player).setHurted();
			
			if(player.worldObj.rand.nextFloat() < 0.35f) {
				ExtendedPlayerData.get(player).subWater(player.worldObj.rand.nextInt(1) + 1);
			}
		}
	}
}