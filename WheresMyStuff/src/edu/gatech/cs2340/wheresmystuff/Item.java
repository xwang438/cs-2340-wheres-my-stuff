package edu.gatech.cs2340.wheresmystuff;

import java.io.Serializable;

/**
 * 
 * @author Bongsu + Emily
 *This is information of lost items
 */
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	enum ItemCategory {
		PERSONAL_ITEM("Personal Item"), APPLIANCE("Appliance"), FURNITURE("Furniture");
		
		private String stringVal;
		
		private ItemCategory(String stringVal) {
			this.stringVal = stringVal;
		}
		
		public String toString() {
			return stringVal;
		}
	}
	
	enum ItemStatus {
		LOST_ITEM("Lost"), FOUND_ITEM("Found"), RETURNED_ITEM("Returned"), DONATED_ITEM("Donation");
		
		private String stringVal;
		
		private ItemStatus(String stringVal) {
			this.stringVal = stringVal;
		}
		
		public String toString() {
			return stringVal;
		}
	}
	//public static Item item; 
	
	private String name = "";
	private int	id;
	private ItemCategory category; //I want this to be a enum type: PERSONAL ITEM, APPLIANCE, FURNITURE
	private ItemStatus status;
	
/**
 * Constructor for the LostItem class. Please do not delete.
 * @param name
 */
	public Item(String name) {
		this.name = name;
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
	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}
	
/**
 * lost or fond status of the item	
 * @return lost or found as a string
 */
	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}
	
}
