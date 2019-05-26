package main.se450.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.se450.singletons.Configuration;
import main.se450.utilities.Extractor;

public class JSONConfigurationParser 
{
	private JSONConfigurationParser()
	{
	}
	
	public static HashMap<String, String> makeConfig(String fileName)
	{
		HashMap<String, String> config = new HashMap<>();
		
		try
		{ 
	        JSONParser parser = new JSONParser();
	 
	        Object obj = parser.parse(new FileReader(fileName));
	 
	        JSONObject jsonObject = (JSONObject) obj;
	 
	        JSONArray jsonArray = (JSONArray) jsonObject.get("game");
	        
	        Iterator<?> jsonIterator = jsonArray.iterator();
	        while (jsonIterator.hasNext())
	        {
	        	JSONObject someField = (JSONObject)jsonIterator.next();
	        	Configuration.getConfiguration().setConfiguration(Extractor.extractInteger(someField.get("framespersecond")), 
	        													  Extractor.extractInteger(someField.get("repeatkeyspeed")), 
	        													  Extractor.extractInteger(someField.get("width")), 
	        													  Extractor.extractInteger(someField.get("height")), 
	        													  Extractor.extractFloat(someField.get("shipwidth")), 
	        													  Extractor.extractFloat(someField.get("shipheight")), 
	        													  Extractor.extractFloat(someField.get("shotspeed")), 
	        													  Extractor.extractFloat(someField.get("shotdiameter")), 
	        													  Extractor.extractInteger(someField.get("shotlifetime")), 
	        													  Extractor.extractFloat(someField.get("forwardthrust")), 
	        													  Extractor.extractFloat(someField.get("reversethrust")), 
	        													  Extractor.extractFloat(someField.get("friction")), 
	        													  Extractor.extractFloat(someField.get("leftright")), 
	        													  Extractor.extractColor(someField.get("color")), 
	        													  someField.get("borders").toString());
	        	
	        }
		}
        catch(FileNotFoundException eFileNotFound)
        {
        }
        catch(IOException eIOException)
        {
        	
        }
        catch(ParseException eParseException)
        {
        }
		
		return config;
	}
}
