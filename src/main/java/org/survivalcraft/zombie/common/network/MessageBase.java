package org.survivalcraft.zombie.common.network;

import org.survivalcraft.zombie.ZombieCraft;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public abstract class MessageBase<E extends IMessage> implements IMessage, IMessageHandler<E, E> {
	
	@Override
	public E onMessage(E message, MessageContext ctx) {
		if(ctx.side == Side.SERVER) handleServer(message, ctx.getServerHandler().playerEntity);
		else handleClient(message, ZombieCraft.instance().getClientProxy().getClientPlayer());
		
		return null;
	} 
	
	public abstract void handleClient(E message, EntityPlayer player);
	
	public abstract void handleServer(E message, EntityPlayer player);	
	
}