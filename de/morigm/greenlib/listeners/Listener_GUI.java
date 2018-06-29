package de.morigm.greenlib.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import de.morigm.greenlib.Main;
import de.morigm.greenlib.api.gui.Button;
import de.morigm.greenlib.api.gui.GUI;

public class Listener_GUI implements Listener {
	
	@EventHandler
	public void on(InventoryClickEvent e)
	{
		try 
		{
			if(e.getClickedInventory().getHolder() == null)
			{
				for(GUI gui : Main.getInstance().getDefaultConfig().guis.values())
				{
						try 
						{
							Player p = Bukkit.getPlayer(e.getWhoClicked().getName());
							if(gui.getPlayer().equals(p))
								if(gui.getSize() == e.getClickedInventory().getSize())
									if(gui.getTitle().equals(e.getClickedInventory().getTitle()))
										for(Button b : gui.getButtonList())
											if(e.getCurrentItem().getType().equals(b.item.getType()))
												if(e.getSlot() == e.getRawSlot())
													if(e.getCurrentItem().getItemMeta().hasDisplayName())
													{
														if(b.item.getItemMeta().hasDisplayName())
														{
															if(e.getCurrentItem().getItemMeta().getDisplayName().equals(b.item.getItemMeta().getDisplayName()))
															{
																if(gui.getPermission().isEmpty())
																{
																	gui.onButton(b);
																	e.setCancelled(true);
																}
																else
																{
																	if(p.hasPermission(gui.getPermission()))
																	{
																		gui.onButton(b);
																		e.setCancelled(true);
																	}
																	else
																	{
																		e.setCancelled(true);
																	}
																}
															}
														}
													}
													else
													{
														if(gui.getPermission().isEmpty())
														{
															gui.onButton(b);
															e.setCancelled(true);
														}
														else
														{
															if(p.hasPermission(gui.getPermission()))
															{
																gui.onButton(b);
															}
															else
															{
																e.setCancelled(true);
															}
														}
													}
												
						} 
						catch (Exception exe) 
						{
							exe.printStackTrace();
						}
				}
			}
		
		}
		catch (Exception exe) 
		{
			exe.printStackTrace();
		}
		openInv(e);
	}

	private void openInv(InventoryClickEvent e)
	{
		try 
		{
			Player p = Bukkit.getPlayer(e.getWhoClicked().getName());
			Inventory inv = p.getOpenInventory().getTopInventory();
			for(GUI gui : Main.getInstance().getDefaultConfig().guis.values())
			{
				if(gui.getTitle().equals(inv.getTitle()))
					if(gui.getPlayer().equals(p))
						if(gui.getSize() == inv.getSize())
							e.setCancelled(true);
			}
		} 
		catch (Exception exe) 
		{
			exe.printStackTrace();
		}
	}

}
