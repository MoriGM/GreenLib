package de.morigm.greenlib.api.npc.entity;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class NPCEntity {
	
	public abstract void addPlayer(Player p);
	
	public abstract void removePlayer(Player p);
	
	public abstract List<Player> getPlayers();
	
	public abstract void setGlowing(boolean b);
	
	public abstract boolean isGlowing();
	
	public abstract void teleport(Location loc);
	
	public abstract void move(int x,int y, int z);
	
	public abstract int getID();
	
	public abstract void setInvisible(boolean b);
	
	public abstract boolean isInvisible();

}
