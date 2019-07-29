package com.PluginBase.Effects;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.PluginBase.Effect;
import com.PluginBase.ParticleEmitter;

public class CloudEffect extends Effect {

	public CloudEffect(Plugin plugin, LivingEntity entity) {
		super(plugin, entity);
	}

	@Override
	public void applyEffectToEntity(LivingEntity entity) {
		
		// Prepare the potion effect
		PotionEffect slowFalling = new PotionEffect
		(
				PotionEffectType.SLOW_FALLING,	// type
				600,							// duration
				1,								// amplifier
				false,							// ambient
				true,							// particles
				false							// icon
		);

		// Apply the potion effect to the player
		entity.addPotionEffect(slowFalling);

		// Play some particle effects at the player location
		ParticleEmitter.getInstance().emitParticlesContinuously
		(
				entity,							// entity
				new Vector(0, -1, 0),			// offset
				Particle.CLOUD,					// particle
				10,								// amount
				0.05,							// speed
				new Vector(0.5, 0, 0.5),		// spread
				plugin,							// plugin
				0,								// delay
				5,								// period
				600								// duration
				);
	}
}
