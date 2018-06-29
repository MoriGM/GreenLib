package de.morigm.greenlib.api.managers;

import org.bukkit.Bukkit;

public class ConsolenManager {
	
	public void sendMessage(String s)
	{
		Bukkit.getConsoleSender().sendMessage(s);
	}
	
	public void sendMessage(String... s)
	{
		Bukkit.getConsoleSender().sendMessage(s);
	}
	

}
