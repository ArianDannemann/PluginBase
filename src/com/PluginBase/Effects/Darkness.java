package com.PluginBase.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.PluginBase.Effect;

public class Darkness extends Effect {

	public Darkness(Plugin plugin, LivingEntity entity) {
		super(plugin, entity);
	}

	@Override
	public void applyEffectToEntity(LivingEntity entity) {
		// Prepare the potion effect
		PotionEffect blindness = new PotionEffect
		(
				PotionEffectType.BLINDNESS,		// type
				600,							// duration
				0,								// amplifier
				false,							// ambient
				true,							// particles
				false							// icon
		);
		// Apply the potion effect to the player
		entity.addPotionEffect(blindness);
	}
}
