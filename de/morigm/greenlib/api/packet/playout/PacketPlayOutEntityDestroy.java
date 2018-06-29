package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutEntityDestroy implements PacketPlayOut {
	
	private int[] entitysID;

	public PacketPlayOutEntityDestroy(int... entitysID) 
	{
		this.entitysID = entitysID;
	}

	public int[] getEntitysID() 
	{
		return entitysID;
	}

}
