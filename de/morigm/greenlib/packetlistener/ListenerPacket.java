package de.morigm.greenlib.packetlistener;

import org.bukkit.entity.Player;


public abstract class ListenerPacket {
	
	protected Player player;

	public ListenerPacket(Player p)
	{
		this.player = p;
	}
	
	public abstract void init();
	
	public abstract void close();
	
	public abstract boolean isInit();
	
	public Player getPlayer()
	{
		return player;
	}
	

}
