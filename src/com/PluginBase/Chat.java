package com.PluginBase;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * This class manages everything that has to do with sending chat messages
 * 
 * @author Arian Dannemann
 */
public class Chat {

	public static final Chat chat = new Chat();
	
	public static Chat getInstance() {
		return chat;
	}
	
	/**
	 * Sends a message to the console
	 * 
	 * @param pluginName	The name of the plugin that send the message
	 * @param message		The massage which will appear in the console
	 */
	public void sendMessageToConsole(String pluginName, String message) {
		
		// Get the console sender (so we can send colored chat messages)
		ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
		
		// Send the message over to the console
		consoleSender.sendMessage("[" + pluginName + "] " + message);
	}
	
	/**
	 * Sends a message to the console
	 * 
	 * @param message	The massage which will appear in the console
	 */
	public void sendMessageToConsole(Plugin plugin, String message) {
		
		// Get the plugin name, use null if a plugin wasn't given
		String pluginName = plugin == null ? "null [PluginBase?]" : plugin.getName();
		
		sendMessageToConsole(pluginName, message);
	}
	
	/**
	 * Sends a message with an error prefix to the console
	 * 
	 * @param message	The massage which will appear in the console
	 */
	public void sendWarningMessageToConsole(String pluginName, String message) {

		// Send the message over to the console
		sendMessageToConsole(pluginName, ChatColor.YELLOW + "[WARNING] " + message);
	}
	
	/**
	 * Sends a message with an error prefix to the console
	 * 
	 * @param message	The massage which will appear in the console
	 */
	public void sendWarningMessageToConsole(Plugin plugin, String message) {

		// Send the message over to the console
		sendMessageToConsole(plugin, ChatColor.YELLOW + "[WARNING] " + message);
	}
	
	/**
	 * Sends a message with an error prefix to the console
	 * 
	 * @param message	The massage which will appear in the console
	 */
	public void sendErrorToConsole(String pluginName, String error, String explanation) {
		
		// Send the message over to the console
		sendMessageToConsole(pluginName, ChatColor.RED + "[ERROR] " + error + ": " + explanation);
		
		// Also notify online admins
		broadcastMessageToOPs(ChatColor.RED + "[ERROR] " + error + ChatColor.GRAY + " See console for further information");
	}
	
	/**
	 * Sends a message with an error prefix to the console
	 * 
	 * @param message	The massage which will appear in the console
	 */
	public void sendErrorToConsole(Plugin plugin, String error, String explanation) {
		
		// Get the plugin name, use null if a plugin wasn't given
		String pluginName = plugin == null ? "null [PluginBase?]" : plugin.getName();
		
		sendErrorToConsole(pluginName, error, explanation);
	}
	
	/**
	 * Sends a chat message to a specific player
	 * 
	 * @param player	The player who should receive the message
	 * @param message	The message which will be send to the player
	 */
	public void sendMessageToPlayer(Player player, String message) {
		player.sendMessage(message);
	}
	
	/**
	 * Sends a chat message to all online players
	 * 
	 * @param message	The message that should be send
	 */
	public void broadcastMessage(String message) {
		
		// Loop through all online players
		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			
			// Send them the broadcast
			sendMessageToPlayer(onlinePlayer, message);
		}
	}
	
	/**
	 * Sends a chat message to all online players in a defined wolrd
	 * 
	 * @param world		The world in which a player has to be to recieve the broadcast
	 * @param message	The message all players in the world should recieve
	 */
	public void broadcastMessage(World world, String message) {
		
		// Loop through all online players
		for (Player onlinePlayer: Bukkit.getOnlinePlayers()) {
			
			// Check if the player is on the requested world
			if (onlinePlayer.getWorld() == world) {
				
				// Send them the broadcast
				sendMessageToPlayer(onlinePlayer, message);
			}
		}
	}
	
	/**
	 * Sends a chat message to all online players with operator rights (admins)
	 * 
	 * @param message	The message that should be send
	 */
	public void broadcastMessageToOPs(String message) {
		
		// Loop through all online players
		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			
			// Check if the online player has operator rights
			if (onlinePlayer.isOp()) {
				
				// Send them the broadcast
				sendMessageToPlayer(onlinePlayer, message);
			}
		}
	}
}
