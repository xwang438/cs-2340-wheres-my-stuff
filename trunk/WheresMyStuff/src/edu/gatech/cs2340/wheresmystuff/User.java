package edu.gatech.cs2340.wheresmystuff;

public class User{
	//instance variables
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Boolean locked = false;
	private Boolean admin = false;
	
	public User(String username, String password, String firstName, String lastName, Boolean locked, Boolean admin){
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.locked = locked;
		this.admin = admin;
	}
	
	public User(String username, String password){
		this(username, password, "New", "User", false, false);
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
	
	public String getFirstname(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastname(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public boolean isAdmin(){
		return admin;
	}
	
	public void setAsAdmin(){
		admin = true;
	}
	
	public boolean isLocked(){
		return locked;
	}
	public void lockAccount(){
		locked = true;
	}
	
	public void unlockAccount(){
		locked = false;
	}
	
}