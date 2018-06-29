package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.effect.MobEffect;

public class PacketPlayOutEntityEffect {
	
	private MobEffect mobEffect;
	private int paramint;

	public PacketPlayOutEntityEffect(int paramint,MobEffect mobEffect) 
	{
		this.paramint = paramint;
		this.mobEffect = mobEffect;
	}

	public MobEffect getMobEffect()
	{
		return mobEffect;
	}

	public int getParamint() 
	{
		return paramint;
	}

}
