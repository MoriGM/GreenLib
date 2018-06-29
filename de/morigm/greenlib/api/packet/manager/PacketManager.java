package de.morigm.greenlib.api.packet.manager;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.morigm.greenlib.api.packet.annatation.PacketHandler;
import de.morigm.greenlib.api.packet.listener.PacketListener;
import de.morigm.greenlib.api.packet.types.PacketPlayIn;
import de.morigm.greenlib.api.packet.types.ServerPlayOut;

public class PacketManager {
	
	List<PacketListener> list = new ArrayList<>();
	
	public void registerListener(PacketListener l)
	{
		list.add(l);
	}
	
	public void onPacket(PacketPlayIn packet)
	{
		for(PacketListener l : list)
		{
			Class<?> c = l.getClass();
			for(Method m : c.getMethods())
			{
				if(has(m))
				{
					try 
					{
						m.invoke(l, packet);
					}
					catch (IllegalAccessException | IllegalArgumentException e) 
					{
					}
					catch (InvocationTargetException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void onPacket(ServerPlayOut packet)
	{
		for(PacketListener l : list)
		{
			Class<?> c = l.getClass();
			for(Method m : c.getMethods())
			{
				if(has(m))
				{
					try 
					{
						m.invoke(l, packet);
					}
					catch (IllegalAccessException | IllegalArgumentException e) 
					{
					}
					catch (InvocationTargetException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private boolean has(Method m)
	{
		if(m.getAnnotations().length == 0)
			return false;
		for(Annotation a : m.getAnnotations())
		{
			if(a instanceof PacketHandler)
			{
				return true;
			}
		}
		return false;
	}

}
