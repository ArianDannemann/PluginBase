package com.PluginBase.Effects;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.PluginBase.Effect;
import com.PluginBase.ParticleEmitter;
import com.PluginBase.SoundEmitter;

/**
 * This effect is trying to recreate a scared player by adding blindness, slowness and a bunch of particle effects
 * 
 * @author Arian Dannemann
 */
public class ScaredEffect extends Effect {
	
	public ScaredEffect(Plugin plugin, LivingEntity entity) {
		super(plugin, entity);
	}

	@Override
	public void applyEffectToEntity(LivingEntity entity) {

		// Prepare the potion effects
		PotionEffect blindness = new PotionEffect
		(
				PotionEffectType.BLINDNESS,		// type
				600,							// duration
				0,								// amplifier
				false,							// ambient
				true,							// particles
				false							// icon
		);
		PotionEffect slowness = new PotionEffect
		(
				PotionEffectType.SLOW,
				600,
				0,
				false,
				true,
				false
		);
		
		// Apply the potion effects to the player
		entity.addPotionEffect(blindness);
		entity.addPotionEffect(slowness);
		
		// Play some particle effects at the player location
		ParticleEmitter.getInstance().emitParticlesContinuously
		(
				entity,							// entity
				Particle.SQUID_INK,				// particle
				30,								// amount
				0.1,							// speed
				new Vector(3, 3, 3),			// spread
				this.plugin,					// plugin
				0,								// delay
				2,								// period
				600								// duration
		);
		
		// Play random enderman screams
		SoundEmitter.getInstance().emitContinuousSoundsRandomly
		(
				entity,							// entity
				Sound.AMBIENT_CAVE,				// sound
				SoundCategory.HOSTILE,			// sound category
				1,								// volume
				1, 								// pitch
				30,								// chance
				this.plugin,					// plugin
				0,								// delay
				20,								// period
				600								// duration
		);
	}
}
