package de.morigm.greenlib.api.buffer.v1_9_R2;

import java.util.Date;
import java.util.UUID;

import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_9_R2.PacketDataSerializer;

public class PacketBuffer implements de.morigm.greenlib.api.buffer.PacketBuffer{
	
	private PacketDataSerializer packetDataSerializer;

	public PacketBuffer() 
	{
		this.packetDataSerializer = new PacketDataSerializer(Unpooled.buffer());
	}

	public PacketBuffer(PacketDataSerializer packetDataSerializer)
	{
		this.packetDataSerializer = packetDataSerializer;
	}
	
	@Override
	public void writeString(String s) 
	{
		packetDataSerializer.a(s);
	}

	@Override
	public void writeInteger(int i) 
	{
		packetDataSerializer.writeInt(i);
	}

	@Override
	public void writeShort(Short s) 
	{
		packetDataSerializer.writeShort(s);
	}

	@Override
	public void writeByte(byte b) 
	{
		packetDataSerializer.writeByte(b);
	}

	@Override
	public void writeLong(long l) 
	{
		packetDataSerializer.writeLong(l);
	}

	@Override
	public void writeChar(char c) 
	{
		packetDataSerializer.writeChar(c);
	}

	@Override
	public void writeUUID(UUID uuid) 
	{
		packetDataSerializer.a(uuid);
	}

	@Override
	public void writeDate(Date date)
	{
		packetDataSerializer.writeLong(date.getTime());
	}

	@Override
	public String readString() 
	{
		return packetDataSerializer.e(1048576);
	}

	@Override
	public Integer readInteger() 
	{
		return packetDataSerializer.readInt();
	}

	@Override
	public Short readShort() 
	{
		return packetDataSerializer.readShort();
	}

	@Override
	public Byte readByte() 
	{
		return packetDataSerializer.readByte();
	}

	@Override
	public Long readLong() 
	{
		return packetDataSerializer.h();
	}

	@Override
	public char readChar() 
	{
		return packetDataSerializer.getChar(1048576);
	}

	@Override
	public UUID readUUID() 
	{
		return packetDataSerializer.i();
	}

	@Override
	public Date readDate() 
	{
		return new Date(readLong());
	}

	public PacketDataSerializer getPacketDataSerializer()
	{
		return packetDataSerializer;
	}

}
