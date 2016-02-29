/**
 * 
 */
package yolobe.test.eugenio;

import java.io.IOException;
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

	/**
	 * @param args[0]: URL of the endpoint
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Get the url from the parameters
		URL url = new URL(args[0]);
		//Obtain the string of the request
	    String str = getJSONstring(url);
	    
		//Get and JSONArray with the items of the endpoint
		JSONArray itemsArray=parseJSONstring(str);
		
		//Get the information about opportunities, users and networks
		Opportunity[] opportunityArray= getContentInformation(itemsArray);
		User[] userArray=getUserInformation(itemsArray);
		Network[] networkArray=getNetworkInformation(itemsArray);
		
		//Print the requirements (title in ascending order and hits)
	    printResults(opportunityArray);

	}
	/**
	 * 
	 * @param url of the discover endpoint
	 * @return string with the JSON data
	 * @throws IOException
	 */
	private static String getJSONstring(URL url) throws IOException {
		// Request JSON from the URL of the endpoint
	    Scanner scan= new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext()){
			str += scan.nextLine();
		}
		
		scan.close();
		return str;
	}
	
	/**
	 * This method print the title of the presented opportunities by title,
	 * printing the titles in alphabetical order.
	 * And display the average number of hits per presented Opportunity
	 */
	private static void printResults(Opportunity[] opportunityArray) {
		// TODO Auto-generated method stub
		//Sort the array by title
		Arrays.sort(opportunityArray,Opportunity.TitleComparator);
		for (int i = 0; i < opportunityArray.length; i++) {
			System.out.println(opportunityArray[i].toString());
		}
		
	}
	/**
	 * This method creates opportunity objects from the JSON objects
	 * @param itemsArray items of the JSON file
	 * @return array of opportunity objects with id, title and number of hits
	 * @throws JSONException
	 */
	protected static Opportunity[] getContentInformation(JSONArray itemsArray) throws JSONException {
		JSONArray contentArray= new JSONArray();
		Opportunity[] opportunityArray=new Opportunity[itemsArray.length()];
		
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
		
		return opportunityArray;
	}
	/**
	 * This method creates user objects from the JSON objects
	 * @param itemsArray items of the JSON file
	 * @return array of user objects with id and name
	 * @throws JSONException
	 */
	protected static User[] getUserInformation(JSONArray itemsArray) throws JSONException {
		JSONArray userJSONArray= new JSONArray();
		User[] userArray=new User[itemsArray.length()];
		for (int i = 0; i < itemsArray.length(); i++) {
	    	
	    	JSONObject auxItem=itemsArray.getJSONObject(i);
	    	userJSONArray.put(auxItem.getJSONObject("user"));
	    	
	    	//Auxiliary variables for the string that we are looking for
	    	int id=userJSONArray.getJSONObject(i).getInt("id");
	    	String name=userJSONArray.getJSONObject(i).getString("name");
	    		    	
	    	//New Opportunity object
	    	userArray[i]=new User(id, name);
	    }
		return userArray;
	}
	/**
	 * This method creates network objects from the JSON objects
	 * @param itemsArray items of the JSON file
	 * @return array of network objects with id and name
	 * @throws JSONException
	 */
	protected static Network[] getNetworkInformation(JSONArray itemsArray) throws JSONException {
		JSONArray networkJSONArray= new JSONArray();
		Network [] networkArray=new Network[itemsArray.length()];
		for (int i = 0; i < itemsArray.length(); i++) {
	    	
	    	JSONObject auxItem=itemsArray.getJSONObject(i);
	    	networkJSONArray.put(auxItem.getJSONObject("network"));
	    	
	    	//Auxiliary variables for the string that we are looking for
	    	int id=networkJSONArray.getJSONObject(i).getInt("id");
	    	String name=networkJSONArray.getJSONObject(i).getString("name");
	    		    	
	    	//New Opportunity object
	    	networkArray[i]=new Network(id, name);
	    }
		return networkArray;
	}
	
	/**
	 * This method initialize the JSONArray with all the items of the JSON data
	 * @param str is the String that was obtained from the discover endpoint 
	 * @throws JSONException
	 */
	protected static JSONArray parseJSONstring(String str) throws JSONException {
		// Build a JSON object from the string obtained on the endpoint site
	    JSONObject obj = new JSONObject(str);
	    //Get the items of the file
	    JSONArray itemsArray = obj.getJSONArray("items"); 
	    return itemsArray;
	}
}

