package de.morigm.greenlib.api.boss;

import java.util.UUID;

import de.morigm.greenlib.api.enums.BarColor;
import de.morigm.greenlib.api.enums.BarStyle;

public class BossBattle {
	
	private BarColor color;
	private UUID uuid;
	private BarStyle style;
	private String text;
	private float progress;

	public BossBattle(BarColor color,UUID uuid,BarStyle barstyle,String text,float progress) 
	{
		this.color = color;
		this.uuid = uuid;
		this.style = barstyle;
		this.text = text;
		this.progress = progress;
	}

	public BarColor getColor() 
	{
		return color;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public BarStyle getStyle()
	{
		return style;
	}

	public String getText()
	{
		return text;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) 
	{
		this.progress = progress;
	}
	
}
