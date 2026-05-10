package org.survivalcraft.zombie.common.network.messages;

import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.common.network.MessageBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageS2CPlayerData extends MessageBase<MessageS2CPlayerData> {

	public boolean legBroken;
	public boolean bleeding;
	public int waterAmount;
	public int deaths;
	public int daysPassed;
	public long lastHurtTime;
	
	public MessageS2CPlayerData() { }
	
	public MessageS2CPlayerData(ExtendedPlayerData data) {
		this.legBroken = data.isLegBroken();
		this.bleeding = data.isBleeding();
		this.waterAmount = data.getWaterAmount();
		this.deaths = data.getDeaths();
		this.daysPassed = data.getDaysPassed();
		this.lastHurtTime = data.getLastHurtTime();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.legBroken = buf.readBoolean();
		this.bleeding = buf.readBoolean();
		this.waterAmount = buf.readInt();
		this.deaths = buf.readInt();
		this.daysPassed = buf.readInt();
		this.lastHurtTime = buf.readLong();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.legBroken);
		buf.writeBoolean(this.bleeding);
		buf.writeInt(this.waterAmount);
		buf.writeInt(this.deaths);
		buf.writeInt(this.daysPassed);
		buf.writeLong(this.lastHurtTime);
	}

	@Override
	public void handleClient(MessageS2CPlayerData message, EntityPlayer player) {
		ExtendedPlayerData data = ExtendedPlayerData.get(player);	
		data.handleMesage(message);
	}

	@Override
	public void handleServer(MessageS2CPlayerData message, EntityPlayer player) { }
}