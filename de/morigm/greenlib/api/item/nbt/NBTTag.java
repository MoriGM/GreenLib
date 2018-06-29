package de.morigm.greenlib.api.item.nbt;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NBTTag {
	
	private Map<String, String> strings = new HashMap<>();
	private Map<String, Short> shorts = new HashMap<>();
	private Map<String, Byte> bytes = new HashMap<>();
	private Map<String, Boolean> booleans = new HashMap<>();
	private Map<String, Long> longs = new HashMap<>();
	private Map<String, NBTTag> nbttags = new HashMap<>();
	private Map<String, Integer> integers = new HashMap<>();
	private Map<String, Byte[]> bytesarray = new HashMap<>();
	private Map<String, Integer[]> integersarray = new HashMap<>();
	private Map<String, Double> doubles = new HashMap<>();
	private Map<String, Float> floats = new HashMap<>();
	private Map<String, NBTList> list = new HashMap<>();
	
	public void setString(String key,String value)
	{
		strings.put(key, value);
	}
	
	public String getString(String key)
	{
		return strings.get(key);
	}
	
	public void setShort(String key,Short value)
	{
		shorts.put(key, value);
	}
	
	public Short getShort(String key)
	{
		return shorts.get(key);
	}
	
	public void setByte(String key,Byte value)
	{
		bytes.put(key, value);
	}
	
	public Byte getByte(String key)
	{
		return bytes.get(key);
	}
	
	public void setBoolean(String key,Boolean value)
	{
		booleans.put(key, value);
	}
	
	public Boolean getBoolean(String key)
	{
		return booleans.get(key);
	}
	
	public void setLong(String key,Long value)
	{
		longs.put(key, value);
	}
	
	public Long getLong(String key)
	{
		return longs.get(key);
	}
	
	public void setNBTTag(String key,NBTTag value)
	{
		nbttags.put(key, value);
	}
	
	public NBTTag getNBTTag(String key)
	{
		return nbttags.get(key);
	}
	
	public void setInteger(String key,Integer value)
	{
		integers.put(key, value);
	}
	
	public Integer getInteger(String key)
	{
		return integers.get(key);
	}
	
	public void setByteArray(String key,Byte[] value)
	{
		bytesarray.put(key, value);
	}
	
	public Byte[] getByteArray(String key)
	{
		return bytesarray.get(key);
	}
	
	public void setIntegerArray(String key,Integer[] value)
	{
		integersarray.put(key, value);
	}
	
	public Integer[] getIntegerArray(String key)
	{
		return integersarray.get(key);
	}
	
	public void setDouble(String key,Double value)
	{
		doubles.put(key, value);
	}
	
	public Double getDouble(String key)
	{
		return doubles.get(key);
	}
	
	public void setFloat(String key,Float value)
	{
		floats.put(key, value);
	}
	
	public Float getFloat(String key)
	{
		return floats.get(key);
	}
	
	public void setList(String key,NBTList value)
	{
		list.put(key, value);
	}
	
	public NBTList getList(String key)
	{
		return list.get(key);
	}

	public Set<Entry<String, String>> getStrings() 
	{
		return strings.entrySet();
	}

	public Set<Entry<String, Short>> getShorts() 
	{
		return shorts.entrySet();
	}

	public Set<Entry<String, Byte>> getBytes() 
	{
		return bytes.entrySet();
	}

	public Set<Entry<String, Boolean>> getBooleans() 
	{
		return booleans.entrySet();
	}

	public Set<Entry<String, Long>> getLongs() 
	{
		return longs.entrySet();
	}

	public Set<Entry<String, NBTTag>> getNBTTags() 
	{
		return nbttags.entrySet();
	}
	
	public Set<Entry<String, Integer>> getIntegers() 
	{
		return integers.entrySet();
	}

	public Set<Entry<String, Integer[]>> getIntegerArrays() 
	{
		return integersarray.entrySet();
	}

	public Set<Entry<String, Byte[]>> getByteArrays() 
	{
		return bytesarray.entrySet();
	}
	
	public Set<Entry<String, Float>> getFloats() 
	{
		return floats.entrySet();
	}

	public Set<Entry<String, Double>> getDoubles() 
	{
		return doubles.entrySet();
	}
	
	public Set<Entry<String, NBTList>> getLists() 
	{
		return list.entrySet();
	}

}
