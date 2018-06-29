package de.morigm.greenlib.api.combat;

import org.bukkit.entity.Entity;

public class CombatTracker {
	
	private Entity entity;

	public CombatTracker(Entity e) 
	{
		this.entity = e;
	}

	public Entity getEntity()
	{
		return entity;
	}

}
