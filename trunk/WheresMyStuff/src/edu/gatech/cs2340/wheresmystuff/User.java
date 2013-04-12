package edu.gatech.cs2340.wheresmystuff;

import java.io.*;

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
	
	public int getID(){
		return id;
	}
	
	public void setID(int i){
		this.id = i;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public boolean isAdmin(){
		return admin;
	}
	
	public void setAdmin(boolean a){
		admin = a;
	}
	
	public boolean isLocked(){
		return locked;
	}
	
	public void setLocked(boolean l){
		locked = l;
	}
}