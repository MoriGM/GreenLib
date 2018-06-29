package de.morigm.greenlib.api.cpu;

public class CPU {
	
	public int CPUS()
	{
		return Runtime.getRuntime().availableProcessors();
	}

}
