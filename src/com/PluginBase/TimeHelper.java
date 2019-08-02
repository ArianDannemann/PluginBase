package com.PluginBase;

import org.bukkit.World;

/**
 * This class helps manage the time for a specific world
 * 
 * @author Arian Dannemann
 */
public class TimeHelper {
	
	public static final TimeHelper timeHelper = new TimeHelper();
	
	public static TimeHelper getInstance() {
		return timeHelper;
	}
	
	private int nightTime 	= 13000,
				dayTime 	= 23000;
	
	/**
	 * Checks if it's night in a specific world
	 * 
	 * @param world		The world that will be checked for night
	 * @return			Is the world time night
	 */
	public boolean isNight(World world) {
		return world.getTime() >= this.nightTime
				&& world.getTime() <= this.dayTime;
	}
	
	/**
	 * Sets the time of the world to night
	 * 
	 * @param world		The world which time will be set to night
	 */
	public void setNight(World world) {
		world.setTime(this.nightTime);
	}
	
	/**
	 * Sets the time of the world to night
	 * 
	 * @param world		The world which time will be set to day
	 */
	public void setDay(World world) {
		world.setTime(this.dayTime);
	}
}

