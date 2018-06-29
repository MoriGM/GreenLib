package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutCloseWindow implements PacketPlayOut {
	
	private int window;

	public PacketPlayOutCloseWindow(int paramint) 
	{
		this.window = paramint;
	}

	public int getWindow() 
	{
		return window;
	}

}
