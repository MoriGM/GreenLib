package de.morigm.greenlib.api.packet.playout;

import de.morigm.greenlib.api.combat.CombatTracker;
import de.morigm.greenlib.api.packet.types.PacketPlayOut;
import net.minecraft.server.v1_12_R1.PacketPlayOutCombatEvent.EnumCombatEventType;

public class PacketPlayOutCombatEvent implements PacketPlayOut {
	
	private CombatTracker combatTracker;
	private EnumCombatEventType type;
	private boolean paramBoolean;

	public PacketPlayOutCombatEvent(CombatTracker combatTracker,EnumCombatEventType type)
	{
		this(combatTracker,type,true);
	}
	
	public PacketPlayOutCombatEvent(CombatTracker combatTracker, EnumCombatEventType type, boolean paramBoolean)
	{
		this.combatTracker = combatTracker;
		this.type = type;
		this.paramBoolean= paramBoolean;
	}

	public CombatTracker getCombatTracker()
	{
		return combatTracker;
	}

	public EnumCombatEventType getType() 
	{
		return type;
	}

	public boolean is() 
	{
		return paramBoolean;
	}

}
