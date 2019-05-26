package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
//import java.awt.geom.Line2D;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.Configuration;
import main.se450.singletons.ProjectileManager;

public class Projectile extends Shape implements IObservable
{
	private	Ellipse2D circle = new Ellipse2D.Float(0.0f,0.0f,0.0f,0.0f);
	private Float shotSpeed = Configuration.getConfiguration().getShotSpeed();
	private Integer shotLifeTime = Configuration.getConfiguration().getShotLifetime();
	private Float xMultiplier = 0.0f;
	private Float yMultiplier = 0.0f;
	
	public Projectile(float nLeft, float nTop, float nRight, float nBottom, 
			float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
	}
	
	@Override
	public void draw(Graphics graphics) 
	{
  		circle.setFrame(getMinX(), getMinY(), getWidth(), getHeight());
  		
  		Graphics2D g2d = (Graphics2D)(graphics);
  		
  		g2d.setColor(getColor());
  		g2d.draw(circle);
	}
	
	@Override
	public void update()
	{
		if (shotLifeTime.equals(0))
		{
			// remove this projectile from the list
			ProjectileManager.getProjectileManager().expire(this);
		}
		else
		{
			super.update();
			translateXY(xMultiplier, yMultiplier);
			if (ProjectileManager.getProjectileManager().collisionCheck(this))
			{
				System.out.println("HIT");
			}
			shotLifeTime -= 1;
		}
	}
	
	public Float getXMultiplier()
	{
		return xMultiplier;
	}
	
	public Float getYMultiplier()
	{
		return yMultiplier;
	}
	
	public void setXMultiplier(Float xm)
	{
		xMultiplier = xm;
	}
	
	public void setYMultiplier(Float ym)
	{
		yMultiplier = ym;
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
