package org.survivalcraft.zombie.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryDummy implements IInventory {

    public ItemStack[] contents;
    public EntityPlayer player;
    public String name;
    public int size;
    
    public InventoryDummy(EntityPlayer player, String name, int size) {
	this.contents = new ItemStack[size];
	this.player = player;
	this.name = name;
	this.size = size;
    }
    
    @Override
    public int getSizeInventory() {
	return this.size;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
	return slot >= 0 && slot < this.size ? this.contents[slot] : null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int decr) {
	if(this.contents[slot] != null) {
	    if(this.contents[slot].stackSize <= decr) {
		ItemStack stack = this.contents[slot];
		this.contents[slot] = null;
		
		this.markDirty();
		return stack;
	    } else {
		ItemStack stack = this.contents[slot].splitStack(decr);
		
		if(this.contents[slot].stackSize <= 0) this.contents[slot] = null;
		
		this.markDirty();
		return stack;
	    }
	} else return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
	return this.getStackInSlot(slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
	this.contents[slot] = stack;
	
	if(stack != null && stack.stackSize > this.getInventoryStackLimit()) stack.stackSize = this.getInventoryStackLimit();
	this.markDirty();
    }

    @Override
    public String getInventoryName() {
	return this.name;
    }

    @Override
    public boolean hasCustomInventoryName() {
	return false;
    }

    @Override
    public int getInventoryStackLimit() {
	return 64;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
	return true;
    }    
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
	return true;
    }

    @Override
    public void markDirty() { }

    @Override
    public void openInventory() { }

    @Override
    public void closeInventory() { }
}