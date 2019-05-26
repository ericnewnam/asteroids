package main.se450.singletons;

public class PointsManager 
{
	private static PointsManager pointsManager= null;
	private volatile Integer runningTotal;
	
	static
	{
		pointsManager = new PointsManager();
	}
	
	private PointsManager()
	{
		runningTotal = 0;
	}
	
	public final static PointsManager getPointsManager()
	{
		return pointsManager;
	}
	
	public Integer getPoints()
	{
		return runningTotal;
	}
	
	public void setPoints(Integer i)
	{
		runningTotal += i;
	}
	
	public void resetPoints()
	{
		runningTotal = 0;
	}
}

