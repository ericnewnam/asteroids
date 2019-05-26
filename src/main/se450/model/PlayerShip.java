package main.se450.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.BadStrategyException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.factories.ShapeFactory;
import main.se450.factories.StrategyFactory;
import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.singletons.Configuration;
import main.se450.singletons.ProjectileManager;
import main.se450.singletons.ShipManager;
import main.se450.singletons.SoundManager;

/**
 * A class that graphically represents the space ship
 * 
 * @author Eric Newnam
 * @version 2.0 June 2 2017
 * 
 */
public class PlayerShip extends ShapeDroid implements IObservable
{
	/**
	 * Numerical value that represents friction, slowing down the motion of the ship.
	 */
	private Float friction = Configuration.getConfiguration().getFriction();
	
	/**
	 * Numerical value passed to a velocity function, and representing amount of thrust.
	 */
	private Float thrust = Configuration.getConfiguration().getForwardThrust();
	
	/**
	 * Amount the player's ship rotates with each key press.
	 */
	private Float leftright = Configuration.getConfiguration().getLeftright();
	
	/**
	 * The width of the projectile fired from the ship.
	 */
	private Float shotwidth = Configuration.getConfiguration().getShotDiameter();
	
	/**
	 * The amount the ship's projectiles move with each update call.
	 */
	private Float shotSpeed = Configuration.getConfiguration().getShotSpeed();
	
	/**
	 * A numerical value that allows a fine adjustment of the projectile velocity.
	 */
	private final Float projectileSlowdownFactor = 0.6f;
	
	/**
	 * The diameter of the circle representing the player ship's shield.
	 */
	private final Float shieldDiameter = 80.0f;
	
	/**
	 * A boolean value indicating that the player ship's shield is on or off.
	 */
	private volatile boolean shieldActive = false;
	
	/**
	 * The Ship constructor.
	 * 
	 * @param nLeft 	The left x coordinate of the bounding shape.
	 * @param nTop 		The top y coordinate of the bounding shape.
	 * @param nRight 	The right x coordinate of the bounding shape.
	 * @param nBottom 	The bottom y coordinate of the bounding shape.
	 * @param nX 		The x direction. The value determines the speed.
	 * @param nY		The y direction. The value determines the speed.
	 * @param nRotation	The rotational direction as an angle from 0.0.
	 * @param cColor	The RGB color value as a Color object.
	 * @param iStrategy	The strategy describing the way the shape moves or interacts.
	 * 
	 */
	public PlayerShip(float nLeft, float nTop, float nRight, float nBottom, 
			float nX, float nY, float nRotation, Color cColor, IStrategy iStrategy)
	{
		super(nLeft, nTop, nRight, nBottom, nX, nY, nRotation, cColor, iStrategy);
		setFriction(friction);
    }
	
	/**
	 * Draws the PlayerShip and the Shield on the graphics object.
	 * 
	 * @param graphics The graphics object on which to draw the lines.
	 */
	@Override
	public void draw(Graphics graphics) 
	{
    	super.draw(graphics);
    	if (shieldActive)
    	{
    		createShield(graphics);
    	}
    }
	
	/**
	 * Shoots off a projectile object based on the angle of the Player Ship.
	 * Adds the projectile to the ProjectileManager.
	 * 
	 */
	public void shoot()
	{
		Float xTip = getMidpointX1X2();  // tip of ship x
		Float yTip = getMidpointY1Y2();  // tip of ship y
		Float xRear = getMidpointX1X3(); // rear of ship y
		Float yRear = getMidpointY1Y3(); // rear of ship y
		
		// top left x of shot
		Float nLeft = xTip - (shotwidth * 0.5f);
		// top left y of shot
		Float nTop = yTip - shotwidth;
		// bottom right x of shot
		Float nRight = xTip + (shotwidth * 0.5f);
		// bottom right y of shot
		Float nBottom = yTip - 0.0f;
		
		IStrategy shotStrategy = null;
		IShape projectile = null;
		try 
		{
			shotStrategy = StrategyFactory.makeStrategy("PassThru");
			projectile = ShapeFactory.makeShape("Projectile", nLeft, nTop, nRight, nBottom, 
					getFX(), getFY(), 0.0f, getColor(), shotStrategy);
		} 
		catch (UnsupportedShapeException e) 
		{
			System.out.println("Exception: Unsupported Shape.");
		}
		catch (BadShapeException e1)
		{
			System.out.println("Exception: Bad Shape.");
		}
		catch (BadStrategyException e2)
		{
			System.out.println("Exception: Bad Strategy.");
		}
		
		// shoot the projectile
		Float xVector = (xTip - xRear) * projectileSlowdownFactor;
		Float yVector = (yTip - yRear) * projectileSlowdownFactor;
		ProjectileManager.getProjectileManager().shoot(projectile, xVector, yVector);
		
	}
	
	/**
	 * Rotates the Player Ship in increments offset from 0.0 degrees.
	 * 
	 * @param direction The string describing rotational direction from 0.0.
	 * 
	 */
	public void rotateShip(String direction)
	{
		if (direction.equals("left"))
		{
			setRotation(-leftright);
		}
		else if (direction.equals("right"))
		{
			setRotation(leftright);
		}
		else if (direction.equals("stop"))
		{
			setRotation(0.0f);
		}
	}
	
	/**
	 * Propels the ship forward or backward.
	 * 
	 * @param direction String describing either forward, reverse, or stop.
	 */
	public void thrust(String direction)
	{
		if (direction.equals("forward"))
		{
			fooVelocity(thrust);
		}
		else if (direction.equals("reverse"))
		{
			fooVelocity(-thrust);
		}
		else if (direction.equals("stop"))
		{
			
		}
	}
	
	/**
	 * Creates an explosion object after the player ship is destroyed.
	 * Plays a sound, then adds the explosion to the ShipManager, 
	 * and finally resets the ship back to the center of the screen.
	 */
	public void blowUpShip()
	{
		// create the explosion
		ShipExplosion shipExplosion = new ShipExplosion(getX1(), getY1(), getX3(), getY3(), getX(), getY(), 
				getRotation(), getColor(), getStrategy());
		ShipManager.getShipManager().addExplosion(shipExplosion);
		
		SoundManager.getSoundManager().explosion();
		
		// reset the players ship back to center
		ShipManager.getShipManager().ResetPlayerShip();
		
	}
	
	/**
	 * Resets the velocity of the ship back to 0.0.
	 */
	public void stopShip()
	{
		fooVelocity(0.0f);
	}
	
	/**
	 * Method called by an observer at regular intervals to 
	 * update the position and check for object collisions
	 * on screen.
	 */
	@Override
	public void update()
	{
		super.update();
		if (ShipManager.getShipManager().collisionCheck(this))
		{
			if (shieldActive == false)
			{
				blowUpShip();
			}
		}
	}
	
	/**
	 * Method to get the state of the ship's shield.
	 * 
	 * @return the boolean state of the ship's shield.
	 */
	public boolean getShieldActive()
	{
		return shieldActive;
	}
	
	/**
	 * Method to set the state of the ship's shield.
	 * 
	 * @param s The boolean value to set.
	 */
	public void setShieldActive(boolean s)
	{
		shieldActive = s;
	}
	
	/**
	 * Toggles the current boolean state of the ship's
	 * shield from on to off or vice versa.
	 */
	public void toggleShieldActive()
	{
		shieldActive = !shieldActive;
	}
	
	/**
	 * Method to create the shield around the player ship
	 * object. Represented by a circle.
	 * 
	 * @param g The graphics object where the shape output is drawn.
	 */
	public void createShield(Graphics g)
	{
		IShape shield = new Circle(getX1(), getY1(), getX3(), getY3(), getX(), getY(), 
				getRotation(), new Color(-16711936), getStrategy());
		shield.draw(g);
		SoundManager.getSoundManager().shield();
	}
	
	/**
	 * Method to randomly place the ship at a different location
	 * on the screen.
	 */
	public void hyperSpace()
	{
		//	x1        = nLeft;
		//	y1        = nTop;
		//	x2        = nRight;
		//	y2        = nTop;
		//	x3        = nRight;
		//	y3        = nBottom;
		//	x4        = nLeft;
		//	y4        = nBottom;
		Random random = new Random();
		Float pivot = random.nextFloat() * 300.0f; // to make value much greater than 1
		setX1(getX1() + pivot);
		setY1(getY1() + pivot);
		setX2(getX2() + pivot);
		setY2(getY2() + pivot);
		setX3(getX3() + pivot);
		setY3(getY3() + pivot);
		setX4(getX4() + pivot);
		setY4(getY4() + pivot);
	}
}

