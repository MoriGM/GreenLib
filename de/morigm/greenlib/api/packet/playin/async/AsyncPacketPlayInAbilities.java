package de.morigm.greenlib.api.packet.playin.async;

import org.bukkit.entity.Player;

import de.morigm.greenlib.api.packet.types.PacketPlayIn;

public class AsyncPacketPlayInAbilities implements PacketPlayIn {
	
	private boolean cancelled = false;
	private boolean d;
	private boolean c;
	private boolean b;
	private boolean a;
	private float f;
	private float e;
	private Player p;

	public boolean a()
	  {
	    return this.a;
	  }
	  
	  public void a(boolean paramBoolean)
	  {
	    this.a = paramBoolean;
	  }
	  
	  public boolean isFlying()
	  {
	    return this.b;
	  }
	  
	  public void b(boolean paramBoolean)
	  {
	    this.b = paramBoolean;
	  }
	  
	  public boolean c()
	  {
	    return this.c;
	  }
	  
	  public void c(boolean paramBoolean)
	  {
	    this.c = paramBoolean;
	  }
	  
	  public boolean d()
	  {
	    return this.d;
	  }
	  
	  public void d(boolean paramBoolean)
	  {
	    this.d = paramBoolean;
	  }
	  
	  public void a(float paramFloat)
	  {
	    this.e = paramFloat;
	  }
	  
	  public void b(float paramFloat)
	  {
	    this.f = paramFloat;
	  }
	  
	  public float b()
	  {
		  return this.e;
	  }
	  
	  public float e()
	  {
		  return this.f;
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
