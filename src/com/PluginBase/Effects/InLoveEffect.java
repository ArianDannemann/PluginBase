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
				false,							// ambient
				true,							// particles
				false							// icon
		);
		PotionEffect jumpBoost = new PotionEffect
		(
				PotionEffectType.JUMP,
				600,
				1,
				false,
				true,
				false
		);

		// Apply the potion effects to the player
		entity.addPotionEffect(regeneration);
		entity.addPotionEffect(jumpBoost);

		// Play some particle effects at the player location
		ParticleEmitter.getInstance().emitParticlesContinuously
		(
				entity,							// entity
				new Vector(0, 2, 0),			// offset
				Particle.HEART,					// particle
				30,								// amount
				0.05,							// speed
				new Vector(0, 0, 0),			// spread
				plugin,							// plugin
				0,								// delay
				10,								// period
				600								// duration
				);
	}
}
