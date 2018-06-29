package de.morigm.greenlib.api.events.conversation;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.morigm.greenlib.api.conversation.Conversation;

public class PlayerConversationAddEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private Conversation conversation;
	private boolean cancelled = false;
	
	public PlayerConversationAddEvent(Player player,Conversation conversation) 
	{
		this.player = player;
		this.conversation = conversation;
	}

	@Override
	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public Player getPlayer() 
	{
		return player;
	}

	public void setPlayer(Player player) 
	{
		this.player = player;
	}

	public Conversation getConversation()
	{
		return conversation;
	}

	public boolean isCancelled() 
	{
		return cancelled;
	}

	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}
	
	public static HandlerList getHandlerList() 
	{
        return handlers;

	}

}
