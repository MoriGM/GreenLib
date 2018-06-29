package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.buffer.PacketBuffer;
import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutCustomPayload implements PacketPlayOut {
	
	private String channel;
	private PacketBuffer packetBuffer;

	public PacketPlayOutCustomPayload(String channel,PacketBuffer packetBuffer) 
	{
		this.channel = channel;
		this.packetBuffer = packetBuffer;
	}

	public String getChannel() 
	{
		return channel;
	}

	public PacketBuffer getPacketBuffer() 
	{
		return packetBuffer;
	}

}
