package de.morigm.greenlib.api.enums;

public enum EquipmentType {
	
	MAINHAND(0),
	OFFHAND(0),
	HAND(0),
	FEET(1),
	LEGS(2),
	CHEST(3),
	HEAD(4);
	
	private int id;

	private EquipmentType(int id) 
	{
		this.id = id;
	}

	public int getID()
	{
		return id;
	}

}
