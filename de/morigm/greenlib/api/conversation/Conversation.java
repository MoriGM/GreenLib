package de.morigm.greenlib.api.conversation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.morigm.greenlib.api.chat.ChatColor;
import de.morigm.greenlib.api.events.conversation.PlayerConversationAddEvent;
import de.morigm.greenlib.api.events.conversation.PlayerConversationRemoveEvent;
import de.morigm.greenlib.chat.Chat;

public class Conversation {
	
	private List<Player> players = new ArrayList<>();
	private String prefix;
	private ChatColor chatcolor;
	
	public Conversation(String prefix) 
	{
		this.prefix = prefix;
		this.chatcolor = ChatColor.GRAY;
	}
	
	public Conversation() 
	{
		this.prefix = Chat.prefix;
		this.chatcolor = ChatColor.GRAY;
	}
	
	public Conversation(String prefix,ChatColor cc) 
	{
		this.prefix = prefix;
		this.chatcolor = cc;
	}
	
	public Conversation(ChatColor cc) 
	{
		this.prefix = Chat.prefix;
		this.chatcolor = cc;
	}

	public List<Player> getPlayers()
	{
		return players;
	}

	public String getPrefix() 
	{
		return prefix;
	}
	
	public void addPlayer(Player p)
	{
		if(!players.contains(p))
		{
			PlayerConversationAddEvent event = new PlayerConversationAddEvent(p, this);
			Bukkit.getPluginManager().callEvent(event);
			if(!event.isCancelled())
				players.add(event.getPlayer());
		}
	}
	
	public void removePlayer(Player p)
	{
		if(players.contains(p))
		{
			PlayerConversationRemoveEvent event = new PlayerConversationRemoveEvent(p, this);
			Bukkit.getPluginManager().callEvent(event);
			if(!event.isCancelled())
				players.remove(event.getPlayer());
		}
	}
	
	public void sendMessage(Player MessageSender,String message)
	{
		for(Player p : players)
			p.sendMessage(this.prefix + ChatColor.GRAY + "<" + chatcolor + MessageSender.getName() + ChatColor.GRAY + ">" + " " + message);
	}
	
	public void sendMessage(String message)
	{
		for(Player p : players)
			p.sendMessage(this.prefix + message);
	}

}
