package de.morigm.greenlib.api;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.morigm.greenlib.Main;
import de.morigm.greenlib.api.buffer.PacketBuffer;
import de.morigm.greenlib.api.conversation.Conversation;
import de.morigm.greenlib.api.cpu.CPU;
import de.morigm.greenlib.api.item.GreenItem;
import de.morigm.greenlib.api.managers.ChatManager;
import de.morigm.greenlib.api.managers.ConsolenManager;
import de.morigm.greenlib.api.managers.PlayerManager;
import de.morigm.greenlib.api.packet.manager.PacketManager;
import de.morigm.greenlib.api.ram.Ram;
import de.morigm.greenlib.api.translator.Translator;
import de.morigm.greenlib.chat.Chat;

public class GreenLib {
	
	private static ChatManager chatmanager;
	private static PacketManager packetmanager;
	private static ConsolenManager consolenmanager;
	private static Ram ram;
	
	private static Map<Player,PlayerManager> playermanagers = new HashMap<>();
	private static CPU cpu;
	
	private static String[] versions = new String[] {"v1_8_R3", "v1_9_R1", "v1_9_R2", "v1_10_R1", "v1_11_R1", "v1_12_R1"};
	
	public static String getGreenLibVersion()
	{
		return Chat.version;
	}
	
	public static String getServerVersion()
	{
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		return version;
	}
	
	public static ConsolenManager getConsolenManager()
	{
		if(GreenLib.consolenmanager == null)
			GreenLib.consolenmanager = new ConsolenManager();
		return GreenLib.consolenmanager;
	}
	
	public static PlayerManager getPlayerManager(Player p)
	{
		PlayerManager playermanager = null;
		if(playermanagers.containsKey(p))
			return playermanagers.get(p);
		if(GreenLib.getServerVersion().equals("v1_8_R3"))
			playermanager = new de.morigm.greenlib.api.managers.v1_8_R3.PlayerManager(p);
		if(GreenLib.getServerVersion().equals("v1_9_R1"))
			playermanager =  new de.morigm.greenlib.api.managers.v1_9_R1.PlayerManager(p);
		if(GreenLib.getServerVersion().equals("v1_9_R2"))
			playermanager =  new de.morigm.greenlib.api.managers.v1_9_R2.PlayerManager(p);
		if(GreenLib.getServerVersion().equals("v1_10_R1"))
			playermanager =  new de.morigm.greenlib.api.managers.v1_10_R1.PlayerManager(p);
		if(GreenLib.getServerVersion().equals("v1_11_R1"))
			playermanager =  new de.morigm.greenlib.api.managers.v1_11_R1.PlayerManager(p);
		if(GreenLib.getServerVersion().equals("v1_12_R1"))
			playermanager =  new de.morigm.greenlib.api.managers.v1_12_R1.PlayerManager(p);
		playermanagers.put(p, playermanager);
		return playermanager;
	}
	
	public static ChatManager getChatManager()
	{
		if(GreenLib.chatmanager == null)
			GreenLib.chatmanager = new ChatManager();
		return GreenLib.chatmanager;
	}
	
	public static PacketManager getPacketManager()
	{
		if(GreenLib.packetmanager == null)
			GreenLib.packetmanager = new PacketManager();
		return GreenLib.packetmanager;
	}

	public static Ram getRam() 
	{
		if(GreenLib.ram == null)
			GreenLib.ram = new Ram();
		return GreenLib.ram;
	}
	
	public static CPU getCPU() 
	{
		if(GreenLib.cpu == null)
			GreenLib.cpu = new CPU();
		return GreenLib.cpu;
	}
	
	public static void addCoversation(Conversation conversation)
	{
		if(!Main.getInstance().getDefaultConfig().conversations.contains(conversation))
			Main.getInstance().getDefaultConfig().conversations.add(conversation);
	}
	
	public static void removeCoversation(Conversation conversation)
	{
		if(Main.getInstance().getDefaultConfig().conversations.contains(conversation))
			Main.getInstance().getDefaultConfig().conversations.remove(conversation);
	}
	
	public static List<Conversation> getConversations()
	{
		return Main.getInstance().getDefaultConfig().conversations;
	}
	
	public static PacketBuffer createPacketBuffer()
	{
		PacketBuffer packetBuffer = null;
		if(GreenLib.getServerVersion().equals("v1_8_R3"))
			packetBuffer = new de.morigm.greenlib.api.buffer.v1_8_R3.PacketBuffer();
		if(GreenLib.getServerVersion().equals("v1_9_R1"))
			packetBuffer =  new de.morigm.greenlib.api.buffer.v1_9_R1.PacketBuffer();
		if(GreenLib.getServerVersion().equals("v1_9_R2"))
			packetBuffer =  new de.morigm.greenlib.api.buffer.v1_9_R2.PacketBuffer();
		if(GreenLib.getServerVersion().equals("v1_10_R1"))
			packetBuffer =  new de.morigm.greenlib.api.buffer.v1_10_R1.PacketBuffer();
		if(GreenLib.getServerVersion().equals("v1_11_R1"))
			packetBuffer =  new de.morigm.greenlib.api.buffer.v1_11_R1.PacketBuffer();
		if(GreenLib.getServerVersion().equals("v1_12_R1"))
			packetBuffer =  new de.morigm.greenlib.api.buffer.v1_12_R1.PacketBuffer();
		return packetBuffer;
	}
	
	public static String[] getSupportedVersions()
	{
		return versions;
	}
	
	public static boolean isSupported()
	{
		boolean flag = false;
		for(String s : versions)
			if(getServerVersion().equals(s))
				flag = true;
		return flag;
	}
	
	public static Translator getTranslator(Plugin plugin, File langsDir,String langName)
	{
		return new Translator(plugin, langsDir, langName);
	}
	
	public static GreenItem createGreenItem(Material material)
	{
		return createGreenItem(material, 1,(short) 0, null, null);
	}
	
	public static GreenItem createGreenItem(Material material,int amount)
	{
		return createGreenItem(material, amount, (short) 0, null, null);
	}
	
	public static GreenItem createGreenItem(Material material,short meta)
	{
		return createGreenItem(material, 1,meta, null, null);
	}
	
	public static GreenItem createGreenItem(Material material, String name)
	{
		return createGreenItem(material, 1, (short) 0, name, null);
	}
	public static GreenItem createGreenItem(Material material, List<String> lore)
	{
		return createGreenItem(material, 1, (short) 0, null, lore);
	}
	
	public static GreenItem createGreenItem(Material material, int amount, short meta)
	{
		return createGreenItem(material, amount, meta, null, null);
	}
	
	public static GreenItem createGreenItem(Material material, int amount, short meta, String name)
	{
		return createGreenItem(material, amount, meta, name, null);
	}
	
	public static GreenItem createGreenItem(Material material, int amount, short meta, List<String> lore)
	{
		return createGreenItem(material, amount, meta, null, lore);
	}
	
	public static GreenItem createGreenItem(Material material,int amount,short meta,String name,List<String> lore)
	{
		GreenItem greenItem = null;
		if(GreenLib.getServerVersion().equals("v1_8_R3"))
			greenItem = new de.morigm.greenlib.api.item.v1_8_R3.GreenItem(material, amount, meta, name, lore);
		if(GreenLib.getServerVersion().equals("v1_9_R1"))
			greenItem = new de.morigm.greenlib.api.item.v1_9_R1.GreenItem(material, amount, meta, name, lore);
		if(GreenLib.getServerVersion().equals("v1_9_R2"))
			greenItem = new de.morigm.greenlib.api.item.v1_9_R2.GreenItem(material, amount, meta, name, lore);
		if(GreenLib.getServerVersion().equals("v1_10_R1"))
			greenItem = new de.morigm.greenlib.api.item.v1_10_R1.GreenItem(material, amount, meta, name, lore);
		if(GreenLib.getServerVersion().equals("v1_11_R1"))
			greenItem = new de.morigm.greenlib.api.item.v1_11_R1.GreenItem(material, amount, meta, name, lore);
		if(GreenLib.getServerVersion().equals("v1_12_R1"))
			greenItem = new de.morigm.greenlib.api.item.v1_12_R1.GreenItem(material, amount, meta, name, lore);
		return greenItem;
	}
	

}
