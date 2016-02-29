/**
 * 
 */
package yolobe.test.eugenio;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

/**
 * @author EugenioMoreno
 *
 */
public class JsonParserTest {
	private static String jsonString;
	
	/**
	 * Initialize the string with a JSON file with 3 opportunities
	 */
	private void setUp() {
		// TODO Auto-generated method stub	
		jsonString=new String("{\"info\":{\"api\":\"0.1\",\"incompatible\":\"/update\"},\"items\":[{\"id\":1548,\"content\":{\"id\":1548,\"field1\":\"First field\",\"field2\":\"Second field\",\"field3\":\"Thrid field.\",\"field4\":null,\"field5\":null,\"hits\":10014,\"tags\":[\"tempor\",\"invidunt\"],\"template\":\"card1\",\"title\":\"My first post\",\"media1\":{\"path\":\"URL1\"},\"media2\":{\"path\":\"URL2\"}},\"network\":{\"id\":5,\"name\":\"7th Street Church\"},\"user\":{\"id\":12,\"name\":\"Ulrike Duval\",\"profile\":{\"path\":\"URL3\"}}},{\"id\":1549,\"content\":{\"id\":1549,\"field1\":\"First field\",\"field2\":\"Second field\",\"field3\":\"Third field.\",\"field4\":null,\"field5\":null,\"hits\":3001,\"tags\":[\"et\"],\"template\":\"card1\",\"title\":\"Second Post\",\"media1\":{\"path\":\"URL4\"},\"media2\":{\"path\":\"URL5\"}},\"network\":{\"id\":6,\"name\":\"Breman High School\"},\"user\":{\"id\":6,\"name\":\"Lara Rozelle\",\"profile\":{\"path\":\"URL6\"}}},{\"id\":1550,\"content\":{\"id\":1550, \"field1\":\"First field\",\"field2\":\"Second field\",\"field3\":\"Third field.\",\"field4\":null,\"field5\":null,\"hits\":5269,\"tags\":[\"dolore\"],\"template\":\"card1\",\"title\":\"Breaking the test\",\"media1\":{\"path\":\"URL7\"},\"media2\":{\"path\":\"URL9\"}},\"network\":{\"id\":6,\"name\":\"Breman High School\"},\"user\":{\"id\":9,\"name\":\"Bridgett Lujan\",\"profile\":{\"path\":\"URL9\"}}}]}");
	}
	
	@Test
	public void threeDifferentItems() {
		//Having
		setUp();
		JSONArray itemsArray=new JSONArray();
		//When
		try {
			itemsArray=JsonParser.parseJSONstring(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Then
		assertEquals(3, itemsArray.length());
	}
	
	@Test
	public void opportunityTitles() {
		//Having
		setUp();
		JSONArray itemsArray=new JSONArray();
		Opportunity[] opportunityArray=new Opportunity[itemsArray.length()];
		//When
		try {
			itemsArray=JsonParser.parseJSONstring(jsonString);
			opportunityArray=JsonParser.getContentInformation(itemsArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Then
		assertEquals("My first post", opportunityArray[0].getTitle());
		assertEquals("Second Post", opportunityArray[1].getTitle());
		assertEquals("Breaking the test", opportunityArray[2].getTitle());
	}
	
	@Test
	public void alphabeticalOrderTitles() {
		//Having
		setUp();
		JSONArray itemsArray=new JSONArray();
		Opportunity[] opportunityArray=new Opportunity[itemsArray.length()];
		//When
		try {
			itemsArray=JsonParser.parseJSONstring(jsonString);
			opportunityArray=JsonParser.getContentInformation(itemsArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Arrays.sort(opportunityArray,Opportunity.TitleComparator);
		//Then the order has changed
		assertEquals("My first post", opportunityArray[1].getTitle());
		assertEquals("Second Post", opportunityArray[2].getTitle());
		assertEquals("Breaking the test", opportunityArray[0].getTitle());
	}
	
	@Test
	public void numberOfHintsPerContent() {
		//Having
		setUp();
		JSONArray itemsArray=new JSONArray();
		Opportunity[] opportunityArray=new Opportunity[itemsArray.length()];
		//When
		try {
			itemsArray=JsonParser.parseJSONstring(jsonString);
			opportunityArray=JsonParser.getContentInformation(itemsArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int [] hints=new int [opportunityArray.length];
		for (int i = 0; i < opportunityArray.length; i++) {
			hints[i]=opportunityArray[i].getHits();
		}
		int [] hintsExpected={10014,3001,5269};
		assertArrayEquals(hintsExpected, hints);
	}
	
	@Test
	public void hitsInDescendingOrder() {
		//Having
		setUp();
		JSONArray itemsArray=new JSONArray();
		Opportunity[] opportunityArray=new Opportunity[itemsArray.length()];
		//When
		try {
			itemsArray=JsonParser.parseJSONstring(jsonString);
			opportunityArray=JsonParser.getContentInformation(itemsArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int [] hints=new int [opportunityArray.length];
		//Sort the array by number of hints
		Arrays.sort(opportunityArray);
		for (int i = 0; i < opportunityArray.length; i++) {
			hints[i]=opportunityArray[i].getHits();
		}
		
		int [] hintsExpected={10014,5269,3001};
		assertArrayEquals(hintsExpected, hints);
	}

}
