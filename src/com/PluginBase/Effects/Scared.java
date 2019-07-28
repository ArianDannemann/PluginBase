package com.PluginBase.Effects;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.PluginBase.Effect;
import com.PluginBase.ParticleEmitter;

/**
 * This effect is trying to recreate a scared player by adding blindness, slowness and a bunch of particle effects
 * 
 * @author Arian Dannemann
 */
public class Scared extends Effect {
	
	public Scared(Plugin plugin, LivingEntity entity) {
		super(plugin, entity);
	}

	@Override
	public void applyEffectToEntity(LivingEntity entity) {

		// Prepare the potion effects
		PotionEffect blindness = new PotionEffect(
				PotionEffectType.BLINDNESS,	// type
				600,						// duration
				0);							// amplifier
		PotionEffect slowness = new PotionEffect(
				PotionEffectType.SLOW,		// type
				600,						// duration
				0);							// amplifier
		
		// Apply the potion effects to the player
		entity.addPotionEffect(blindness);
		entity.addPotionEffect(slowness);
		
		// Play some particle effects at the player location
		ParticleEmitter.getInstance().emitParticlesContinuously(
				entity,
				Particle.SQUID_INK,
				30,
				0.1,
				new Vector(3, 3, 3),
				plugin,
				0,
				2,
				600);
	}
}
