package de.morigm.greenlib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.morigm.greenlib.Main;
import de.morigm.greenlib.packetlistener.ListenerPacket;

public class Listener_Join implements Listener {
	
	@EventHandler
	public void on(PlayerJoinEvent e)
	{
		ListenerPacket lp = Main.getInstance().getListenerPacketManager().getListenerPacket(e.getPlayer());
		if(!lp.isInit())
			lp.init();
		Main.getInstance().getListenerPacketManager().getListenerPackets().add(lp);
	}

}
