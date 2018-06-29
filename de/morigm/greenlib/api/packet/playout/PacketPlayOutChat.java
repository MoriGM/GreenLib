package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutChat implements PacketPlayOut {
	
	private String text;

	public PacketPlayOutChat(String text)
	{
		this.text = text;
	}

	public String getText() 
	{
		return text;
	}

}
