package de.morigm.greenlib.manager;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import de.morigm.greenlib.Main;
import de.morigm.greenlib.cmds.CMD_GreenLib;
import de.morigm.greenlib.listeners.Listener_GUI;
import de.morigm.greenlib.listeners.Listener_Join;
import de.morigm.greenlib.listeners.Listener_Quit;
import de.morigm.greenlib.listeners.Listener_Chat;
import de.morigm.greenlib.listeners.Listener_Death;


public class PluginManager {
	
	public void loadCommands()
	{
		registerCommand("greenlib", new CMD_GreenLib());
	}
	
	public void loadListeners()
	{
		registerListener(new Listener_GUI());
		registerListener(new Listener_Death());
		registerListener(new Listener_Chat());
		registerListener(new Listener_Join());
		registerListener(new Listener_Quit());
	}
	
	public void registerCommand(String name,CommandExecutor cmd)
	{
		Main.getInstance().getCommand(name).setExecutor(cmd);
	}
	
	public void registerListener(Listener l)
	{
		Bukkit.getPluginManager().registerEvents(l, Main.getInstance());
	}

}
