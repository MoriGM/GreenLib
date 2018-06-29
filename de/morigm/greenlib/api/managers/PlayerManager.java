package de.morigm.greenlib.api.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mojang.authlib.GameProfile;

import de.morigm.greenlib.api.GreenLib;
import de.morigm.greenlib.api.conversation.Conversation;
import de.morigm.greenlib.api.enums.DemoScreenType;
import de.morigm.greenlib.api.enums.EquipmentType;
import de.morigm.greenlib.api.gui.GUI;
import de.morigm.greenlib.api.listener.SignListener;
import de.morigm.greenlib.api.packet.types.PacketPlayOut;

public abstract class PlayerManager {
	
	protected Player player;

	public PlayerManager(Player p)
	{
		this.player = p;
	}
	
	public abstract void sendMessage(String s);
	
	public abstract void sendMessage(String... s);
	
	public abstract void sendActionBar(String s);
	
	public abstract int getPing();
	
	public abstract void openGUI(GUI gui);
	
	public abstract void openSignGui(SignListener sl);
	
	public abstract void sendEquipment(Entity e,EquipmentType type,ItemStack item);
	
	public abstract void openDemoScreen(DemoScreenType tpye);
	
	public abstract void openEndScreen();
	
	public abstract void sendPacket(PacketPlayOut packet);
	
	public abstract void setName(String name);
	
	public abstract void removeName();
	
	public abstract void setSkin(UUID uuid);
	
	public abstract void removeSkin();
	
	public abstract void setSkinAndName(String name);
	
	public abstract void removeSkinAndName();
	
	public abstract GameProfile getGameProfile();
	
	public boolean isInConversation()
	{
		for(Conversation conv : GreenLib.getConversations())
			if(conv.getPlayers().contains(player))
				return true;
		return false;
	}
	
	public List<Conversation> getConversations()
	{
		List<Conversation> list = new ArrayList<>();
		for(Conversation conv : GreenLib.getConversations())
			if(conv.getPlayers().contains(player))
				list.add(conv);
		return list;
	}
	
}
