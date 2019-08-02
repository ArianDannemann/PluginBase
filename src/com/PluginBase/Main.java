package com.PluginBase;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Chat.getInstance().sendMessageToConsole(this, "[INFO] Plugin Base Enabled");
	}
	
}
