package com.PluginBase;

import org.bukkit.plugin.Plugin;

/**
 * Helps other plugins check if they are using the correct plugin base version
 * 
 * @author Arian Dannemann
 */
public class VersionChecker {
	
	public static final VersionChecker versionChecker = new VersionChecker();
	
	public static VersionChecker getInstance() {
		return versionChecker;
	}
	
	/**
	 * Checks if the plugin uses the correct version of the plugin base
	 * 
	 * @param plugin			The plugin that is using the plugin base
	 * @param expectedVersion	The version it was built on
	 * @return					Whether it's using the correct plugin base version
	 */
	public boolean checkVersion(Plugin plugin, String expectedVersion) {
		
		boolean isAPIUpToDate = Main.version.equals(expectedVersion);
		
		if (!isAPIUpToDate) {
			Chat.getInstance().sendWarningMessageToConsole
			(
					plugin,
					plugin.getName() + " is expecting PluginBase version "
					+ expectedVersion + " while the actual version is "
					+ Main.version
			);
		}
		
		return isAPIUpToDate;
	}
}
