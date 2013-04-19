package edu.gatech.cs2340.wheresmystuff;

import java.io.*;

/**
 * User.java
 * Object to contain user information.
 * @author Kenneth Craig
 **/

/**
 * This class contains the user information of individual users
 * for the "Where's My Stuff" App
 **/

public class User implements Serializable {
	//instance variables
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Boolean locked = false;
	private Boolean admin = false;
	private static final long serialVersionUID = 555;
	
	public User(String username, String password, String firstName, String lastName, Boolean locked, Boolean admin){
		this.id = 1;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.locked = locked;
		this.admin = admin;
	}
	
	public User(int i, String username, String password, String firstName, String lastName, Boolean locked, Boolean admin){
		this.id = i;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.locked = locked;
		this.admin = admin;
	}
	
	public User(){
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
	}
	
	public User(String username, String password){
		this(username, password, "New", "User", false, false);
	}
	
	/**
	 * @return id of this user.
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * @param i
	 *            id to set this user to.
	 */
	
	public void setID(int i){
		this.id = i;
	}
	
	/**
	 * @return username of this user.
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * @param username
	 *            username to set this user to.
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * @return password of this user.
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * @param password
	 *            password to set this user to.
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * @return first name of this user.
	 */
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * @param firstName
	 *            first name to set this user to.
	 */
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	/**
	 * @return last name of this user.
	 */
	public String getLastName(){
		return lastName;
	}
	
	/**
	 * @param lastName
	 *            last name to set this user to.
	 */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	/**
	 * @return true if is admin, false if not admin.
	 */
	public boolean isAdmin(){
		return admin;
	}
	
	/**
	 * @param a
	 *            true to make admin, false to remove admin privileges
	 */
	public void setAdmin(boolean a){
		admin = a;
	}
	
	/**
	 * @return true if locked, false if unlocked.
	 */
	public boolean isLocked(){
		return locked;
	}
	
	/**
	 * @param l
	 *            true to lock, false to unlock
	 */
	public void setLocked(boolean l){
		locked = l;
	}
}