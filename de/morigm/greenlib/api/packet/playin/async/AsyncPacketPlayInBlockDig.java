package de.morigm.greenlib.api.packet.playin.async;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.morigm.greenlib.api.enums.EnumDirection;
import de.morigm.greenlib.api.enums.EnumPlayerDigType;
import de.morigm.greenlib.api.packet.types.PacketPlayIn;

public class AsyncPacketPlayInBlockDig implements PacketPlayIn {

	private Player player;
	private Location a;
	private EnumDirection b;
	private EnumPlayerDigType c;
	private boolean cancelled = false;

	public AsyncPacketPlayInBlockDig(Location loc, EnumDirection b, EnumPlayerDigType c) 
	{
		this.a = loc;
		this.b = b;
		this.c = c;
	}

	@Override
	public Player getPlayer() 
	{
		return this.player;
	}

	public Location getLocation()
	{
		return this.a;
	}

	public EnumDirection getDirection() 
	{
		return this.b;
	}

	public EnumPlayerDigType getPlayerDigType() 
	{
		return this.c;
	}

	public boolean isCancelled()
	{
		return cancelled;
	}

	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}
	
	@Override
	public void setPlayer(Player p) 
	{
		this.player = p;
	}
	
}
