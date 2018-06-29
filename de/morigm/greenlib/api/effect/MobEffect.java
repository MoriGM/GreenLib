package de.morigm.greenlib.api.effect;

public class MobEffect {
	
	private MobEffectList mobEffectList;
	private int i;
	private int x;
	private boolean parm1;
	private boolean parm2;

	public MobEffect(MobEffectList mobEffectList) 
	{
		this(mobEffectList, 0, 0, false, true);
	}
	
	public MobEffect(MobEffectList effectList,int i) 
	{
		this(effectList, i, 0, false, true);
	}
	
	public MobEffect(MobEffectList mobEffectList, int i, int x) 
	{
		this(mobEffectList, i, x, false, true);
	}

	public MobEffect(MobEffectList mobEffectList,int i,int x,boolean parm1,boolean parm2) 
	{
		this.mobEffectList = mobEffectList;
		this.i = i;
		this.x = x;
		this.parm1 = parm1;
		this.parm2 = parm2;
	}

	public MobEffectList getMobEffectList()
	{
		return mobEffectList;
	}

	public int getI()
	{
		return i;
	}

	public int getX()
	{
		return x;
	}

	public boolean isParm1()
	{
		return parm1;
	}

	public boolean isParm2() 
	{
		return parm2;
	}

}
