package com.PluginBase;

import java.util.Random;

/**
 * This class contains some usefull mathematic functions
 * 
 * @author Arian Dannemann
 */
public class MathHelper {

	public static final MathHelper mathHelper = new MathHelper();
	
	public static MathHelper getInstance() {
		return mathHelper;
	}
	
	private final Random random = new Random();
	
	/**
	 * Returns a random number generator
	 * 
	 * @return Random number generator
	 */
	public Random getRandom() {
		return this.random;
	}
	
	/**
	 * Checks if a possibility occured
	 * 
	 * @param chance The possibility of a hit (in %)
	 * @return If a hit occured
	 */
	public boolean hasChanceHit(double chance) {
		return this.random.nextDouble() * 100 <= chance;
	}
	
	/**
	 * Checks if a possibility occured
	 * 
	 * @param chance The possibility of a hit (in %)
	 * @return If a hit occured
	 */
	public boolean hasChanceHit(int chance) {
		return this.random.nextInt(101) <= chance;
	}
}
