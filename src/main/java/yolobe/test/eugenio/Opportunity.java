/**
 * 
 */
package yolobe.test.eugenio;

import java.util.Comparator;

/**
 * @author EugenioMoreno
 *
 */
public class Opportunity implements Comparable<Opportunity> {
	private int id;
	private String title;
	private int hits;

	/**
	 *@Constructor
	 *@param
	 *title Title of the publication
	 *@param
	 *id id of the publications
	 *@param
	 *hits number of hits on the opportunity
	 */
	public Opportunity(int id, String title, int hits) {
		// TODO Auto-generated constructor stub
		this.title=title;
		this.id=id;
		this.hits=hits;
	}
	
	//Getters
	/**
	 * @return Opportunity id
	 */
		public int getId() {
			return id;
		}
		/**
		 * @return Opportunity title
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * @return number of hits
		 */
		public int getHits() {
			return hits;
		}
		
		/**
		 * This method permits to compare 2 Opportunities
		 * and sort them alphabetically by title
		 */
		public static Comparator<Opportunity> TitleComparator= new Comparator<Opportunity>() {
			
			@Override
			public int compare(Opportunity o1, Opportunity o2) {
				// TODO Auto-generated method stub
				String title1=o1.getTitle().toUpperCase();
				String title2=o2.getTitle().toUpperCase();
				/*Return the list sorted in ascending order from A to Z*/
				return title1.compareTo(title2);
			}
		};
		
		/**
		 * This method overrides compareTo so
		 * a list of Opportunities can be compared by the number of hits.
		 * this is used for sorting a list of opportunities by this number.
		 */
		@Override
		public int compareTo(Opportunity op) {
			// TODO Auto-generated method stub
			int compareQuantity = ((Opportunity) op).getHits();
			//descending order
			return compareQuantity - this.hits;
			//Ascending
			//return this.hits-compareQuantity;
		}

}
