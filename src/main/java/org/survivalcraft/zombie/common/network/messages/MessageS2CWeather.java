package org.survivalcraft.zombie.common.network.messages;

import org.survivalcraft.zombie.client.ClientVariables;
import org.survivalcraft.zombie.common.network.MessageBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageS2CWeather extends MessageBase<MessageS2CWeather> {

	public int newWeatherType;
	public float rainFogDensity;
	public float stormFogDensity;
	public float normalFogDensity;
	public float foggyFogDensity;
	public boolean enableFog;
	
	public MessageS2CWeather() { }
	
	public MessageS2CWeather(int type, float rain, float storm, float foggy, float normal, boolean enable) {
		this.newWeatherType = type;
		this.rainFogDensity = rain;
		this.stormFogDensity = storm;
		this.foggyFogDensity = foggy;
		this.normalFogDensity = normal;
		this.enableFog = enable;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.newWeatherType = buf.readInt();
		this.rainFogDensity = buf.readFloat();
		this.stormFogDensity = buf.readFloat();
		this.normalFogDensity = buf.readFloat();
		this.foggyFogDensity = buf.readFloat();
		this.enableFog = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.newWeatherType);
		buf.writeFloat(this.rainFogDensity);
		buf.writeFloat(this.stormFogDensity);
		buf.writeFloat(this.normalFogDensity);
		buf.writeFloat(this.foggyFogDensity);
		buf.writeBoolean(this.enableFog);
	}

	@Override
	public void handleClient(MessageS2CWeather message, EntityPlayer player) {
		ClientVariables.customFogEnabled = message.enableFog;
		ClientVariables.fogFoggyDensity = message.foggyFogDensity;
		ClientVariables.fogStormDensity = message.stormFogDensity;
		ClientVariables.fogRainDensity = message.rainFogDensity;
		ClientVariables.fogNormalDensity = message.normalFogDensity;
		ClientVariables.weatherTypeIndex = message.newWeatherType;
		
		System.err.println("Received weather packet from server...");
	}

	@Override
	public void handleServer(MessageS2CWeather message, EntityPlayer player) { }
}