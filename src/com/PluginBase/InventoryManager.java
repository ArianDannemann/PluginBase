package com.PluginBase;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

/**
 * This class can create and manage different interactable inventories
 * 
 * @author Arian Dannemann
 */
public class InventoryManager {

	public static final InventoryManager inventoryManager = new InventoryManager();
	
	public static InventoryManager getInstance() {
		return inventoryManager;
	}
	
	private final String clickableInventoryPrefix = ChatColor.RED + "" + ChatColor.BLACK + "" + ChatColor.BLUE + "" + ChatColor.GREEN + "" + ChatColor.RESET + "";
	
	/**
	 * Creates and returns a inventory with a given size and name aswell as an invisible prefix to mark the inventory as a 'gui' inventory
	 * 
	 * @param size	The size of the inventory (number of slots)
	 * @param name	The name of the inventory
	 * @return		Generated inventory
	 */
	public Inventory createClickableInventory(int size, String name) {
		return Bukkit.getServer().createInventory(null, size, this.clickableInventoryPrefix + name);
	}
	
	/**
	 * Creates and returns a 'gui' inventory with a given name and a default size of 27 slots
	 * 
	 * @param name	The name of the inventory
	 * @return		Generated inventory
	 */
	public Inventory createClickableInventory(String name) {
		return createClickableInventory(27, name);
	}
	
	/**
	 * Creates and returns a inventory with a given size and name
	 * 
	 * @param size	The size of the inventory (number of slots)
	 * @param name	The name of the inventory
	 * @return		Generated inventory
	 */
	public Inventory createInventory(int size, String name) {
		return Bukkit.getServer().createInventory(null, size, name);
	}
	
	/**
	 * Creates and returns a inventory with a given name and a default size of 27 slots
	 * 
	 * @param name	The name of the inventory
	 * @return		Generated inventory
	 */
	public Inventory createInventory(String name) {
		return createInventory(27, name);
	}
	
	/**
	 * Checks if the inventory that was clicked in has the clickable inventory prefab
	 * 
	 * @param inventoryView	The inventory that was clicked in
	 * @return				If the inventory is clickable
	 */
	public boolean isInventoryClickable(InventoryView inventoryView) {
		return inventoryView.getTitle().startsWith(this.clickableInventoryPrefix);
	}
}
