package org.survivalcraft.zombie.common.network.messages;

import java.util.HashMap;

import org.survivalcraft.zombie.common.ExtendedPlayerData;
import org.survivalcraft.zombie.common.loot.LootTable;
import org.survivalcraft.zombie.common.network.MessageBase;
import org.survivalcraft.zombie.utils.ChatHelper;
import org.survivalcraft.zombie.utils.Logger;
import org.survivalcraft.zombie.utils.PlayerHelper;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;

public class MessageS2CLooting extends MessageBase<MessageS2CLooting> {

    public InventoryBasic lootedInv;
    
    public MessageS2CLooting(InventoryBasic lootedInv) { 
	this.lootedInv = lootedInv;
    }
    
    public MessageS2CLooting() { }
    
    @Override
    public void fromBytes(ByteBuf buf) {
	int size = buf.readInt();
	
	this.lootedInv = new InventoryBasic("Looting", true, size);

	for(int i = 0; i < size; i++) {
	    int status = buf.readInt();
	    
	    if(status == 1) this.lootedInv.setInventorySlotContents(i, ByteBufUtils.readItemStack(buf));
	    else if(status == 0) this.lootedInv.setInventorySlotContents(i, null);
	}
    }

    @Override
    public void toBytes(ByteBuf buf) {
	buf.writeInt(this.lootedInv.getSizeInventory());
	
	for(int i = 0; i < this.lootedInv.getSizeInventory(); i++) {
	    if(this.lootedInv.getStackInSlot(i) != null) {
		buf.writeInt(1);
		ByteBufUtils.writeItemStack(buf, this.lootedInv.getStackInSlot(i)); 
	    } else buf.writeInt(0);
	}
    }

    @Override
    public void handleClient(MessageS2CLooting message, EntityPlayer player) {
	ExtendedPlayerData data = ExtendedPlayerData.get(player);
	data.setLootingInventory(message.lootedInv);
	
	Logger.debug("Opening looting menu for player " + player.getCommandSenderName());
    }

    @Override
    public void handleServer(MessageS2CLooting message, EntityPlayer player) {	}
}