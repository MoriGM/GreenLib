package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.enums.SoundCategory;
import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutCustomSoundEffect implements PacketPlayOut {
	
	private String soundid;
	private SoundCategory soundCategory;
	private double x;
	private double y;
	private double z;
	private float volume;
	private float pitch;

	public PacketPlayOutCustomSoundEffect(String soundid,SoundCategory soundCategory,double x,double y,double z,float volume,float pitch)
	{
		this.soundid = soundid;
		this.soundCategory = soundCategory;
		this.x = x;
		this.y = y;
		this.z = z;
		this.volume = volume;
		this.pitch = pitch;
	}

	public String getSoundId()
	{
		return soundid;
	}

	public SoundCategory getSoundCategory()
	{
		return soundCategory;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	public float getVolume()
	{
		return volume;
	}

	public float getPitch()
	{
		return pitch;
	}

}
