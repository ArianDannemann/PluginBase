package com.PluginBase;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

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
	
	public void emitContinuousSoundsRandomly(Entity entity, Sound sound, SoundCategory soundCategory, float volume, float pitch, int chance, Plugin plugin, int delay, int period, int duration) {
		
		// Create a synchronous task
		BukkitRunnable runnable = new BukkitRunnable() {
			
			// Keep track of how many times runnable was run
			int counter = 0;
			
			@Override
			public void run() {
				
				// Check if the chance has hit
				if (MathHelper.getInstance().hasChanceHit(chance)) {
					
					// Emit particles
					emitSound(entity.getWorld(), entity.getLocation(), sound, soundCategory, volume, pitch);
				}
				
				// Add the time between runs to timer count
				counter += period;
				
				// If counter has run for the needed amount of time
				if (counter >= duration) {
					
					// Cancel this runnable
					this.cancel();
				}
			}
		};
		runnable.runTaskTimer(plugin, delay, period);
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
