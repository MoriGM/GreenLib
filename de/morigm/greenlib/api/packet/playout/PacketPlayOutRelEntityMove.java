package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutRelEntityMove implements PacketPlayOut {
	
	private int entityId;
	private long deltaX;
	private long deltaY;
	private long deltaZ;
	private boolean onGround;
	
	public PacketPlayOutRelEntityMove(int entityid,long deltaX,long deltaY,long deltaZ,boolean onGround) 
	{
		this.entityId = entityid;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
		this.onGround = onGround;
	}

	public int getEntityId()
	{
		return entityId;
	}

	public long getDeltaX()
	{
		return deltaX;
	}

	public long getDeltaY() 
	{
		return deltaY;
	}

	public long getDeltaZ() 
	{
		return deltaZ;
	}

	public boolean isOnGround() 
	{
		return onGround;
	}

}
