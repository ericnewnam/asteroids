package main.se450.factories;

import java.awt.Color;

import main.se450.exceptions.BadShapeException;
import main.se450.exceptions.UnsupportedShapeException;
import main.se450.interfaces.IShape;
import main.se450.interfaces.IStrategy;
import main.se450.model.Circle;
import main.se450.model.PlayerShip;
import main.se450.model.Projectile;
import main.se450.model.Ship;
import main.se450.model.Square;
import main.se450.model.Triangle;

/**
 * Auxillary class that creates specific shapes given an input type.
 * 		The objects all share the same IShape interface.
 * 
 * @author ericnewnam
 * @version 2.0 June 2 2017.
 * 
 */
public class ShapeFactory
{
	private ShapeFactory()
	{
	}
	
	/**
	 * Static method that creates the desired shape object.
	 * 		The current legal shapes are: circle, square,
	 * 		line, triangle, playership and projectile.
	 * 
	 * @param type 				The type of shape object to create.
	 * @param nLeft				The left x coordinate of the bounding shape.
	 * @param nTop				The top y coordinate of the bounding shape.
	 * @param nRight			The right x coordinate of the bounding shape.
	 * @param nBottom			The bottom y coordinate of the bounding shape.
	 * @param x					The x direction. The value determines the speed.
	 * @param y					The y direction. The value determines the speed.
	 * @param rotation			The rotational direction as an angle from 0.0.
	 * @param cColor			The RGB color value as an integer.
	 * @param iStrategy			The strategy describing the way the shape interacts.
	 * 
	 * @return 					The desired shape object upcast as an IShape.
	 * 
	 * @throws BadShapeException If the type value input does not correlate with 
	 * 								any known createable shape by the factory.
	 * @throws UnsupportedShapeException	If trying to create a Line object.
	 */
	public static IShape makeShape(final String type, float nLeft, float nTop, float nRight, float nBottom, float x, float y, float rotation, Color cColor, IStrategy iStrategy) throws BadShapeException, UnsupportedShapeException
	{
		IShape iShape = null;

    	if (type.equals("Circle"))
    	{
    		iShape = new Circle(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    	}
        else if (type.equals("Square"))
    	{
    		iShape = new Square(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    	}
    	else if (type.equals("Line"))
    	{
    		throw new UnsupportedShapeException(type);
    	}
    	else if (type.equals("Triangle"))
    	{
    		iShape = new Triangle(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    	}
    	else if (type.equals("Ship"))
    	{
    		iShape = new Ship(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    	}
    	else if (type.equals("PlayerShip"))
    	{
    		iShape = new PlayerShip(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    	}
    	else if (type.equals("Projectile"))
    	{
    		iShape = new Projectile(nLeft, nTop, nRight, nBottom, x, y, rotation, cColor, iStrategy);
    	}
    	else
    	{
    		throw new BadShapeException(type);
    	}
		
		return iShape;
	}
}
      