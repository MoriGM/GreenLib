package de.morigm.greenlib.api.managers;

import de.morigm.greenlib.api.chat.ChatColor;

public class ChatManager {
	
	public String createPrefix(ChatColor chatcolor,String pluginname)
	{
		return ChatColor.GRAY + "[" + chatcolor + pluginname + ChatColor.GRAY +  "] ";
	}
	
	public String StringBuilder(String[] args)
	{
		String s = "";
		for(int i = 0;i < args.length;i++)
		{
			if(s.isEmpty())
			{
				s = args[i];
			}
			else
			{
				s = s + " " + args[i];
			}
		}
		return s;
	}
	
	public String StringBuilder(String[] args,int start)
	{
		String s = "";
		for(int i = start;i < args.length;i++)
		{
			if(s.isEmpty())
			{
				s = args[i];
			}
			else
			{
				s = s + " " + args[i];
			}
		}
		return s;
	}
	
	public String StringBuilder(String[] args,int start,int stop)
	{
		String s = "";
		for(int i = start;i < stop;i++)
		{
			if(s.isEmpty())
			{
				s = args[i];
			}
			else
			{
				s = s + " " + args[i];
			}
		}
		return s;
	}
	
	public String ChatColorTranslater(char colorchar,String s)
	{
		return s.replace(String.valueOf(colorchar), "§");
	}
	
	public String ChatColorTranslater(String colorchar,String s)
	{
		return s.replace(String.valueOf(colorchar), "§");
	}

}
