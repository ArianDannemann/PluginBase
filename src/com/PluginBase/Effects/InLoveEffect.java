package com.PluginBase.Effects;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.PluginBase.Effect;
import com.PluginBase.ParticleEmitter;

public class InLoveEffect extends Effect {

	public InLoveEffect(Plugin plugin, LivingEntity entity) {
		super(plugin, entity);
	}

	@Override
	public void applyEffectToEntity(LivingEntity entity) {
		
		// Prepare the potion effects
		PotionEffect regeneration = new PotionEffect
		(
				PotionEffectType.REGENERATION,	// type
				600,							// duration
				0,								// amplifier
				true							// ambient
		);
		PotionEffect jumpBoost = new PotionEffect
		(
				PotionEffectType.JUMP,
				600,
				0,
				true
		);

		// Apply the potion effects to the player
		entity.addPotionEffect(regeneration);
		entity.addPotionEffect(jumpBoost);

		// Play some particle effects at the player location
		ParticleEmitter.getInstance().emitParticlesContinuously
		(
				entity,							// entity
				new Vector(0, 1.5, 0),			// offset
				Particle.HEART,					// particle
				30,								// amount
				0.1,							// speed
				new Vector(3, 3, 3),			// spread
				plugin,							// plugin
				0,								// delay
				2,								// period
				600								// duration
				);
	}
}
