package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutRelEntityMoveLook implements PacketPlayOut {
	
	private int entityId;
	private long deltaX;
	private long deltaY;
	private long deltaZ;
	private byte pitch;
	private byte yaw;
	private boolean onGround;

	public PacketPlayOutRelEntityMoveLook(int entityid,long deltaX,long deltaY,long deltaZ,byte pitch,byte yaw,boolean onGround) 
	{
		this.entityId = entityid;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
		this.pitch = pitch;
		this.yaw = yaw;
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

	public byte getPitch()
	{
		return pitch;
	}

	public byte getYaw()
	{
		return yaw;
	}

	public boolean isOnGround()
	{
		return onGround;
	}

}
