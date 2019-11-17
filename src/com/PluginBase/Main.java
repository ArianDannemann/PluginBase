package com.PluginBase;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	/*
	 * TODO:
	 * - stop using an invisible prefix for the clickable inventory and switch to just saving the object
	 */
	
	public static final String version = "1.2";
	
	@Override
	public void onEnable() {
		Chat.getInstance().sendMessageToConsole(this, "[INFO] Plugin Base Enabled");
	}
}
