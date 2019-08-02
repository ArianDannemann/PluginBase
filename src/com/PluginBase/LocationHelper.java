package com.PluginBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

/**
 * This class helps with mixed things that have to do with location manipulation and finding
 * 
 * @author Arian Dannemann
 */
public class LocationHelper {

	public static final LocationHelper locationHelper = new LocationHelper();
	
	public static LocationHelper getInstance() {
		return locationHelper;
	}
	
	/**
	 * Adds an offset to a location
	 * 
	 * @param startLocation 	The location that will be used as the center for the new offseted location
	 * @param offset 			The offset that should be added to the start location
	 * @return 					Offset location
	 */
	public Location offsetLocation(Location startLocation, Vector offset) {
		return new Location(
				startLocation.getWorld(),
				startLocation.getX() + offset.getX(),
				startLocation.getY() + offset.getY(),
				startLocation.getZ() + offset.getZ());
	}
	
	/**
	 * Finds specific blocks near a location
	 * 
	 * @param startLocation 	The location that will be used as the center of possible nearby blocks
	 * @param range 			The range in which blocks will be searched
	 * @param blockType 		The type of block that is being searched for
	 * @return 					List of blocks found near the location
	 */
	public List<Block> findNearbyBlocks(Location startLocation, int range, Material blockType) {
		
		// Prepare a list in which all the nearby blocks of the type blockType will be saved
		List<Block> blocks = new ArrayList<>();
		
		// Loop through all coordinates in range
		for (int x = -range; x <= range; x++) {
			for (int y = -range; y <= range; y++) {
				for (int z = -range; z <= range; z++) {
					
					// Get the location
					Location location = offsetLocation(startLocation, new Vector(x, y, z));
					// Get the block at the location nearby
					Block block = location.getBlock();
					
					// Check if the block is of the right block type
					if (block.getType() == blockType) {
						
						// Add the block to the list
						blocks.add(block);
					}
				}
			}
		}
		
		// Return list of found blocks
		return blocks;
	}
	
	/**
	 * Changes a location on the y axis to ensure a player can stand there
	 * 
	 * @param location 	The location that should be validated
	 * @return			Validated location
	 */
	public Location validateLocation(Location location) {
		
		// Create a new location to return later
		Location validatedLocation = location;
		
		// While block at location is solid
		while (validatedLocation.getBlock().getType().isSolid()) {
			
			// Move location up one
			validatedLocation.setY(validatedLocation.getY() + 1);
		}
		
		// While block under location is not solid
		while (!offsetLocation(validatedLocation, new Vector(0, -1, 0)).getBlock().getType().isSolid()) {
			
			// Move location down one
			validatedLocation.setY(validatedLocation.getY() - 1);
		}
		
		// Return validated location
		return validatedLocation;
	}
	
	/**
	 * Gets a random nearby validated position between two distances
	 * 
	 * @param startLocation		The location that will be used as the center of possible nearby positions
	 * @param minimumRange		The minimum range the new location has to be from the start location
	 * @param maximumRange		The maximum range the new location may be from the start location
	 * @return					Random nearby validated location
	 */
	public Location getRandomNearbyPosition(Location startLocation, int minimumRange, int maximumRange) {
		
		// Prepare a random nearby location to return later
		Location randomLocation = startLocation;
		
		// Check if the random location is to close to the start location
		while (randomLocation.distance(startLocation) < minimumRange) {
			
			// Get a random nearby location
			randomLocation = getRandomNearbyPosition(startLocation, maximumRange);
		}
		
		// Return the validated random nearby location
		return randomLocation;
	}
	
	/**
	 * Gets a random nearby validated location.
	 * NOTE: It might return a location that isn't optimal if it can't find one the first 100 times
	 * 
	 * @param startLocation 	The location that will be used as the center of possible nearby positions
	 * @param range 			The range in which random locations will be searched for
	 * @return					Random nearby validated location
	 */
	public Location getRandomNearbyPosition(Location startLocation, int range) {
		
		// Create a random number generator
		Random rdm = MathHelper.getInstance().getRandom();
		
		// Create a new location to return later
		Location randomLocation = startLocation;
		Location validatedRandomLocation = null;
		
		// Keep track of how many times we tried to find a location
		int tries = 0;
		int maximumTries = 100;
		
		/*
		 *  Check if the validated random location doesn't exist
		 *  Check if the validated random location is to far from the start location
		 *  Check if the validated random location is inside a block (which shouldn't happen TODO: check if this check even needs to exist)
		 *  Check if there is water beneath the random location
		 *  Check if there is lava beneath the random location
		 */
		while (tries <= maximumTries &&
				(validatedRandomLocation == null
				|| validatedRandomLocation.distance(startLocation) > range * 2
				|| validatedRandomLocation.getBlock().getType().isSolid()
				|| validatedRandomLocation.getBlock().getType().toString().toLowerCase().contains("water")
				|| validatedRandomLocation.getBlock().getType().toString().toLowerCase().contains("lava"))) {

			// Generate a random offset
			Vector offset = new Vector(
					rdm.nextInt(range * 2) - range,
					rdm.nextInt(range * 2) - range,
					rdm.nextInt(range * 2) - range);

			// Add offset to random location
			randomLocation = offsetLocation(startLocation, offset);

			// Validate random location
			validatedRandomLocation = validateLocation(randomLocation);
			
			// Note the try
			tries++;
		}
		
		if (tries >= maximumTries - 1) {
			Chat.getInstance().sendWarningMessageToConsole(
					null,
					"Exceeded maximum amount of tries while trying to find a random nearby location. " 
					+ "The resulting location may not be suitable for entities");
		}
		
		// Return random validated location
		return validatedRandomLocation;
	}
}
