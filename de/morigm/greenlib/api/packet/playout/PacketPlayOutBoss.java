package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.boss.BossBattle;
import de.morigm.greenlib.api.enums.BossAction;
import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public class PacketPlayOutBoss implements PacketPlayOut {
	
	private BossAction action;
	private BossBattle battle;

	public PacketPlayOutBoss(BossAction action,BossBattle battle) 
	{
		this.action = action;
		this.battle = battle;
	}

	public BossAction getAction()
	{
		return action;
	}

	public BossBattle getBattle()
	{
		return battle;
	}

}
