package de.morigm.greenlib.api.gui;

import org.bukkit.inventory.ItemStack;

public class Button {
	
	public ItemStack item;
	public int id;
	private int slot = -1;

	public Button(ItemStack item,int id) 
	{
		this.id = id;
		this.item = item;
	}

	public int getSlot() 
	{
		return slot;
	}

	public void setSlot(int slot) 
	{
		this.slot = slot;
	}


}
