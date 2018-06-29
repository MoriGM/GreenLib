package de.morigm.greenlib.api.packet.playout;

import org.bukkit.entity.Entity;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutAnimation implements PacketPlayOut {
	
	private Entity a;
	private int b;

	public PacketPlayOutAnimation(Entity paramEntity, int paramInt)
	  {
	    this.a = paramEntity;
	    this.b = paramInt;
	  }

	public Entity a() 
	{
		return a;
	}

	public int b() 
	{
		return b;
	}

}
