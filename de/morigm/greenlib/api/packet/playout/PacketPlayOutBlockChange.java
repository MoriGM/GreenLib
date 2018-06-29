package de.morigm.greenlib.api.packet.playout;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutBlockChange implements PacketPlayOut {
	
	private World world;
	private Location location;
	private byte data;
	private Material material;

	public PacketPlayOutBlockChange(World w,Location loc,Material material,byte data) 
	{
		this.world = w;
		this.location = loc;
		this.material = material;
		this.data = data;
	}
	
	@SuppressWarnings("deprecation")
	public PacketPlayOutBlockChange(World w,Location loc,int id,byte data) 
	{
		this.world = w;
		this.location = loc;
		this.material = Material.getMaterial(id);
		this.data = data;
	}

	public World getWorld() 
	{
		return world;
	}

	public Location getLocation() 
	{
		return location;
	}

	public byte getData() 
	{
		return data;
	}

	public Material getMaterial()
	{
		return material;
	}

}
