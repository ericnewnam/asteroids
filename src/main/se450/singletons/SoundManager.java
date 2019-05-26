package main.se450.singletons;

import java.util.HashMap;

import main.se450.constants.Constants;
import main.se450.interfaces.ISound;
import main.se450.sound.Explosion;
import main.se450.sound.Fire;
import main.se450.sound.ForwardThrust;
import main.se450.sound.ReverseThrust;
import main.se450.sound.Shield;

public class SoundManager
{
	private static SoundManager soundManager = null;
	
	private HashMap<String,ISound> sounds = null;
	
	static
	{
		soundManager = new SoundManager();
	}
	
    private SoundManager()
    {
    	sounds = new HashMap<String,ISound>();
    	
    	sounds.put(Constants.FIRE,                   new Fire());
    	sounds.put(Constants.FORWARD_THRUST_PRESSED, new ForwardThrust());
    	sounds.put(Constants.REVERSE_THRUST_PRESSED, new ReverseThrust());
    	sounds.put(Constants.EXPLOSION,              new Explosion());
    	sounds.put(Constants.SHIELD,                 new Shield());
    }
    
	public final static SoundManager getSoundManager() 
	{
		return soundManager;
	}
	
	public void fire()
	{
		sounds.get(Constants.FIRE).play();
	}
	
	public void forwardThrust()
	{
		sounds.get(Constants.FORWARD_THRUST_PRESSED).play();
	}
	
	public void reverseThrust()
	{
		sounds.get(Constants.REVERSE_THRUST_PRESSED).play();
	}
	
	public void explosion()
	{
		sounds.get(Constants.EXPLOSION).play();
	}
	
	public void shield()
	{
		sounds.get(Constants.SHIELD).play();
	}
}

