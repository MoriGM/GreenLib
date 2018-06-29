package de.morigm.greenlib.api.packet.playin.async;

import org.bukkit.entity.Player;

import de.morigm.greenlib.api.enums.EnumHand;
import de.morigm.greenlib.api.packet.types.PacketPlayIn;

public class AsyncPacketPlayInArmAnimation implements PacketPlayIn {
	
	private EnumHand a;
	private boolean cancelled;
	private Player p;
	
	public void a(EnumHand enumhand)
	{
	    this.a = enumhand;
	}
	
	public EnumHand getHand()
	{
	    return this.a;
	}
	
	public boolean isCanceled() 
	{
		return cancelled;
	}

	public void setCancelled(boolean canceled) 
	{
		this.cancelled = canceled;
	}
	
	@Override
	public Player getPlayer() 
	{
		return p;
	}
	
	@Override
	public void setPlayer(Player p) 
	{
		this.p = p;
	}

}
