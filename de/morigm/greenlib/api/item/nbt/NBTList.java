package de.morigm.greenlib.api.item.nbt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NBTList {
	
	List<NBTTag> list = new ArrayList<>();
	
	public void add(NBTTag tag)
	{
		list.add(tag);
	}
	
	public Collection<NBTTag> getTags()
	{
		return list;
	}
	
	public void remove(NBTTag tag)
	{
		list.remove(tag);
	}

}
