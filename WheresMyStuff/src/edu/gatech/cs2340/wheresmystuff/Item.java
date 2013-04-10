package edu.gatech.cs2340.wheresmystuff;

import java.io.Serializable;

/**
 * 
 * @author Bongsu + Emily This is information of lost items
 */
public class Item implements Serializable {

	// public static Item item;

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
	 * Constructor for the LostItem class. Please do not delete.
	 * 
	 * @param name
	 */
	public Item(String name) {
		this.name = name;
	}

	public Item() {
	}

	/**
	 * name of the lost items
	 * 
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
	 * 
	 * @return User Id
	 */
	public String getUid() {
		return uid;
	}

	public void setUid(String id) {
		this.uid = uid;
	}

	public int getIid() {
		return iid;
	}

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

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	/**
	 * lost or fond status of the item
	 * 
	 * @return lost or found as a string
	 */
	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getResolved() {
		return resolved;
	}

	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}
}