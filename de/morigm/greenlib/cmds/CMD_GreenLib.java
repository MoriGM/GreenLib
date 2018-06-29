package de.morigm.greenlib.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.morigm.greenlib.chat.Chat;

public class CMD_GreenLib implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender com, Command cmd, String label, String[] args) 
	{
		if(com.hasPermission("greenlib.greenlib"))
		{
			com.sendMessage(Chat.prefix + "Plugin Version:" + Chat.version);
			com.sendMessage(Chat.prefix + "Plugin Build:" + Chat.build);
		}
		else
			com.sendMessage(Chat.no_permission);
		return false;
	}

}
