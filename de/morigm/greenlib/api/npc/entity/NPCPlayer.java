package de.morigm.greenlib.api.npc.entity;

import org.bukkit.inventory.ItemStack;

import com.mojang.authlib.GameProfile;

import de.morigm.greenlib.api.enums.EnumHand;
import de.morigm.greenlib.api.enums.EquipmentType;

public abstract class NPCPlayer extends NPCEntity {
	
	private String name;
	private GameProfile gameProfile;

	public NPCPlayer(String name,GameProfile gameProfile)
	{
		this.name = name;
		this.gameProfile = gameProfile;
	}
	
	public String getName()
	{
		return name;
	}

	public GameProfile getGameProfile() 
	{
		return gameProfile;
	}
	
	public abstract void setEquipment(EquipmentType type,ItemStack item);
	
	public abstract ItemStack getEquipment(EquipmentType type);
	
	public abstract ItemStack[] getEquipments();
	
	public abstract void swingArm(EnumHand hand);


}
