package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutEntity implements PacketPlayOut {
	
	private int entityid;

	public PacketPlayOutEntity(int entityid) 
	{
		this.entityid = entityid;
	}

	public int getEntityId() 
	{
		return entityid;
	}

}
