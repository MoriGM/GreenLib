package de.morigm.greenlib.api.item.v1_11_R1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.morigm.greenlib.api.item.nbt.NBTList;
import de.morigm.greenlib.api.item.nbt.NBTTag;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagList;

public class GreenItem implements de.morigm.greenlib.api.item.GreenItem {

	private NBTTag tag;
	private String name;
	private int amount;
	private short meta;
	private List<String> lore;
	private Material material;
	
	public GreenItem(Material material,int amount,short meta,String name,List<String> lore) 
	{
		this.material = material;
		this.amount = amount;
		this.meta = meta;
		this.name = name;
		this.lore = lore;
	}

	@Override
	public ItemStack createItemStack() 
	{
		ItemStack item = new ItemStack(material, amount, meta);
		net.minecraft.server.v1_11_R1.ItemStack itemmc = CraftItemStack.asNMSCopy(item);
		if(tag != null)
		{
			NBTTagCompound nbt = createNBT(tag);
			itemmc.setTag(nbt);
		}
		ItemStack itemstack = CraftItemStack.asBukkitCopy(itemmc);
		if(lore != null || name != null)
		{
			ItemMeta meta = itemstack.getItemMeta();
			if(lore != null)
				meta.setLore(lore);
			if(name != null)
				meta.setDisplayName(name);
			itemstack.setItemMeta(meta);
		}
		return itemstack;
	}

	@Override
	public void setDisplayName(String name) 
	{
		this.name = name;
	}

	@Override
	public String getDisplayName() 
	{
		return name;
	}

	@Override
	public boolean hasDisplayName() 
	{
		return name != null;
	}

	@Override
	public void setAmount(int amount) 
	{
		this.amount = amount;
	}

	@Override
	public int getAmount() 
	{
		return amount;
	}

	@Override
	public void setMeta(short meta) 
	{
		this.meta = meta;
	}

	@Override
	public short getMeta() 
	{
		return meta;
	}

	@Override
	public void setTag(NBTTag tag) 
	{
		this.tag = tag;
	}

	@Override
	public boolean hasTag() 
	{
		return tag != null;
	}

	@Override
	public void setLore(String[] lore) 
	{
		this.lore = new ArrayList<>();
		for(String s : lore)
			this.lore.add(s);
	}

	@Override
	public void setLore(List<String> lore) 
	{
		this.lore = lore;
	}

	@Override
	public List<String> getLore() 
	{
		return lore;
	}

	@Override
	public boolean hasLore() 
	{
		return lore != null;
	}

	@Override
	public void setType(Material material) 
	{
		this.material = material;
	}

	@Override
	public Material getType() 
	{
		return material;
	}
	
	private NBTTagCompound createNBT(NBTTag tag)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		for(Entry<String, String> entry : tag.getStrings())
			nbt.setString(entry.getKey(), entry.getValue());
		for(Entry<String, Boolean> entry : tag.getBooleans())
			nbt.setBoolean(entry.getKey(), entry.getValue());
		for(Entry<String, Long> entry : tag.getLongs())
			nbt.setLong(entry.getKey(), entry.getValue());
		for(Entry<String, Byte> entry : tag.getBytes())
			nbt.setByte(entry.getKey(), entry.getValue());
		for(Entry<String, Short> entry : tag.getShorts())
			nbt.setShort(entry.getKey(), entry.getValue());
		for(Entry<String, NBTTag> entry : tag.getNBTTags())
			nbt.set(entry.getKey(), createNBT(entry.getValue()));
		for(Entry<String, Integer> entry : tag.getIntegers())
			nbt.setLong(entry.getKey(), entry.getValue());
		for(Entry<String, Byte[]> entry : tag.getByteArrays())
			nbt.setByteArray(entry.getKey(), createByteArray(entry.getValue()));
		for(Entry<String, Integer[]> entry : tag.getIntegerArrays())
			nbt.setIntArray(entry.getKey(), createIntegerArray(entry.getValue()));
		for(Entry<String, Double> entry : tag.getDoubles())
			nbt.setDouble(entry.getKey(), entry.getValue());
		for(Entry<String, Float> entry : tag.getFloats())
			nbt.setFloat(entry.getKey(), entry.getValue());
		for(Entry<String, NBTList> entry : tag.getLists())
		{
			NBTTagList list = new NBTTagList();
			for(NBTTag tags : entry.getValue().getTags())
				list.add(createNBT(tags));
			nbt.set(entry.getKey(), list);
		}
		return nbt;
	}
	
	private byte[] createByteArray(Byte[] bytes)
	{
		byte[] byt = new byte[bytes.length];
		for(int i = 0;i < bytes.length;i++)
			byt[i] = bytes[i];
		return byt;
	}
	
	private int[] createIntegerArray(Integer[] in)
	{
		int[] ints = new int[in.length];
		for(int i = 0;i < in.length;i++)
			ints[i] = in[i];
		return ints;
	}

}
