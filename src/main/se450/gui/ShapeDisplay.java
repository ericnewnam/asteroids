package main.se450.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import main.se450.interfaces.IObservable;
import main.se450.interfaces.IShape;
import main.se450.model.ShipExplosion;
import main.se450.singletons.DisplayManager;
import main.se450.singletons.ProjectileManager;
import main.se450.singletons.ShapeList;
import main.se450.singletons.ShipManager;

/**
 * Class that displays the shape objects to screen.
 * 
 * @author Eric Newnam
 * @version 2.0 June 2 2017
 *
 */
public class ShapeDisplay extends JPanel implements IObservable
{
  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShapeDisplay()
	{
	}
	
	/**
	 * Method that validates the tree.
	 */
	@Override
	public void validateTree()
	{
		super.validateTree();

		Dimension dimension = getSize();
		
		DisplayManager.getDisplayManager().setDisplaySize(dimension.width, dimension.height);
	}
	
	/**
	 * Method that handles the painting of shape objects, the player ship,
	 * and projectiles to the graphics object.
	 * 
	 * @param graphics The graphics object that the shapes will be output to.
	 */
	public void paint(Graphics graphics) 
  	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, DisplayManager.getDisplayManager().getWidth(), DisplayManager.getDisplayManager().getHeight());
		
		final ArrayList<IShape> iShapeList = ShapeList.getShapeList().getShapes(); // clone ?
		ArrayList<IShape> iShClone = new ArrayList<>(iShapeList);
		if (iShClone != null)
		{
			Iterator<IShape> iiShapes = iShClone.iterator();
			while (iiShapes.hasNext())
			{
				IShape iShape = iiShapes.next();
				if (iShape != null)
				{
					iShape.update();
					iShape.draw(graphics);
				}
			}
		}
		
		// get the first player ship
		// update and draw it
		ShipManager.getShipManager().getPlayerShip().update();
		ShipManager.getShipManager().getPlayerShip().draw(graphics);
		
		// draw and update the projectiles
		ArrayList<IShape> iProjectileList = ProjectileManager.getProjectileManager().getProjectiles();
		ArrayList<IShape> iProjectileListClone = new ArrayList<>(iProjectileList);
		if (iProjectileListClone != null)
		{
			Iterator<IShape> iiProjectles = iProjectileListClone.iterator();
			while (iiProjectles.hasNext())
			{
				IShape iShape = iiProjectles.next();
				if (iShape != null)
				{
					iShape.update();
					iShape.draw(graphics);
				}
			}
		}
		
		// test the TTL for explosions here
		ArrayList<IShape> iExplosions = ShipManager.getShipManager().getShapeExplosionList();
		ArrayList<IShape> iExplosionsList = new ArrayList<>(iExplosions);
		try
		{
			if (!iExplosionsList.isEmpty())
			{
				for (IShape iShape : iExplosionsList)
				{
					ShipExplosion se = (ShipExplosion) iShape;
					Integer i = se.getTimeToLive();
					if (i.equals(0))
					{
						iExplosionsList.remove((IShape)se);
					}
				}
			}
		}
		catch (Exception e)
		{
			
		}
		
		// draw the explosions if they are still TTL > 0
		//ArrayList<IShape> iExplosionsList = ShipManager.getShipManager().getShapeExplosionList();
		ArrayList<IShape> iExplosionsListClone = new ArrayList<>(iExplosionsList);
		if (iExplosionsListClone != null)
		{
			Iterator<IShape> iiExplosions = iExplosionsListClone.iterator();
			while (iiExplosions.hasNext())
			{
				IShape iShape = iiExplosions.next();
				if (iShape != null)
				{
					iShape.update();
					iShape.draw(graphics);
				}
			}
		}
		// set the new smaller explosions list
		ShipManager.getShipManager().setShapeExplosionList(iExplosionsListClone);
    }
	
	/**
	 * Method to repaint the graphics object at regular intervals
	 * with the shape objects.
	 */
	@Override
	public void update() 
	{
		repaint();
	}
}
      