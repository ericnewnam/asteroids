package main.se450.singletons;

import java.util.ArrayList;

import main.se450.interfaces.IShape;

public class ShieldManager 
{
	private static ShieldManager shieldManager= null;
	private ArrayList<IShape> shieldList = null;
	
	static
	{
		shieldManager = new ShieldManager();
	}
	
	private ShieldManager()
	{
		shieldList = new ArrayList<IShape>();
	}
	
	public final static ShieldManager getShieldManager()
	{
		return shieldManager;
	}
	
	public void setShieldList(ArrayList<IShape> list)
	{
		shieldList = list;
	}
	
	public ArrayList<IShape> getShieldList()
	{
		return shieldList;
	}
	
	public void addShield(IShape sh)
	{
		shieldList.add(sh);
	}
}

