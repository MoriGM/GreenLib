package de.morigm.greenlib.api.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class GUI {
	
	private Player player;
	private List<Button> buttons = new ArrayList<>();
	private int size;
	private String title;
	private Inventory inv;
	private String perm = "";

	public GUI(Player p) 
	{
		this.player = p;
	}
	
	public abstract void load();
	
	public void onButton(Button b){}
	
	public void addButton(Button b)
	{
		buttons.add(b);
	}
	
	public void addButton(Button b,int slot)
	{
		b.setSlot(slot);
		buttons.add(b);
	}
	
	public void loadButtons()
	{
		Inventory inv = Bukkit.createInventory(null, getSize(), getTitle());
		List<Button> buttons_left = new ArrayList<>();
		for(Button b : buttons)
		{
			if(b.getSlot() >= 0)
			{
				inv.setItem(b.getSlot(), b.item);
			}
			else
			{
				buttons_left.add(b);
			}
		}
		for(Button b : buttons_left)
		{
			inv.addItem(b.item);
		}
		this.inv = inv;
	}

	public Player getPlayer() 
	{
		return player;
	}

	public int getSize() 
	{
		return size;
	}

	public void setSize(int size) 
	{
		this.size = size;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public void closeGui()
	{
		player.closeInventory();
	}

	public List<Button> getButtonList()
	{
		return buttons;
	}
	
	public void open()
	{
		player.openInventory(inv);
	}

	public String getPermission() {
		return perm;
	}

	public void setPermission(String perm) {
		this.perm = perm;
	}
	
}
