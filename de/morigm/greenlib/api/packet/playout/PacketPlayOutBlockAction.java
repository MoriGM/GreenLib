package de.morigm.greenlib.api.packet.playout;

import org.bukkit.Location;
import org.bukkit.block.Block;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutBlockAction implements PacketPlayOut {
	
	private Location a;
	private int b;
	private int c;
	private Block d;
	
	public PacketPlayOutBlockAction(Location paramBlockPosition, Block paramBlock, int paramInt1, int paramInt2)
	{
		this.a = paramBlockPosition;
	    this.b = paramInt1;
	    this.c = paramInt2;
	    this.d = paramBlock;
	 }

	public Location a() 
	{
		return a;
	}

	public int b() 
	{
		return b;
	}

	public int c() 
	{
		return c;
	}

	public Block d() 
	{
		return d;
	}

}
