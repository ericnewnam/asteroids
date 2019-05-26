package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStrategy;

public class ShipExplosion extends Shape implements IObservable
{
	private	Ellipse2D circle = new Ellipse2D.Float(0.0f,0.0f,0.0f,0.0f);
	private Integer timeToLive;
	private final Float expansionFactor = 2.0f;
	
	public ShipExplosion(float nLeft, float nTop, float nRight, float nBottom, 
			float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
		timeToLive = 80;
	}
	
	@Override
	public void draw(Graphics graphics) 
	{
  		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
  		
  		Graphics2D g2d = (Graphics2D)(graphics);
  		
  		g2d.setColor(getColor());
  		g2d.draw(circle);
	}
	
	public Integer getTimeToLive()
	{
		return timeToLive;
	}
	
	@Override
	public void update()
	{
		super.update();
		//System.out.println(timeToLive);
		// adjust the values and expand the circle
		setX1(getX1() - expansionFactor);
		setY1(getY1() - expansionFactor);
		setX2(getX2() + expansionFactor);
		setY2(getY2() - expansionFactor);
		setX3(getX3() + expansionFactor);
		setY3(getY3() + expansionFactor);
		setX4(getX4() - expansionFactor);
		setY4(getY4() + expansionFactor);
		
		if (timeToLive > 0)
			timeToLive -= 1;
	}
	
	@Override
	public float getMinX() 
	{
		return getCenterX() - getRadius();
	}
	
	@Override
	public float getMinY() 
	{
		return getCenterY() - getRadius();
	}

	@Override
	public float getMaxX() 
	{
		return getCenterX() + getRadius();
	}

	@Override
	public float getMaxY() 
	{
		return getCenterY() + getRadius();
	}
	
	public float getCenterX()
	{
		return getMidpointX1X3();
	}

	public float getCenterY()
	{
		return getMidpointY1Y3();
	}

	public float getRadius()
	{
		return getWidth() * 0.5f; //getWidth == getHeight for circle
	}
}

