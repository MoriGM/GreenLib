package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutEntityLook implements PacketPlayOut {
	
	private int entityId;
	private byte pitch;
	private byte yaw;
	private boolean onGround;

	public PacketPlayOutEntityLook(int entityid,byte pitch,byte yaw,boolean onGround) 
	{
		this.entityId = entityid;
		this.pitch = pitch;
		this.yaw = yaw;
		this.onGround = onGround;
	}

	public int getEntityId() 
	{
		return entityId;
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
