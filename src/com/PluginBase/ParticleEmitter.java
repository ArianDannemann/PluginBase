package com.PluginBase;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
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
	
	public Particle[] particleTypesThatNeedData = new Particle[] {
			Particle.REDSTONE,
			Particle.ITEM_CRACK,
			Particle.BLOCK_CRACK,
			Particle.BLOCK_DUST
	};
	
	/**
	 * Emit particles for a certain amount of time at the location of an entity
	 * 
	 * @param entity 		The entity at which the particles will be emitted
	 * @param particle 		The particle type to be emitted
	 * @param amount 		How many particles should be emitted
	 * @param speed			How quickly the particles should play their animation
	 * @param spread 		The spread of the particles (using the entity location as a base)
	 * @param plugin 		The plugin
	 * @param delay 		Delay in minecraft ticks until the first particle emits (20 ticks = 1 second)
	 * @param period 		Delay in minecraft ticks between particle emissions (20 ticks = 1 second)
	 * @param duration 		Duration in minecraft ticks of how long particles will be emitted (20 ticks = 1 second)
	 */
	public void emitParticlesContinuously(Entity entity, Particle particle, int amount, double speed, Vector spread, Plugin plugin, int delay, int period, int duration) {
		
		// Create a synchronous task
		BukkitRunnable runnable = new BukkitRunnable() {
			
			// Keep track of how many times runnable was run
			int counter = 0;
			
			@Override
			public void run() {
				
				// Emit particles
				emitParticles
				(
						entity.getWorld(),
						entity.getLocation(),
						particle,
						amount,
						speed,
						spread
				);
				
				// Add the time between runs to timer count
				this.counter += period;
				
				// If counter has run for the needed amount of time
				if (this.counter >= duration) {
					
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
	 * Emit particles for a certain amount of time at the location of an entity
	 * 
	 * @param entity 		The entity at which the particles will be emitted
	 * @param offset 		The offset from the entity where the particles will be emitted
	 * @param particle 		The particle type to be emitted
	 * @param amount 		How many particles should be emitted
	 * @param speed			How quickly the particles should play their animation
	 * @param spread 		The spread of the particles (using the entity location as a base)
	 * @param plugin 		The plugin
	 * @param delay 		Delay in minecraft ticks until the first particle emits (20 ticks = 1 second)
	 * @param period 		Delay in minecraft ticks between particle emissions (20 ticks = 1 second)
	 * @param duration 		Duration in minecraft ticks of how long particles will be emitted (20 ticks = 1 second)
	 */
	public void emitParticlesContinuously(Entity entity, Vector offset, Particle particle, int amount, double speed, Vector spread, Plugin plugin, int delay, int period, int duration) {
		
		// Create a synchronous task
		BukkitRunnable runnable = new BukkitRunnable() {
			
			// Keep track of how many times runnable was run
			int counter = 0;
			
			@Override
			public void run() {
				
				// Emit particles
				emitParticles
				(
						entity.getWorld(),
						LocationHelper.getInstance().offsetLocation
						(
								entity.getLocation(),
								offset
						),
						particle,
						amount,
						speed,
						spread
				);
				
				// Add the time between runs to timer count
				this.counter += period;
				
				// If counter has run for the needed amount of time
				if (this.counter >= duration) {
					
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
	 * @param location 		The location at which the particles will be emitted
	 * @param particle 		The particle type to be emitted
	 * @param amount 		How many particles should be emitted
	 * @param speed 		How quickly the particles should play their animation
	 * @param spread 		The spread of the particles (using the entity location as a base)
	 * @param plugin 		The plugin
	 * @param delay 		Delay in minecraft ticks until the first particle emits (20 ticks = 1 second)
	 * @param period 		Delay in minecraft ticks between particle emissions (20 ticks = 1 second)
	 * @param duration 		Duration in minecraft ticks of how long particles will be emitted (20 ticks = 1 second)
	 */
	public void emitParticlesContinuously(Location location, Particle particle, int amount, double speed, Vector spread, Plugin plugin, int delay, int period, int duration) {
		
		// Create a synchronous task
		BukkitRunnable runnable = new BukkitRunnable() {
			
			// Keep track of how many times runnable was run
			int counter = 0;
			
			@Override
			public void run() {
				
				// Emit particles
				emitParticles
				(
						location.getWorld(),
						location,
						particle,
						amount,
						speed,
						spread
				);
				
				// Add the time between runs to timer count
				this.counter += period;
				
				// If counter has run for the needed amount of time
				if (this.counter >= duration) {
					
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
	 * @param world 		The world in which the particles will be emitted
	 * @param location 		The location at which the particles will be emitted
	 * @param particle 		The particle type to be emitted
	 * @param amount 		How many particles should be emitted
	 * @param speed 		How quickly the particles should play their animation
	 * @param spread 		The spread of the particles (using the entity location as a base)
	 */
	public void emitParticles(World world, Location location, Particle particle, int amount, double speed, Vector spread) {
		
		// Loop through all particle types that minecraft expects some data for
		for (Particle particleThatNeedsData : this.particleTypesThatNeedData) {
			
			// Check if the particle type given needs data
			if (particle == particleThatNeedsData) {
				
				// Log the error in the console
				Chat.getInstance().sendErrorToConsole
				(
						null,
						"Tried to emit particle without data",
						"Particle." + particle + " may need either dust options or block data to be spawned"
				);
				
				// Do not actually spawn the particle
				return;
			}
		}
		
		world.spawnParticle
		(
				particle,					// Particle type
				location.getX(),			// Location X
				location.getY(),			// Location Y
				location.getZ(),			// Location Z
				amount,						// Particle amount
				spread.getX(),				// Particle spread X
				spread.getY(),				// Particle spread Y
				spread.getZ(),				// Particle spread Z
				speed						// Particle speed
		);
	}
	
	/**
	 * Emit redstone dust once at a specific location
	 * 
	 * @param world 		The world in which the particles will be emitted
	 * @param location 		The location at which the particles will be emitted
	 * @param particle 		The particle type to be emitted
	 * @param amount 		How many particles should be emitted
	 * @param speed 		How quickly the particles should play their animation
	 * @param spread 		The spread of the particles (using the entity location as a base)
	 * @param dustOptions	The type of the particle dust
	 */
	public void emitParticles(World world, Location location, Particle particle, DustOptions dustOptions, int amount, double speed, Vector spread) {
		world.spawnParticle
		(
				particle,					// Particle type
				location.getX(),			// Location X
				location.getY(),			// Location Y
				location.getZ(),			// Location Z
				amount,						// Particle amount
				spread.getX(),				// Particle spread X
				spread.getY(),				// Particle spread Y
				spread.getZ(),				// Particle spread Z
				speed,						// Particle speed
				dustOptions					// Particle dust options
		);
	}
	
	/**
	 * Emit particles with custom block data once at a specific location
	 * 
	 * @param world 		The world in which the particles will be emitted
	 * @param location 		The location at which the particles will be emitted
	 * @param particle 		The particle type to be emitted
	 * @param amount 		How many particles should be emitted
	 * @param speed 		How quickly the particles should play their animation
	 * @param spread 		The spread of the particles (using the entity location as a base)
	 * @param blockData		The type of block the particles should belong to
	 */
	public void emitParticles(World world, Location location, Particle particle, BlockData blockData, int amount, double speed, Vector spread) {
		world.spawnParticle
		(
				particle,					// Particle type
				location.getX(),			// Location X
				location.getY(),			// Location Y
				location.getZ(),			// Location Z
				amount,						// Particle amount
				spread.getX(),				// Particle spread X
				spread.getY(),				// Particle spread Y
				spread.getZ(),				// Particle spread Z
				speed,						// Particle speed
				blockData					// Particle block data
		);
	}
}
