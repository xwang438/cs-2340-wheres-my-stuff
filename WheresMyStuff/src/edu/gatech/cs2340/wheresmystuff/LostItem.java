package edu.gatech.cs2340.wheresmystuff;

/**
 * 
 * @author Bongsu
 *This is information of lost items
 */
public class LostItem {
	String name = "";
	int	id;
	String description = "";

	public LostItem() {
		
	}
/**
 * name of the lost items
 * @return name of the lost items
 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
/**
 * User Id who put the item on our application
 * @return User Id
 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
/**
 * description of the items
 * @return description of the items
 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
