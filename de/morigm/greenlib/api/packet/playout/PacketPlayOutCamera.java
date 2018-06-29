package de.morigm.greenlib.api.packet.playout;

import org.bukkit.entity.Entity;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutCamera implements PacketPlayOut {
	
	private Entity entity;

	public PacketPlayOutCamera(Entity e) 
	{
		this.entity = e;
	}

	public Entity getEntity() 
	{
		return entity;
	}

}
