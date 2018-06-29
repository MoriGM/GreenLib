package de.morigm.greenlib.api.packet.types;

import org.bukkit.entity.Player;

import de.morigm.greenlib.api.packet.Packet;

public interface PacketPlayIn extends Packet {
	
	public Player getPlayer();
	
	public void setPlayer(Player p);
	
}
