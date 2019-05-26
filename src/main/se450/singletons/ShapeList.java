package main.se450.singletons;

import java.util.ArrayList;

import main.se450.interfaces.IShape;

public class ShapeList
{
	private static ShapeList shapeList = null;
	
	private volatile ArrayList<IShape> iShapes = null;
	private volatile ArrayList<IShape> iShapesBlownUp = null;
	
	static
	{
		shapeList = new ShapeList();
	}
	
    private ShapeList()
    {
    	iShapes = new ArrayList<IShape>();
    	iShapesBlownUp = new ArrayList<IShape>();
    }
    
	public final static ShapeList getShapeList() 
	{
		return shapeList;
	}
	
	public synchronized final ArrayList<IShape> getShapes()
	{
		//return new ArrayList<IShape>(iShapes);
		return iShapes;
	}
	
	public void addShapes(final ArrayList<IShape> iShapeList)
	{
		iShapes.addAll(iShapeList);
	}
	
	public void setShapesList(ArrayList<IShape> iShapeList)
	{
		iShapes = iShapeList;
	}
	
	public void addShape(IShape shape)
	{
		iShapes.add(shape);
	}
	
	public void addShapeBlownUp(IShape shape)
	{
		iShapesBlownUp.add(shape);
	}
	
	public ArrayList<IShape> getShapesBlownUp()
	{
		return iShapesBlownUp;
	}
}

