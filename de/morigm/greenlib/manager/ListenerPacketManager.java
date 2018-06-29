package de.morigm.greenlib.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.morigm.greenlib.api.GreenLib;
import de.morigm.greenlib.packetlistener.ListenerPacket;

public class ListenerPacketManager {
	
	List<ListenerPacket> list = new ArrayList<>();
	
	public void load()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			ListenerPacket lp = getListenerPacket(p);
			if(!lp.isInit())
				lp.init();
			list.add(lp);
		}
	}
	
	public void unLoad()
	{
		for(ListenerPacket lp : list)
			if(lp.isInit())
				lp.close();
	}
	
	public ListenerPacket getListenerPacket(Player p)
	{
		ListenerPacket ListenerPacket = null;
		if(GreenLib.getServerVersion().equals("v1_8_R3"))
			ListenerPacket = new de.morigm.greenlib.packetlistener.v1_8_R3.ListenerPacket(p);
		if(GreenLib.getServerVersion().equals("v1_9_R1"))
			ListenerPacket =  new de.morigm.greenlib.packetlistener.v1_9_R1.ListenerPacket(p);
		if(GreenLib.getServerVersion().equals("v1_9_R2"))
			ListenerPacket =  new de.morigm.greenlib.packetlistener.v1_9_R2.ListenerPacket(p);
		if(GreenLib.getServerVersion().equals("v1_10_R1"))
			ListenerPacket =  new de.morigm.greenlib.packetlistener.v1_10_R1.ListenerPacket(p);
		if(GreenLib.getServerVersion().equals("v1_11_R1"))
			ListenerPacket =  new de.morigm.greenlib.packetlistener.v1_11_R1.ListenerPacket(p);
		if(GreenLib.getServerVersion().equals("v1_12_R1"))
			ListenerPacket =  new de.morigm.greenlib.packetlistener.v1_12_R1.ListenerPacket(p);
		return ListenerPacket;
	}

	public List<ListenerPacket> getListenerPackets() 
	{
		return list;
	}

}
