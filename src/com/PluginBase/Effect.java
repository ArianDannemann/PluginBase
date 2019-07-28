package com.PluginBase;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

/**
 * This class is the base for all effects
 * 
 * @author Arian Dannemann
 */
public abstract class Effect {
	
	protected Plugin plugin;

	public Effect(Plugin plugin, LivingEntity entity) {
		this.plugin = plugin;
		applyEffectToEntity(entity);
	}

	/**
	 * Applies the effect to a target entity
	 * 
	 * @param entity	The target entity of the effect
	 */
	public abstract void applyEffectToEntity(LivingEntity entity);
	
}
