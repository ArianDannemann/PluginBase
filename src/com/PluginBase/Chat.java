package com.PluginBase;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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
	 * @param message	The massage which will appear in the console
	 */
	public void sendMessageToConsole(String message) {
		
		// Get the console sender (so we can send colored chat messages)
		ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
		
		// Send the message over to the console
		consoleSender.sendMessage(message);
	}
	
	/**
	 * Sends a message with an error prefix to the console
	 * 
	 * @param message	The massage which will appear in the console
	 */
	public void sendWarningMessageToConsole(String message) {
		
		// Get the console sender (so we can send colored chat messages)
		ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
		
		// Send the message over to the console
		consoleSender.sendMessage(ChatColor.YELLOW + "[WARNING] " + message);
	}
	
	/**
	 * Sends a message with an error prefix to the console
	 * 
	 * @param message	The massage which will appear in the console
	 */
	public void sendErrorMessageToConsole(String message) {
		
		// Get the console sender (so we can send colored chat messages)
		ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
		
		// Send the message over to the console
		consoleSender.sendMessage(ChatColor.RED + "[ERROR] " + message);
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