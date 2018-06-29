package de.morigm.greenlib.api.packet.playout;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Entity;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutAttachEntity implements PacketPlayOut{
	
	private Entity a;
	private Entity b;
	private int c;

	//1.9 and higher
	public PacketPlayOutAttachEntity(Entity paramEntity1, @Nullable Entity paramEntity2)
	{
	    this.a = paramEntity1;
	    this.b = paramEntity2;
	    this.c = 0;
	}
	
	//1.8.7 and higher
	public PacketPlayOutAttachEntity(int i,Entity paramEntity1, @Nullable Entity paramEntity2)
	{
	    this.a = paramEntity1;
	    this.b = paramEntity2;
	    this.c = i;
	}

	public Entity a() 
	{
		return a;
	}

	public Entity b() 
	{
		return b;
	}

	public int c() 
	{
		return c;
	}

}
