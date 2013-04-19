package edu.gatech.cs2340.wheresmystuff;

import java.io.Serializable;

/**
 * 
 * @author Bongsu + Emily This is information of lost items
 */
public class Item implements Serializable {

	private String uid;
	private int iid;
	private String description;
	private String date;
	private String location;
	private Boolean resolved;
	private static final long serialVersionUID = 1L;

	enum ItemCategory {
		PERSONAL_ITEM("Personal Item"), APPLIANCE("Appliance"), FURNITURE(
				"Furniture");

		private String stringVal;

		private ItemCategory(String stringVal) {
			this.stringVal = stringVal;
		}

		public String toString() {
			return stringVal;
		}
	}

	public enum ItemStatus {
		Lost("Lost"),
		Found("Found"),
		Returned("Returned"),
		Donation("Donation");

		private String stringVal;

		private ItemStatus(String stringVal) {
			this.stringVal = stringVal;
		}

		public String toString() {
			return stringVal;
		}
	}

	// public static Item item;

	private String name = "";
	private int id;
	private ItemCategory category; // I want this to be a enum type: PERSONAL
									// ITEM, APPLIANCE, FURNITURE
	private ItemStatus status;

	/**
	 * Constructor for the Item class. Please do not delete.
	 * 
	 * @param name name for item
	 */
	public Item(String name) {
		this.name = name;
	}

	public Item() {
	}

	/**
	 * name of the items
	 * 
	 * @return name of the lost items
	 */
	public String getName() {
		return name;
	}
/**
 * 
 * @param name new name of item
 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * User Id who put the item on our application
	 * 
	 * @return User Id
	 */
	public String getUid() {
		return uid;
	}
/**
 * 
 * @param id item identification string
 */
	public void setUid(String id) {
		this.uid = uid;
	}

	/**
	 * Item Id 
	 * 
	 * @return Item Id
	 */
	public int getIid() {
		return iid;
	}
/**
 * 
 * @param iid item ID number
 */
	public void setIid(int iid) {
		this.iid = iid;
	}

	/**
	 * description of the items
	 * 
	 * @return description of the items
	 */
	public ItemCategory getCategory() {
		return category;
	}
/**
 * 
 * @param category classification of item
 */
	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	/**
	 * lost or found status of the item
	 * 
	 * @return lost or found as a string
	 */
	public ItemStatus getStatus() {
		return status;
	}
/**
 * 
 * @param status lost or found
 */
	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	/**
	 *  lost or found description of the item
	 * @return lost or found as a string
	 */
	public String getDescription() {
		return description;
	}
/**
 * 
 * @param description what do you think?
 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *  date when post the item
	 * @return Date
	 */
	public String getDate() {
		return date;
	}
/**
 * 
 * @param date date the item was lost/found
 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * location where lost or found item
	 * @return location
	 */

	public String getLocation() {
		return location;
	}
/**
 * 
 * @param location place the item was lost/found
 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * status of the resolve lost item
	 * @return resolved
	 */

	public Boolean getResolved() {
		return resolved;
	}
/**
 * 
 * @param resolved stores whether the item has been returned to its owner or not
 */
	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}
}
