package de.morigm.greenlib.api.packet.playout;

import org.bukkit.inventory.ItemStack;

import de.morigm.greenlib.api.enums.EquipmentType;

public class PacketPlayOutEntityEquipment {
	
	private int entityid;
	private EquipmentType equipmentType;
	private ItemStack item;

	public PacketPlayOutEntityEquipment(int entityid,EquipmentType equipmentType,ItemStack item) 
	{
		this.entityid = entityid;
		this.equipmentType = equipmentType;
		this.item = item;
	}

	public int getEntityID() 
	{
		return entityid;
	}

	public EquipmentType getEquipmentType()
	{
		return equipmentType;
	}

	public ItemStack getItemStack()
	{
		return item;
	}

}
