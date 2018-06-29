package de.morigm.greenlib.api.packet.server;

import org.bukkit.entity.Player;

import de.morigm.greenlib.api.packet.types.ServerPlayOut;

public class ServerPlayOutAbilities implements ServerPlayOut {
	
	private boolean Invulnerable;
	private boolean Flying;
	private boolean canFly;
	private boolean canInstantlyBuild;
	private Player player;

	public ServerPlayOutAbilities(Player p,boolean Invulnerable,boolean Flying,boolean canFly,boolean canInstantlyBuild) 
	{
		this.player = p;
		this.Invulnerable = Invulnerable;
		this.Flying = Flying;
		this.canFly = canFly;
		this.canInstantlyBuild = canInstantlyBuild;
	}

	public boolean isInvulnerable() 
	{
		return Invulnerable;
	}

	public boolean isFlying()
	{
		return Flying;
	}

	public boolean CanFly() {
		return canFly;
	}

	public boolean CanInstantlyBuild()
	{
		return canInstantlyBuild;
	}

	public void setInvulnerable(boolean invulnerable) 
	{
		Invulnerable = invulnerable;
	}

	public void setFlying(boolean flying) 
	{
		Flying = flying;
	}

	public void CanFly(boolean canFly) 
	{
		this.canFly = canFly;
	}

	public void CanInstantlyBuild(boolean canInstantlyBuild)
	{
		this.canInstantlyBuild = canInstantlyBuild;
	}

	@Override
	public Player getPlayer() 
	{
		return player;
	}


	
}
