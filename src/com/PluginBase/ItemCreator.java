package com.PluginBase;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * This class creates customized items
 * 
 * @author Arian Dannemann
 */
public class ItemCreator {

	public static final ItemCreator itemCreator = new ItemCreator();
	
	public static ItemCreator getInstance() {
		return itemCreator;
	}
	
	/**
	 * Creates an item with set custom information
	 * 
	 * @param type		The item type
	 * @param amount	The amout of items
	 * @param name		The item name
	 * @param lore		The item description
	 * @return			Generate item stack
	 */
	public ItemStack createItem(Material type, int amount, String name, String loreString) {
		
		List<String> loreList = new ArrayList<>();
		String[] loreArray = loreString.split("\n");
		
		for (String loreLine : loreArray) {
			loreList.add(loreLine);
		}
		
		return createItem(type, amount, name, loreList);
	}
	
	/**
	 * Creates an item with set custom information
	 * 
	 * @param type		The item type
	 * @param amount	The amout of items
	 * @param name		The item name
	 * @param lore		The item description
	 * @return			Generate item stack
	 */
	public ItemStack createItem(Material type, int amount, String name, List<String> lore) {
		
		// Generate the item with given type and amount
		ItemStack item = new ItemStack(type, amount);
		
		// Get the meta info of the item
		ItemMeta itemMeta = item.getItemMeta();
		
		// Set the display name
		itemMeta.setDisplayName(name);
		// Set the lore
		itemMeta.setLore(lore);
		
		// Save the meta data to the item
		item.setItemMeta(itemMeta);
		
		// Return the finished item
		return item;
	}
}
