 package de.morigm.greenlib.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import de.morigm.greenlib.api.conversation.Conversation;
import de.morigm.greenlib.api.gui.GUI;

public class Config {
	
	public HashMap<Player,GUI> guis = new HashMap<>();
	
	//Skin Load
	public List<Player> skins = new ArrayList<>();

	//Name Load
	public List<Player> names = new ArrayList<>();
	
	//Conversations List
	public List<Conversation> conversations = new ArrayList<>();
}
