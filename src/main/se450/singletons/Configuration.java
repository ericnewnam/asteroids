package main.se450.singletons;

import java.awt.Color;

public class Configuration 
{
	private static Configuration configuration = null;
	
	private Integer framesPerSecond;
	private Integer repeatKeySpeed;
	private Integer width;
	private Integer height;
	private Float shipWidth;
	private Float shipHeight;
	private Float shotSpeed;
	private Float shotDiameter;
	private Integer shotLifetime;
	private Float forwardThrust;
	private Float reverseThrust;
	private Float friction;
	private Float leftright;
	private Color colorShip;
	private String borders;
	private Integer blownShapeMultiplier = 3;
	private boolean startGame = false;
	
	static
	{
		configuration = new Configuration();
	}
	
	private Configuration()
	{
	}
	
	public void setConfiguration(Integer fps, Integer rks, Integer wid, Integer hgt, 
			Float shw, Float shh, Float shs, Float shd, Integer shl, Float fwt, Float rvt,
			Float frc, Float lr, Color col, String brd)
	{
		framesPerSecond = fps;
		repeatKeySpeed = rks;
		width = wid;
		height = hgt;
		shipWidth = shw;
		shipHeight = shh;
		shotSpeed = shs;
		shotDiameter = shd;
		shotLifetime = shl;
		forwardThrust = fwt;
		reverseThrust = rvt;
		friction = frc;
		leftright = lr;
		colorShip = col;
		borders = brd;
	}
	
	public final static Configuration getConfiguration()
	{
		return configuration;
	}

	public Integer getFramesPerSecond() 
	{
		return framesPerSecond;
	}

	public Integer getRepeatKeySpeed() 
	{
		return repeatKeySpeed;
	}

	public Integer getWidth() 
	{
		return width;
	}

	public Integer getHeight() 
	{
		return height;
	}

	public Float getShipWidth() 
	{
		return shipWidth;
	}

	public Float getShipHeight() 
	{
		return shipHeight;
	}

	public Float getShotSpeed() 
	{
		return shotSpeed;
	}

	public Float getShotDiameter() 
	{
		return shotDiameter;
	}

	public Integer getShotLifetime() 
	{
		return shotLifetime;
	}

	public Float getForwardThrust() 
	{
		return forwardThrust;
	}

	public Float getReverseThrust() 
	{
		return reverseThrust;
	}

	public Float getFriction() 
	{
		return friction;
	}

	public Float getLeftright() 
	{
		return leftright;
	}

	public Color getColorShip() 
	{
		return colorShip;
	}

	public String getBorders() 
	{
		return borders;
	}
	
	public Integer getBlownShapeMultiplier()
	{
		return blownShapeMultiplier;
	}
	
	public boolean getStartGame()
	{
		return startGame;
	}
	
	public void setStartGame(boolean value)
	{
		startGame = value;
	}
}

