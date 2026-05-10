package org.survivalcraft.zombie.client.events;

import org.survivalcraft.zombie.client.ModKeybindings;
import org.survivalcraft.zombie.utils.Logger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class EventKeyboardHandler {

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		ModKeybindings pressed = this.getPressedKey();
		
		if(pressed != null) {
			switch(pressed) {
				case TEST:
					Logger.info("Test key pressed!");
					break;
				default:
					break;
			}
		}
	}
	
	private ModKeybindings getPressedKey() {
		for(ModKeybindings key : ModKeybindings.values()) if(key.isPressed()) return key;
		
		return null;
	}
}