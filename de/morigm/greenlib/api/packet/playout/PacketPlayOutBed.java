package de.morigm.greenlib.api.packet.playout;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutBed implements PacketPlayOut {
	
	Player a;
	Location b;
	
	public PacketPlayOutBed(Player a,Location b) 
	{
		this.a = a;
		this.b = b;
	}

	public Player a() 
	{
		return a;
	}

	public Location b() 
	{
		return b;
	}

}
