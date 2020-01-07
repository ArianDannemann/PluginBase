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
	 * Checks if a location has a block somewhere above it
	 * 
	 * @param location	The starting location
	 * @return			True if the location has a block above it
	 */
	public boolean isLocationUnderBlocks(Location location) {
		
		// Aslong as the max build height wasn't reached
		while(location.getY() <= 256) {
			
			// Increase the Y coordinate of the location by one
			location.setY(location.getY() + 1);
			
			// Check if the location has a solid block
			if (location.getBlock().getType().isSolid()) {
				return true;
			}
		}
		
		return false;
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
					"PluginBase",
					"Exceeded maximum amount of tries while trying to find a random nearby location. " 
					+ "The resulting location may not be suitable for entities");
		}
		
		// Return random validated location
		return validatedRandomLocation;
	}
	
	/**
	 * Returns a list of all blocks between two locations (square shape)
	 * 
	 * @param location1		The start location
	 * @param location2		The end location
	 * @return				List of all blocks between the locations
	 */
	public List<Block> getBlocksBetweenLocations(Location location1, Location location2) {

		// Check if both locations are in the same world
		if (location1.getWorld() != location2.getWorld()) {
			Chat.getInstance().sendErrorToConsole("PluginBase", "Can't get blocks between locations",
					"The locations specified are not in the same dimension. "
							+ "Getting a list of blocks between two locations only works if they are in the same world");
			return null;
		}

		// Prepare an empty list of blocks to return later on
		List<Block> blocks = new ArrayList<>();
		
		/**
		 * Here we take the smallest location (put all of the smallest values from location1 and location2 into a new location)
		 * Then we get the biggest location. After this we can use a simple for loop to iterate from one to the other without
		 * worrying about direction
		 */
		// Get the smallest possible location
		Location startLocation = new Location
		(
				location1.getWorld(),
				(location1.getX() > location2.getX()) ? location2.getX() : location1.getX(),
				(location1.getY() > location2.getY()) ? location2.getY() : location1.getY(),
				(location1.getZ() > location2.getZ()) ? location2.getZ() : location1.getZ()
		);
		// Get the biggest possible location
		Location goalLocation = new Location
		(
				location1.getWorld(),
				(location1.getX() < location2.getX()) ? location2.getX() : location1.getX(),
				(location1.getY() < location2.getY()) ? location2.getY() : location1.getY(),
				(location1.getZ() < location2.getZ()) ? location2.getZ() : location1.getZ()
		);
		
		// Loop through all spots between the two locations
		for (int x = startLocation.getBlockX(); x < goalLocation.getBlockX(); x++) {
			for (int y = startLocation.getBlockY(); y < goalLocation.getBlockY(); y++) {
				for (int z = startLocation.getBlockZ(); z < goalLocation.getBlockZ(); z++) {
					
					// Get the block that we are currently inspecting
					Block block = new Location(location1.getWorld(), x, y, z).getBlock();
					// Add it to the list
					blocks.add(block);
				}
			}
		}
		
		// Return the list of blocks
		return blocks;
	}
	
	/**
	 * Get the smaller of two locations
	 * @param location1		First location
	 * @param location2		Second location
	 * @return				The smaller location
	 */
	public Location getSmallerLocation(Location location1, Location location2) {
		return new Location
		(
				location1.getWorld(),
				(location1.getX() > location2.getX()) ? location2.getX() : location1.getX(),
				(location1.getY() > location2.getY()) ? location2.getY() : location1.getY(),
				(location1.getZ() > location2.getZ()) ? location2.getZ() : location1.getZ()
		);
	}
	
	/**
	 * Get the bigger of two locations
	 * @param location1		First location
	 * @param location2		Second location
	 * @return				The bigger location
	 */
	public Location getBiggerLocation(Location location1, Location location2) {
		return new Location
		(
				location1.getWorld(),
				(location1.getX() < location2.getX()) ? location2.getX() : location1.getX(),
				(location1.getY() < location2.getY()) ? location2.getY() : location1.getY(),
				(location1.getZ() < location2.getZ()) ? location2.getZ() : location1.getZ()
		);
	}
}
