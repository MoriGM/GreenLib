package de.morigm.greenlib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import de.morigm.greenlib.Main;
import de.morigm.greenlib.api.conversation.Conversation;

@SuppressWarnings("deprecation")
public class Listener_Chat implements Listener {
	
	@EventHandler
	public void on(PlayerChatEvent e)
	{
		for(Conversation c : Main.getInstance().getDefaultConfig().conversations)
			if(c.getPlayers().contains(e.getPlayer()))
			{
					c.sendMessage(e.getPlayer(), e.getMessage());
				e.setCancelled(true);
			}
	}

}
