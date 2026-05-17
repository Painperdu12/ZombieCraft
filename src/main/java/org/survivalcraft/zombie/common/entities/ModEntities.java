package org.survivalcraft.zombie.common.entities;

import java.awt.Color;

import org.survivalcraft.zombie.ZombieCraft;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {

	public static void register() {
		Logger.infoInit("- Registering entities");
		
        EntityRegistry.registerGlobalEntityID(EntityInfected.class, "Infected", EntityRegistry.findGlobalUniqueEntityId(), new Color(0, 255, 0).getRGB(), new Color(255, 0, 0).getRGB());
		EntityRegistry.registerModEntity(EntityInfected.class, "Infected", 0, ZombieCraft.instance(), 80, 10, true);
		
		EntityRegistry.registerModEntity(EntitySeat.class, "Seat", 1, ZombieCraft.instance(), 15, 10, false);
	}
}