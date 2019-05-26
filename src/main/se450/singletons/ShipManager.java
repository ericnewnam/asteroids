package main.se450.singletons;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import main.se450.collision.Collide;
import main.se450.exceptions.BadStrategyException;
import main.se450.factories.StrategyFactory;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.model.PlayerShip;
import main.se450.model.Shape;

public class ShipManager //implements IObservable
{
	private static ShipManager shipManager = null;
	
	private IShape ship = null;
	private ArrayList<IShape> shapeExplosionList = new ArrayList<>();
	
	private int NUMBER_OF_SHIPS = 10;
	
	private String borders = Configuration.getConfiguration().getBorders();
	private Color color = Configuration.getConfiguration().getColorShip();
	private Float forwardThrust = Configuration.getConfiguration().getForwardThrust();
	private Float leftRight = Configuration.getConfiguration().getLeftright();
	private Float reverseThrust = Configuration.getConfiguration().getReverseThrust();
	private Float shipHeight = Configuration.getConfiguration().getShipHeight();
	private Float shipWidth = Configuration.getConfiguration().getShipWidth();
	
	private Float x1 = (Configuration.getConfiguration().getWidth() / 2) - (shipWidth / 2);
	private Float y1 = (Configuration.getConfiguration().getHeight() / 2) - (shipHeight / 2);
	private Float x2 = (Configuration.getConfiguration().getWidth() / 2) + (shipWidth / 2);
	private Float y2 = (Configuration.getConfiguration().getHeight() / 2) + (shipHeight / 2);
	
	static
	{
		shipManager = new ShipManager();
	}
	
	private ShipManager()
	{
		ship = createShip();
	}
	
	public static ShipManager getShipManager()
	{
		return shipManager;
	}
	
	public void ResetPlayerShip()
	{
		// blowup()
		
		if (NUMBER_OF_SHIPS == 0)
		{
			// end the game
		}
		ship = createShip();
		decrementShips();
	}
	
	public Integer getNumberOfShips()
	{
		return NUMBER_OF_SHIPS;
	}
	
	public IShape getPlayerShip()
	{
		return ship;
	}
	
	public void setPlayerShip(IShape s)
	{
		ship = s;
	}
	
	private PlayerShip createShip()
	{
		// handle the bad strategy exception
		IStrategy s = null;
		try 
		{
			s = StrategyFactory.makeStrategy(borders);
		} 
		catch (BadStrategyException e) 
		{
			System.out.println("Exception: Bad Strategy " + borders);
		}
		
		return new PlayerShip(x1, y1, x2, y2, 0.0f, 0.0f, 0.0f, color, s);
	}
	
	public void decrementShips()
	{
		NUMBER_OF_SHIPS -= 1;
	}
	
	public boolean collisionCheck(IShape playerShip)
	{
		ArrayList<IShape> iShapesClone = ShapeList.getShapeList().getShapes();
		if (iShapesClone != null)
		{
			Iterator<IShape> iiShapes = iShapesClone.iterator();
			while(iiShapes.hasNext())
			{
				IShape eachShape = iiShapes.next();
				if (eachShape != null)
				{
					if (Collide.collided(((PlayerShip) playerShip).getSides(), ((Shape) eachShape).getSides())) // collided
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public ArrayList<IShape> getShapeExplosionList()
	{
		return new ArrayList<IShape>(shapeExplosionList);
	}
	
	public void setShapeExplosionList(ArrayList<IShape> el)
	{
		shapeExplosionList = el;
	}
	
	public void addExplosion(IShape s)
	{
		shapeExplosionList.add(s);
	}
}

