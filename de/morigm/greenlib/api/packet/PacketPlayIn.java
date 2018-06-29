package de.morigm.greenlib.api.packet;

import org.bukkit.entity.Player;

public interface PacketPlayIn {
	
	public Player getPlayer();
	
	public void setPlayer(Player p);
	
}
