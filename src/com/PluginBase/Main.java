package com.PluginBase;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static final String version = "1.0";
	
	@Override
	public void onEnable() {
		Chat.getInstance().sendMessageToConsole(this, "[INFO] Plugin Base Enabled");
	}
}
