package com.PluginBase;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * This class helps emit particles at locations or entities
 * 
 * @author Arian Dannemann
 */
public class ParticleEmitter {

	public static final ParticleEmitter particleEmitter = new ParticleEmitter();
	
	public static ParticleEmitter getInstance() {
		return particleEmitter;
	}
	
	/**
	 * Emit particles for a certain amount of time at the location of an entity
	 * 
	 * @param entity The entity at which the particles will be emitted
	 * @param particle The particle type to be emitted
	 * @param amount How many particles should be emitted
	 * @param speed How quickly the particles should play their animation
	 * @param spread The spread of the particles (using the entity location as a base)
	 * @param plugin The plugin
	 * @param delay Delay in minecraft ticks until the first particle emits (20 ticks = 1 second)
	 * @param period Delay in minecraft ticks between particle emissions (20 ticks = 1 second)
	 * @param duration Duration in minecraft ticks of how long particles will be emitted (20 ticks = 1 second)
	 */
	public void emitParticlesContinuously(Entity entity, Particle particle, int amount, double speed, Vector spread, Plugin plugin, int delay, int period, int duration) {
		
		// Create a synchronous task
		BukkitRunnable runnable = new BukkitRunnable() {
			
			// Keep track of how many times runnable was run
			int counter = 0;
			
			@Override
			public void run() {
				
				// Emit particles
				emitParticles(entity.getWorld(), entity.getLocation(), particle, amount, speed, spread);
				
				// Add the time between runs to timer count
				counter += period;
				
				// If counter has run for the needed amount of time
				if (counter >= duration) {
					
					// Cancel this runnable
					this.cancel();
				}
				
				if (entity.isDead()) {
					this.cancel();
				}
			}
		};
		runnable.runTaskTimer(plugin, delay, period);
	}
	
	/**
	 * Emit particles for a certain amount of time at a specific location
	 * 
	 * @param location The location at which the particles will be emitted
	 * @param particle The particle type to be emitted
	 * @param amount How many particles should be emitted
	 * @param speed How quickly the particles should play their animation
	 * @param spread The spread of the particles (using the entity location as a base)
	 * @param plugin The plugin
	 * @param delay Delay in minecraft ticks until the first particle emits (20 ticks = 1 second)
	 * @param period Delay in minecraft ticks between particle emissions (20 ticks = 1 second)
	 * @param duration Duration in minecraft ticks of how long particles will be emitted (20 ticks = 1 second)
	 */
	public void emitParticlesContinuously(Location location, Particle particle, int amount, double speed, Vector spread, Plugin plugin, int delay, int period, int duration) {
		
		// Create a synchronous task
		BukkitRunnable runnable = new BukkitRunnable() {
			
			// Keep track of how many times runnable was run
			int counter = 0;
			
			@Override
			public void run() {
				
				// Emit particles
				emitParticles(location.getWorld(), location, particle, amount, speed, spread);
				
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
	 * Emit particles once at a specific location
	 * 
	 * @param world The world in which the particles will be emitted
	 * @param location The location at which the particles will be emitted
	 * @param particle The particle type to be emitted
	 * @param amount How many particles should be emitted
	 * @param speed How quickly the particles should play their animation
	 * @param spread The spread of the particles (using the entity location as a base)
	 */
	public void emitParticles(World world, Location location, Particle particle, int amount, double speed, Vector spread) {
		world.spawnParticle(
				particle,					// Particle type
				location.getX(),			// Location X
				location.getY(),			// Location Y
				location.getZ(),			// Location Z
				amount,						// Particle amount
				spread.getX(),				// Particle spread X
				spread.getY(),				// Particle spread Y
				spread.getZ(),				// Particle spread Z
				speed);						// Particle speed
	}
}
