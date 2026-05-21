package org.survivalcraft.zombie.common.loot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.survivalcraft.zombie.server.ServerVariables;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class LootManager {

    private final Map<LootableBlockPos, Long> reloadingPositions;
    private int reloadingTime;
    
    /* TODO
     *  - Ajouter un GUI avec Container & Inventory pour l'iventaire du joueur personnalisé.
     *  - Ajouter en conséquence un GuiHandler.
     *  - Dans l'inventaire du joueur personnalisé, ne pas oublier d'ajouter des types d'inventaire (0 pour normal, 1 pour loot) à partir des coordonnées entrées en paramètres lors de l'ouverture.
     *  - Voir pourquoi les noms des blocs ne s'affichent pas dans le message d'echec du butin.
     *  - Voir pour ajouter un début de menu principal personnalisé.
     */
    
    public LootManager() 
	this.reloadingPositions = new HashMap<>();
    }

    public void init(FMLPreInitializationEvent event) {
	this.reloadingTime = ServerVariables.lootReloadTime * 1000;	
	Logger.debug("LootManager reload time setted on " + this.reloadingTime);
    }

    public void onUpdate(World world) {
	long now = System.currentTimeMillis();
	this.reloadingPositions.entrySet().removeIf(entry -> entry.getValue() + this.reloadingTime <= now);
    }

    public void addInReloadingList(int x, int y, int z, long timestamp) {
	this.reloadingPositions.put(new LootableBlockPos(x, y, z), timestamp);
    }

    public boolean isLootable(Block block, int x, int y, int z) {
	return LootTable.blockByLootMapping.containsKey(block);
    }

    public boolean isReloading(int x, int y, int z) {
	return this.reloadingPositions.containsKey(new LootableBlockPos(x, y, z));
    }

    public Collection<LootableBlockPos> getReloadingPositions() {
	return Collections.unmodifiableSet(this.reloadingPositions.keySet());
    }
}