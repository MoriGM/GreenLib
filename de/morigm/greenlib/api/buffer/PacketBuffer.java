package de.morigm.greenlib.api.buffer;

import java.util.Date;
import java.util.UUID;

public interface PacketBuffer {
	
	public void writeString(String s);
	public void writeInteger(int i);
	public void writeShort(Short s);
	public void writeByte(byte b);
	public void writeLong(long l);
	public void writeChar(char c);
	public void writeUUID(UUID uuid);
	public void writeDate(Date date);
	public String readString();
	public Integer readInteger();
	public Short readShort();
	public Byte readByte();
	public Long readLong();
	public char readChar();
	public UUID readUUID();
	public Date readDate();
	
}
