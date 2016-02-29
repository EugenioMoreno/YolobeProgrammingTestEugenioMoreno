/**
 * 
 */
package yolobe.test.eugenio;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Eugenio Moreno
 *
 */
public class JsonParser {
	public static JSONArray itemsArray;
	public static JSONArray contentArray= new JSONArray();
	public static Opportunity[] opportunityArray;

	/**
	 * @param args[0]: URL of the endpoint
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		URL url = new URL(args[0]);
		 
	    // Reques JSON from the URL of the endpoint
	    Scanner scan= new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext()){
			str += scan.nextLine();
		}
		//System.out.println(str);
		scan.close();
		
		parseJSONstring(str);
	    getContentInformation();
	    printResults();

	}
	
	/**
	 * This method print the title of the presented opportunities by title,
	 * printing the titles in alphabetical order.
	 * And display the average number of hits per presented Opportunity
	 */
	private static void printResults() {
		// TODO Auto-generated method stub
		//Sort the array by title
		Arrays.sort(opportunityArray,Opportunity.TitleComparator);
		for (int i = 0; i < opportunityArray.length; i++) {
			
			System.out.println(opportunityArray[i].toString());
		}
		
	}
	/**
	 * This method creates Opportunity objects from the JSON objects
	 * @throws JSONException
	 */
	protected static void getContentInformation() throws JSONException {
		
		for (int i = 0; i < itemsArray.length(); i++) {
	       	JSONObject auxItem=itemsArray.getJSONObject(i);
	    	contentArray.put(auxItem.getJSONObject("content"));
	    	
	    	//Auxiliary variables for the string that we are looking for
	    	int id=contentArray.getJSONObject(i).getInt("id");
	    	String title=contentArray.getJSONObject(i).getString("title");
	    	int hits=contentArray.getJSONObject(i).getInt("hits");
	    	
	    	//New Opportunity object
	    	opportunityArray[i]=new Opportunity(id, title, hits);
	    }
	}
	
	/**
	 * This method initialize the JSONArray with all the items of the JSON data
	 * @param str is the String that was obtained from the discover endpoint 
	 * @throws JSONException
	 */
	protected static void parseJSONstring(String str) throws JSONException {
		// Build a JSON object from the string obtained on the endpoint
	    JSONObject obj = new JSONObject(str);
	    //Get the items of the file
	    itemsArray = obj.getJSONArray("items");
	    //Initialize the array with Opportunity objects with the same length
	    opportunityArray=new Opportunity[itemsArray.length()];
	}
}

