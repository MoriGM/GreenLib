package de.morigm.greenlib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.morigm.greenlib.Main;
import de.morigm.greenlib.packetlistener.ListenerPacket;

public class Listener_Quit implements Listener {
	
	@EventHandler
	public void on(PlayerQuitEvent e)
	{
		ListenerPacket lp = null;
		for(ListenerPacket lps : Main.getInstance().getListenerPacketManager().getListenerPackets())
			if(lps.getPlayer().equals(e.getPlayer()))
				lp = lps;
		if(lp != null)
		{
			if(lp.isInit())
				lp.close();
			Main.getInstance().getListenerPacketManager().getListenerPackets().remove(lp);
		}
	}
	
	@EventHandler
	public void on(PlayerKickEvent e)
	{
		if(e.isCancelled())
			return;
		ListenerPacket lp = null;
		for(ListenerPacket lps : Main.getInstance().getListenerPacketManager().getListenerPackets())
			if(lps.getPlayer().equals(e.getPlayer()))
				lp = lps;
		if(lp != null)
		{
			if(lp.isInit())
				lp.close();
			Main.getInstance().getListenerPacketManager().getListenerPackets().remove(lp);
		}
	}

}
