package de.morigm.greenlib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.morigm.greenlib.Main;

public class Listener_Death implements Listener {
	
	@EventHandler
	public void on(PlayerDeathEvent e)
	{
		if(Main.getInstance().getDefaultConfig().names.contains(e.getEntity()))
		{
			Main.getInstance().getDefaultConfig().names.remove(e.getEntity());
			e.setDeathMessage("");
			e.setKeepInventory(true);
			e.setKeepLevel(true);
		}
		if(Main.getInstance().getDefaultConfig().skins.contains(e.getEntity()))
		{
			Main.getInstance().getDefaultConfig().skins.remove(e.getEntity());
			e.setDeathMessage("");
			e.setKeepInventory(true);
			e.setKeepLevel(true);
		}
	}

}
