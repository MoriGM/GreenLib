package de.morigm.greenlib.api.packet.types;

import org.bukkit.entity.Player;

import de.morigm.greenlib.api.packet.Packet;

public interface ServerPlayOut extends Packet {
	
	public Player getPlayer();

}
