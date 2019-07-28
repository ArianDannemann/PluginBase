package com.PluginBase;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;

/**
 * This class helps emit sounds at specific locations
 * 
 * @author Arian Dannemann
 */
public class SoundEmitter {
	
	public static final SoundEmitter soundEmitter = new SoundEmitter();
	
	public static SoundEmitter getInstance() {
		return soundEmitter;
	}
	
	/**
	 * Emits a sound at a specific location
	 * 
	 * @param world				The world in which the sound will be played
	 * @param location			The location of the world
	 * @param sound				The sound type
	 * @param soundCategory		The sound category
	 * @param volume			The sound volume
	 * @param pitch				The sound pitch
	 */
	public void emitSound(World world, Location location, Sound sound, SoundCategory soundCategory, float volume, float pitch) {
		world.playSound(
				location,			// sound location
				sound,				// some string?
				soundCategory,		// sound category
				volume,				// volume
				pitch				// pitch
		);
	}
}
