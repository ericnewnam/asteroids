package test.se450.test.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import main.se450.model.PlayerShip;

public class PlayerShipTest 
{
	@Test
	public void testGetMidpointX() 
	{
		PlayerShip ship = new PlayerShip(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship.getMidpointX1X3() == 125.0f);
		
		PlayerShip ship2 = new PlayerShip(35.0f, 15.0f, 70.0f, 200.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship2.getMidpointX1X3() == 52.5f);
		
		PlayerShip ship3 = new PlayerShip(-10.0f, 10.0f, 90.0f, 40.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship3.getMidpointX1X3() == 40.0f);
		
		PlayerShip ship4 = new PlayerShip(-10.0f, -10.0f, 0.0f, -40.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship4.getMidpointX1X3() == -5.0f);
	}

	@Test
	public void testGetMidpointY() 
	{
		PlayerShip ship = new PlayerShip(10.0f, 20.0f, 30.0f, 40.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship.getMidpointY1Y3() == 30.0f);
		
		PlayerShip ship2 = new PlayerShip(10.0f, 0.0f, 30.0f, 0.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship2.getMidpointY1Y3() == 0.0f);
		
		PlayerShip ship3 = new PlayerShip(10.0f, -10.0f, 40.0f, 90.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship3.getMidpointY1Y3() == 40.0f);
		
		PlayerShip ship4 = new PlayerShip(-10.0f, -10.0f, -50.0f, -50.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship4.getMidpointY1Y3() == -30.0f);
	}
	
	@Test
	public void testGetMidpointXWithBadValues()
	{
		PlayerShip ship = new PlayerShip(10.0f, 10.0f, 9.0f, 9.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship.getMidpointX1X3() == 9.5f);
		
		PlayerShip ship2 = new PlayerShip(200.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship2.getMidpointX1X3() == 100.0f);
		
		PlayerShip ship3 = new PlayerShip(-550.0f, -200.0f, -450.0f, -90.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship3.getMidpointX1X3() == -500.0f);
		
		PlayerShip ship4 = new PlayerShip(120.0f, 120.0f, -900.0f, -900.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship4.getMidpointX1X3() == -390.0f);
	}
	
	@Test
	public void testMidpointXAndYWithEqualValues()
	{
		PlayerShip ship = new PlayerShip(10.0f, 10.0f, 10.0f, 10.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship.getMidpointX1X3() == 10.0f);
		
		PlayerShip ship2 = new PlayerShip(10.0f, 90.0f, 10.0f, 90.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship2.getMidpointX1X3() == 10.0f);
		
		PlayerShip ship3 = new PlayerShip(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship3.getMidpointX1X3() == 0.0f);
		
		PlayerShip ship4 = new PlayerShip(-10.0f, -10.0f, -10.0f, -10.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship4.getMidpointX1X3() == -10.0f);
		
		PlayerShip ship5 = new PlayerShip(-10.0f, -10.0f, -10.0f, -10.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship5.getMidpointY1Y3() == -10.0f);
		
		PlayerShip ship6 = new PlayerShip(10.0f, 90.0f, 10.0f, 90.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship6.getMidpointY1Y3() == 90.0f);
	}
	
	@Test
	public void testMidPointXAndYWithDifferentValues()
	{
		PlayerShip ship = new PlayerShip(10.0f, 10.0f, 125.0f, 10.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship.getMidpointX1X3() == 67.5f);
		
		PlayerShip ship2 = new PlayerShip(35.0f, 15.0f, 70.0f, 200.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship2.getMidpointY1Y3() == 107.5f);
		
		PlayerShip ship3 = new PlayerShip(-10.0f, 10.0f, 90.0f, 40.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship3.getMidpointX1X3() == 40.0f);
		
		PlayerShip ship4 = new PlayerShip(-10.0f, -10.0f, 0.0f, -40.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship4.getMidpointX1X3() == -5.0f);
		
		PlayerShip ship5 = new PlayerShip(-10.0f, 10.0f, 90.0f, 40.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship5.getMidpointY1Y3() == 25.0f);
		
		PlayerShip ship6 = new PlayerShip(-10.0f, -10.0f, 0.0f, -40.0f, 0.0f, 0.0f, 0.0f, new Color(0), null);
		assertTrue(ship6.getMidpointY1Y3() == -25.0f);
	}
}
