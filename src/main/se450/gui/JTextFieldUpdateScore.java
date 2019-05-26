package main.se450.gui;

import javax.swing.JTextField;

import main.se450.interfaces.IObservable;
import main.se450.singletons.PointsManager;
import main.se450.singletons.ShipManager;

public class JTextFieldUpdateScore extends JTextField implements IObservable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void update() 
	{
		this.setText("Score: " + Integer.toString(PointsManager.getPointsManager().getPoints()) + 
				"  Ships: " + Integer.toString(ShipManager.getShipManager().getNumberOfShips()));
	}
}

