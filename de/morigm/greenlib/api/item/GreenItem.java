package de.morigm.greenlib.api.item;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.morigm.greenlib.api.item.nbt.NBTTag;

public interface GreenItem {
	
	public ItemStack createItemStack();
	public void setDisplayName(String name);
	public String getDisplayName();
	public boolean hasDisplayName();
	public void setAmount(int amount);
	public int getAmount();
	public void setMeta(short meta);
	public short getMeta();
	public void setTag(NBTTag tag);
	public boolean hasTag();
	public void setLore(String[] lore);
	public void setLore(List<String> lore);
	public List<String> getLore();
	public boolean hasLore();
	public void setType(Material material);
	public Material getType();

}
