package edu.gatech.cs2340.wheresmystuff;

public class User{
	//instance variables
	String username;
	String password;
	String firstName;
	String lastName;
	Boolean locked = false;
	Boolean admin = false;
	
	public User(String username, String password, String firstName, String lastName, Boolean locked, Boolean admin){
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.locked = locked;
		this.admin = admin;
	}
	
	//TODO: add constructor chaining
	
	public String getName(){
		return firstName + " " + lastName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setAsAdmin(){
		admin = true;
	}
	
	public void lockAccount(){
		locked = true;
	}
	
	public void unlockAccount(){
		locked = false;
	}
	
}