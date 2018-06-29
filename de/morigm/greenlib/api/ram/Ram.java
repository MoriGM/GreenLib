package de.morigm.greenlib.api.ram;

public class Ram {
	
	public static enum MemoryType
	{
		GigaByte,MegaByte,KiloByte,Byte;
	}
	
	
	public long getMaxMemory(MemoryType mt)
	{
		long count = Runtime.getRuntime().maxMemory();
		if(mt.equals(MemoryType.Byte))
		{
			return count;
		}
		if(mt.equals(MemoryType.KiloByte))
		{
			return count / 1024;
		}
		if(mt.equals(MemoryType.MegaByte))
		{
			return count / (1024 * 1024);
		}
		if(mt.equals(MemoryType.GigaByte))
		{
			return count / (1024 * 1024 * 1024);
		}
		return count;
	}
	
	public long getFreeMemory(MemoryType mt)
	{
		long count = Runtime.getRuntime().freeMemory();
		if(mt.equals(MemoryType.Byte))
		{
			return count;
		}
		if(mt.equals(MemoryType.KiloByte))
		{
			return count / 1024;
		}
		if(mt.equals(MemoryType.MegaByte))
		{
			return count / (1024 * 1024);
		}
		if(mt.equals(MemoryType.GigaByte))
		{
			return count / (1024 * 1024 * 1024);
		}
		return count;
	}
	
	public long getTotalMemory(MemoryType mt)
	{
		long count = Runtime.getRuntime().totalMemory();
		if(mt.equals(MemoryType.Byte))
		{
			return count;
		}
		if(mt.equals(MemoryType.KiloByte))
		{
			return count / 1024;
		}
		if(mt.equals(MemoryType.MegaByte))
		{
			return count / (1024 * 1024);
		}
		if(mt.equals(MemoryType.GigaByte))
		{
			return count / (1024 * 1024 * 1024);
		}
		return count;
	}
	
	public long getUsedMemory(MemoryType mt)
	{
		long max = Runtime.getRuntime().totalMemory();
		long free = Runtime.getRuntime().freeMemory();
		long count = max - free;
		if(mt.equals(MemoryType.Byte))
		{
			return count;
		}
		if(mt.equals(MemoryType.KiloByte))
		{
			return count / 1024;
		}
		if(mt.equals(MemoryType.MegaByte))
		{
			return count / (1024 * 1024);
		}
		if(mt.equals(MemoryType.GigaByte))
		{
			return count / (1024 * 1024 * 1024);
		}
		return count;
	}

}
