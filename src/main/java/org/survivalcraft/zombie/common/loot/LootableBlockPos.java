package org.survivalcraft.zombie.common.loot;

import java.util.Objects;

public class LootableBlockPos {

    public final int x;
    public final int y;
    public final int z;

    public LootableBlockPos(int x, int y, int z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
	if(obj.getClass() != this.getClass()) return false;
        LootableBlockPos other = (LootableBlockPos)obj;	
	
	return other.x == this.x && other.y == y && other.z == z;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}