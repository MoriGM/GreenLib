package de.morigm.greenlib.api.packet.playin.sync;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.morigm.greenlib.api.enums.EnumDirection;
import de.morigm.greenlib.api.enums.EnumPlayerDigType;
import de.morigm.greenlib.api.packet.types.PacketPlayIn;

public class PacketPlayInBlockDig implements PacketPlayIn {
	
	private Player player;
	private Location a;
	private EnumDirection b;
	private EnumPlayerDigType c;

	public PacketPlayInBlockDig(Location loc, EnumDirection b, EnumPlayerDigType c) 
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
	
	@Override
	public void setPlayer(Player p) 
	{
		this.player = p;
	}

}
