package de.morigm.greenlib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.morigm.greenlib.api.GreenLib;
import de.morigm.greenlib.chat.Chat;
import de.morigm.greenlib.config.Config;
import de.morigm.greenlib.manager.ListenerPacketManager;
import de.morigm.greenlib.manager.PluginManager;

public class Main extends JavaPlugin {
	
	private static Main instance;
	private PluginManager PluginManager = new PluginManager();
	private Config DefaultConfig = new Config();
	private ListenerPacketManager ListenerPacketManager = new ListenerPacketManager();
	
	@Override
	public void onLoad()
	{
		Bukkit.getConsoleSender().sendMessage(Chat.prefix + "Plugin wird geladen");
	}

	@Override
	public void onEnable()
	{	
		Main.instance = this;
		PluginManager.loadCommands();
		PluginManager.loadListeners();
		ListenerPacketManager.load();
		Bukkit.getConsoleSender().sendMessage(Chat.prefix + "Plugin ist gestartet");
		if(!GreenLib.isSupported())
			Bukkit.getConsoleSender().sendMessage(Chat.prefix + "Die Version deines Server ist nicht Supported");
	}

	@Override
	public void onDisable() 
	{
		ListenerPacketManager.unLoad();
		Bukkit.getConsoleSender().sendMessage(Chat.prefix + "Plugin ist gestoppt");
	}

	public static Main getInstance()
	{
		return instance;
	}

	public PluginManager getPluginManager() 
	{
		return PluginManager;
	}

	public Config getDefaultConfig()
	{
		return DefaultConfig;
	}

	public ListenerPacketManager getListenerPacketManager() 
	{
		return ListenerPacketManager;
	}

}
