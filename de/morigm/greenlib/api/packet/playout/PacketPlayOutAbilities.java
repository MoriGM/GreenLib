package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutAbilities implements PacketPlayOut {
	
	private boolean Flying;
	private boolean Invulnerable;
	private boolean canFly;
	private boolean InstantlyBuild;

	public PacketPlayOutAbilities(boolean Flying,boolean Invulnerable,boolean canFly,boolean InstantlyBuild) 
	{
		this.Flying = Flying;
		this.Invulnerable = Invulnerable;
		this.canFly = canFly;
		this.InstantlyBuild = InstantlyBuild;
	}

	public boolean isFlying()
	{
		return Flying;
	}

	public boolean isInvulnerable() 
	{
		return Invulnerable;
	}

	public boolean isCanFly() 
	{
		return canFly;
	}

	public boolean isInstantlyBuild() 
	{
		return InstantlyBuild;
	}

}
