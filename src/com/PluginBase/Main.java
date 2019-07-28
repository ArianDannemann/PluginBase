package com.PluginBase;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		Chat.getInstance().sendMessageToConsole("[INFO] Plugin Base Enabled");
	}
	
}
