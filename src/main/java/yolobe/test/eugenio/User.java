/**
 * 
 */
package yolobe.test.eugenio;

/**
 * @author EugenioMoreno
 *
 */
public class User {
	private int id;
	private String name;
	 /**
	  * Constructor
	  * @param id of the user
	  * @param name of the user
	  */
	public User(int id, String name) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
	}
	//Getter
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

}
