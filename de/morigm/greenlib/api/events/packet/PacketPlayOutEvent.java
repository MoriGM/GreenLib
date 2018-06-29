package de.morigm.greenlib.api.events.packet;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.morigm.greenlib.api.enums.Packet;
import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	private PacketPlayOut packet;
	private Player player;
	private Packet PackeType;
	
	public PacketPlayOutEvent(Player player,PacketPlayOut packet) 
	{
		this.player = player;
		this.packet = packet;
		this.PackeType = Packet.valueOf(packet.getClass().getName().replace(".", ",").split(",")[packet.getClass().getName().replace(".", ",").split(",").length - 1]);
	}

	@Override
	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public PacketPlayOut getPacket()
	{
		return packet;
	}

	public Player getPlayer() 
	{
		return player;
	}

	public Packet getPackeType() 
	{
		return PackeType;
	}
	
	public static HandlerList getHandlerList() 
	{
        return handlers;

	}
	

}
