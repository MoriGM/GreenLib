package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutCollect implements PacketPlayOut {
	
	private int a;
	private int b;
	private int c;

	public PacketPlayOutCollect(int paramInt1, int paramInt2, int paramInt3)
	  {
	    this.a = paramInt1;
	    this.b = paramInt2;
	    this.c = paramInt3;
	  }

	public int getCollectedEntityID()
	{
		return a;
	}

	public int getCollectorEntityID()
	{
		return b;
	}

	public int getPickupItemCount() 
	{
		return c;
	}

}
