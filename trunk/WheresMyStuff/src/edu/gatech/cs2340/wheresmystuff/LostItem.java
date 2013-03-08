package edu.gatech.cs2340.wheresmystuff;

public class LostItem {
	public static LostItem item; // PLEASE TAKE ME OUT AS SOON AS POSSIBLE. THIS IS HORRIBLE CODE.
	
	String name = "";
	int	id;
	String description = "";

	public LostItem(String name) {
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
