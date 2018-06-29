package de.morigm.greenlib.api.packet.playin.sync;

import org.bukkit.entity.Player;

import de.morigm.greenlib.api.enums.EnumHand;
import de.morigm.greenlib.api.packet.types.PacketPlayIn;

public class PacketPlayInArmAnimation implements PacketPlayIn {
	
private EnumHand a;
private Player p;
	
	public void a(EnumHand enumhand)
	{
		this.a = enumhand;
	}
	
	public EnumHand getHand()
	{
	    return this.a;
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
