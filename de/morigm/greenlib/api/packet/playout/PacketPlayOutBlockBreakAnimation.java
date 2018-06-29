package de.morigm.greenlib.api.packet.playout;

import org.bukkit.Location;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutBlockBreakAnimation implements PacketPlayOut {
	
	private int a;
	private Location b;
	private int c;

	public PacketPlayOutBlockBreakAnimation(int entityid,Location b,int count) 
	{
		this.a = entityid;
		this.b = b;
		this.c = count;
	}

	public int getEntityID()
	{
		return a;
	}

	public Location getLocation() 
	{
		return b;
	}

	public int getCount() 
	{
		return c;
	}

}
