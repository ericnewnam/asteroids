package main.se450.singletons;

import java.util.ArrayList;
import java.util.Iterator;

import main.se450.collision.Collide;
import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShape;
import main.se450.model.Projectile;
import main.se450.model.Shape;

/**
 * Class that acts a coordinator of the projectile objects fired
 * off by the player's ship.
 * 
 * @author Eric Newnam
 * @version 2.0 June 2 2017
 *
 */
public class ProjectileManager implements IObservable
{
	/**
	 * The field representing the single instance of this class.
	 */
	private static volatile ProjectileManager projectileManager = null;
	
	/**
	 * The collection holding the list of projectiles to draw.
	 */
	private volatile ArrayList<IShape> projectileList = null;
	
	/**
	 * Static initializer creating the single instance of this class.
	 */
	static
	{
		projectileManager = new ProjectileManager();
	}
	
	/**
	 * Constructor creates a new instance of the list of projectiles.
	 */
	private ProjectileManager()
	{
		projectileList = new ArrayList<IShape>();
	}
	
	/**
	 * The accessor method for the single instance.
	 * 
	 * @return The single instance of the ProjectileManager.
	 */
	public static final ProjectileManager getProjectileManager()
	{
		return projectileManager;
	}
	
	/**
	 * Accessor for the list of projectiles.
	 * 
	 * @return The list of projectile objects as a collection.
	 */
	public final ArrayList<IShape> getProjectiles()
	{
		return new ArrayList<IShape>(projectileList);
	}
	
	/**
	 * Method to fire off projectile objects from the player's ship.
	 * 
	 * @param shot			The shape that will get shot from the tip of the ship.
	 * @param xMultiplier	How much to translate the shot in the x direction per update.
	 * @param yMultiplier	How much to translate the shot in the y direction per update.
	 */
	public final void shoot(IShape shot, Float xMultiplier, Float yMultiplier)
	{
		((Projectile) shot).setXMultiplier(xMultiplier);
		((Projectile) shot).setYMultiplier(yMultiplier);
		addProjectileToManager(shot);
	}
	
	/**
	 * Method that takes a projectile object and adds it to a collection
	 * that will be drawn to the screen.
	 * 
	 * @param shot The projectile object to add to the collection.
	 */
	private void addProjectileToManager(IShape shot)
	{
		projectileList.add(shot);
		// Motion.startObserving((IObservable) shot);
	}
	
	/**
	 * Method that removes a projectile object from the collection
	 * that is drawn to screen.
	 * 
	 * @param shot The projectile object to remove from the collection.
	 */
	public void expire(IShape shot)
	{
		// Motion.stopObserving((IObservable) shot); this freezes the system
		projectileList.remove(shot);
	}
	
	/**
	 * Method to check for collisions between projectiles and other shape objects.
	 * 
	 * @param shot The projectile object to check collisions against.
	 * @return The boolean value indicating a shape collision.
	 */
	public boolean collisionCheck(IShape shot) 
	{
		if (!projectileList.contains(shot))
		{
			return false;
		}
		
		ArrayList<IShape> iShapess = ShapeList.getShapeList().getShapes();
		ArrayList<IShape> iShapes = new ArrayList<>(iShapess);
		if (iShapes != null)
		{
			Iterator<IShape> iiShapes = iShapes.iterator();
			while(iiShapes.hasNext())
			{
				IShape eachShape = iiShapes.next();
				if (eachShape != null)
				{
					if (Collide.collided(((Projectile) shot).getSides(), ((Shape) eachShape).getSides())) // collided
					{
						// invoke the explosion of the shape
						((Shape) eachShape).explodeAndSpawnNewShapes();
						
						// remove the shape and projectile from the list
						iShapes.remove(eachShape);
						projectileList.remove(shot);
						return true;
					}
				}
			}
			// set the new reduced list of shapes
			ShapeList.getShapeList().setShapesList(iShapes);
		}
		return false;
	}

	@Override
	public void update() 
	{
		ArrayList<IShape> iShapesProjectiles = new ArrayList<>(projectileList);
		if (iShapesProjectiles != null)
		{
			// foreach shot in projectile list do
			Iterator<IShape> iShapesIterator = iShapesProjectiles.iterator();
			while (iShapesIterator.hasNext())
			{
				IShape shot = iShapesIterator.next();
				ShapeList.getShapeList().addShape(shot);
			}
		}
	}
}

