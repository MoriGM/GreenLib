package de.morigm.greenlib.api.translator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.bukkit.plugin.Plugin;

public class Translator {
	
	private Properties prop;
	private File langsDir;
	private Plugin plugin;
	private File file;

	public Translator(Plugin plugin, File langsDir,String langName) 
	{
		this.plugin = plugin;
		this.langsDir = langsDir;
		if(!langsDir.exists())
			langsDir.mkdirs();
		File file = new File(langsDir, langName);
		this.file = file;
	}
	
	public String getTranslation(String key)
	{
		if(prop == null)
		{
			prop = new Properties();
			try 
			{
				prop.load(new FileInputStream(file));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return prop == null ? key : prop.getProperty(key);
	}
	
	public void copyFilesFromPlugin(String folder, String[] langs)
	{
		try 
		{
			for(String s : langs)
			{
				File file = new File(langsDir, s);
				InputStream in = plugin.getResource(folder + "/" + s);
				FileOutputStream out = new FileOutputStream(file);
				if(file.exists())
				{
					BufferedReader reader_jar = new BufferedReader(new InputStreamReader(in));
					BufferedReader reader_system = new BufferedReader(new FileReader(file));
					List<String> keys = new ArrayList<>();
					List<String> old = new ArrayList<>();
					while (reader_system.ready())
						keys.add(reader_system.readLine().split("=")[0]);
					reader_system.close();
					while(reader_jar.ready())
					{
						String read = reader_jar.readLine();
						if(!keys.contains(read.split("=")[0]))
							old.add(read);
					}
					reader_jar.close();
					BufferedReader reader = new BufferedReader(new FileReader(file));
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					while(reader.ready())
					{
						writer.write(reader.readLine());
						writer.newLine();
					}
					for(String s1 : old)
					{
						writer.write(s1);
						writer.newLine();
					}
					reader.close();
					writer.close();
				}
				else
				{
					IOUtils.copy(in, out);
				}
				in.close();
				out.close();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
}
