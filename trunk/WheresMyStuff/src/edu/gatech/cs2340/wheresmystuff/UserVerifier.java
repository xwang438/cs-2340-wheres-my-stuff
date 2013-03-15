// TODO: Kyle, make a User class and embed it in UserVerifier. It should have all the properties for User according to the CRC. 

package edu.gatech.cs2340.wheresmystuff;

/**
 * M5
 * UserVerifier.java
 * 
 * @author Kyle Hosford
 * @version 2/24/2013
 **/

/** This class handles the checking, storing,
 * and verification of usernames and passwords for the 
 * "Where's My Stuff" App
 **/

public class UserVerifier{
  
	private static String[] usernames = new String[1];
	private static String[] passwords = new String[1];
	private int userIndex;
	private int loginAttempts;
	private DatabaseConnector dbc;
	
  //Basic constructor, creates a "default" username and password for demo purposes
  public UserVerifier(DatabaseConnector DB){
	dbc = DB;
    usernames = dbc.getUsernames(dbc);
    passwords = dbc.getPasswords(dbc);
    userIndex = -1;
    loginAttempts = 0;
  }
  
  /**
   * 
   * @param index the index in the array at which the desired username can be found
   * @return returns the found username as a string
   */
  public String getUser(int index){
    if(index > usernames.length){
      System.out.println("Invalid Index");
      return null;
    }
    else{
      return usernames[index];
    }
  }
  
  /**
   * 
   * @param index the index in the array at which the desired password can be found
   * @return returns the found password as a string
   */
  public String getPassword(int index){
    if(index > passwords.length){
      System.out.println("Invalid Index");
      return null;
    }
    else{
      return passwords[index];
    }
  }
  
  /**
   * 
   * @param user loops through list of usernames and checks if the input parameter exists in the list
   * @return returns true if the username exists, false is it doesn't
   */
  public boolean checkUsername(String user){
    for(int i = 0; i < usernames.length; i++){
      if(usernames[i].equals(user)){
        userIndex = i;
        return true;
      }
    }
    return false;
  }
  
  /**
   * 
   * @param user loops through list of passwords and checks if the input parameter exists in the list
   * @return returns true if the password exists, false is it doesn't
   */
  public boolean checkPassword(String pass){
      if(passwords[userIndex].equals(pass)){
        userIndex = -1;
        return true;
      }
    return false;
  }
  
  /**
   * 
   * @param newUser adds this parameter to the usernames array
   * @param newPassword adds this parameter to the passwords array
   * @return true if the username and password were successfully added to the arrays, false if the username already exists
   */
  public Boolean addUser(String newUser, String newPassword){
    for(int i =0; i < usernames.length; i++){
      if(usernames[i].equals(newUser)){
        System.out.println("Username already exists.");
        return false;
      }
    }
    String[] tempUsers = usernames;
    usernames = new String[usernames.length+1];
    String[] tempPass = passwords;
    passwords = new String[passwords.length+1];
    for(int i = 0; i < tempUsers.length; i++){
      usernames[i] = tempUsers[i];
      passwords[i] = tempPass[i];
    }
    usernames[usernames.length-1] = newUser;
    passwords[passwords.length-1] = newPassword;
    return true;
    }
  
  /**
   * 
   * @param username username parameter to be checked
   * @param password password parameter to be checked
   * @return true if the password and username match, false if not
   */
  public boolean loginCheck(String username, String password){
    if(this.checkUsername(username)){
      if(this.checkPassword(password)){
    	dbc.logInUser(dbc.findID(username));
        return true;
      }
      System.out.println("Incorrect Password. Please try again.");
    }
    System.out.println("Incorrect Username. Please try again.");
    loginAttempts++;
    return false;
  }
  
  /**
   * 
   * @param attempts a variable describing how many times the user has tried to login
   * @return true if login may proceed, false if the user has failed too many times
   */
  public boolean checkAttempt(){
	  if(loginAttempts <= 3){
		  return false;
	  }
	  else{
		  return true;
	  }
  }
  
  
/**
 *       
 * @return returns the values of the lists of usernames and passwords as a string
 */
  public String toString(){
    String result = new String("");
    for(int i=0; i < usernames.length; i++){
      result = result + "User" + i + ": " + usernames[i] + "; Password: " + passwords[i] + "\n";
    }
    return result;
  }
  
  
  
}