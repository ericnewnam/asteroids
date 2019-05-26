package test.se450.test.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.se450.exceptions.BadStrategyException;
import main.se450.factories.StrategyFactory;
import main.se450.interfaces.IStrategy;
import main.se450.model.PlayerShip;
import main.se450.singletons.DisplayManager;

public class ReboundStrategyTest 
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testExecuteLeftSideBounce() 
	{
		DisplayManager.getDisplayManager().setDisplaySize(400, 400);
		IStrategy reboundStrategy = null;
		try 
		{
			reboundStrategy = StrategyFactory.makeStrategy("Rebound");
		} 
		catch (BadStrategyException e) 
		{
			e.printStackTrace();
		}
		
		PlayerShip ship1 = new PlayerShip(0.0f, 10.0f, 40.0f, 50.0f, -1.0f, 0.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship1);
		assertTrue(ship1.getX() == -1.0f);
		
		PlayerShip ship2 = new PlayerShip(0.01f, 10.0f, 40.0f, 50.0f, -1.0f, 0.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship2);
		assertTrue(ship2.getX() == -1.0f);
		
		PlayerShip ship3 = new PlayerShip(0.01f, 10.0f, 40.0f, 50.0f, -10.0f, 0.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship3);
		assertTrue(ship3.getX() == -10.0f);
		
		PlayerShip ship4 = new PlayerShip(-0.01f, 10.0f, 40.0f, 50.0f, -1.0f, 0.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship4);
		assertTrue(ship4.getX() == 1.0f);
	}
	
	@Test
	public void testExecuteLeftCornerBounce()
	{
		DisplayManager.getDisplayManager().setDisplaySize(400, 400);
		IStrategy reboundStrategy = null;
		try 
		{
			reboundStrategy = StrategyFactory.makeStrategy("Rebound");
		} 
		catch (BadStrategyException e) 
		{
			e.printStackTrace();
		}
		
		PlayerShip ship1 = new PlayerShip(0.0f, 0.0f, 40.0f, 40.0f, -1.0f, -1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship1);
		assertTrue(ship1.getX() == -1.0f && ship1.getY() == -1.0f);
		
		PlayerShip ship2 = new PlayerShip(0.01f, 0.01f, 40.0f, 40.0f, -1.0f, -1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship2);
		assertTrue(ship2.getX() == -1.0f && ship2.getY() == -1.0f);
		
		PlayerShip ship3 = new PlayerShip(-0.01f, -0.01f, 40.0f, 40.0f, -1.0f, -1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship3);
		assertTrue(ship3.getX() == 1.0f && ship3.getY() == 1.0f);
		
		PlayerShip ship4 = new PlayerShip(-0.01f, -0.01f, 40.0f, 40.0f, -10.0f, -10.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship4);
		assertTrue(ship4.getX() == 10.0f && ship4.getY() == 10.0f);
	}
	
	@Test
	public void testExecuteRightCornerBounce()
	{
		DisplayManager.getDisplayManager().setDisplaySize(400, 400);
		IStrategy reboundStrategy = null;
		try 
		{
			reboundStrategy = StrategyFactory.makeStrategy("Rebound");
		} 
		catch (BadStrategyException e) 
		{
			e.printStackTrace();
		}
		
		PlayerShip ship1 = new PlayerShip(360.0f, 0.0f, 400.0f, 40.0f, 1.0f, -1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship1);
		assertTrue(ship1.getX() == 1.0f && ship1.getY() == -1.0f);
		
		PlayerShip ship2 = new PlayerShip(360.0f, -0.01f, 400.01f, 40.0f, 1.0f, -1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship2);
		assertTrue(ship2.getX() == -1.0f && ship2.getY() == 1.0f);
		
		PlayerShip ship3 = new PlayerShip(370.0f, -10.0f, 410.01f, 40.0f, 10.0f, -10.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship3);
		assertTrue(ship3.getX() == -10.0f && ship3.getY() == 10.0f);
		
		PlayerShip ship4 = new PlayerShip(370.0f, 0.0f, 400.01f, 40.0f, 10.0f, -10.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship4);
		assertTrue(ship4.getX() == -10.0f && ship4.getY() == -10.0f);
	}
	
	@Test
	public void testExecuteRightBottomBounce()
	{
		DisplayManager.getDisplayManager().setDisplaySize(400, 400);
		IStrategy reboundStrategy = null;
		try 
		{
			reboundStrategy = StrategyFactory.makeStrategy("Rebound");
		} 
		catch (BadStrategyException e) 
		{
			e.printStackTrace();
		}
		
		PlayerShip ship1 = new PlayerShip(360.0f, 360.0f, 400.0f, 400.0f, 1.0f, 1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship1);
		assertTrue(ship1.getX() == 1.0f && ship1.getY() == 1.0f);
		
		PlayerShip ship2 = new PlayerShip(360.0f, 360.0f, 400.0f, 400.01f, 1.0f, 1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship2);
		assertTrue(ship2.getX() == 1.0f && ship2.getY() == -1.0f);
		
		PlayerShip ship3 = new PlayerShip(360.0f, 360.0f, 400.01f, 400.01f, 1.0f, 1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship3);
		assertTrue(ship3.getX() == -1.0f && ship3.getY() == -1.0f);
		
		PlayerShip ship4 = new PlayerShip(360.0f, 360.0f, 400.01f, 410.0f, 1.0f, 1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship4);
		assertTrue(ship4.getX() == -1.0f && ship4.getY() == -1.0f);
	}
	
	@Test
	public void testExecuteLeftBottomCornerBounce()
	{
		DisplayManager.getDisplayManager().setDisplaySize(400, 400);
		IStrategy reboundStrategy = null;
		try 
		{
			reboundStrategy = StrategyFactory.makeStrategy("Rebound");
		} 
		catch (BadStrategyException e) 
		{
			e.printStackTrace();
		}
		
		PlayerShip ship1 = new PlayerShip(0.0f, 360.0f, 40.0f, 400.0f, -1.0f, 1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship1);
		assertTrue(ship1.getX() == -1.0f && ship1.getY() == 1.0f);
		
		PlayerShip ship2 = new PlayerShip(0.0f, 360.0f, 40.0f, 400.01f, -1.0f, 1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship2);
		assertTrue(ship2.getX() == -1.0f && ship2.getY() == -1.0f);
		
		PlayerShip ship3 = new PlayerShip(-0.01f, 360.0f, 40.0f, 400.01f, -1.0f, -1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship3);
		assertTrue(ship3.getX() == 1.0f && ship3.getY() == -1.0f);
		
		PlayerShip ship4 = new PlayerShip(0.01f, 360.0f, 40.0f, 410.0f, -1.0f, 1.0f, 
				0.0f, new Color(0), reboundStrategy);
		reboundStrategy.execute(ship4);
		assertTrue(ship4.getX() == -1.0f && ship4.getY() == -1.0f);
	}
}

