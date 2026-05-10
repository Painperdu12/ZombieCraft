package org.survivalcraft.zombie.client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;

public enum ModKeybindings {

	TEST("test", Keyboard.KEY_H);
	
	private final KeyBinding keybinding;
	
	private ModKeybindings(String name, int defaultKeyCode) {
		this.keybinding = new KeyBinding(name, defaultKeyCode, "key.categories.zombiecraft");
	}
	
	public KeyBinding getKeybinding() {
		return keybinding;
	}
	
	public boolean isPressed() {
		return this.keybinding.isPressed();
	}
	
}