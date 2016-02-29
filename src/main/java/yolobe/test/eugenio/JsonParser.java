/**
 * 
 */
package yolobe.test.eugenio;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Eugenio Moreno
 *
 */
public class JsonParser {

	/**
	 * @param args
	 * args[0]: URL of the endpoint
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
		System.out.println(str);
		scan.close();

	}

}
