// TODO: Kyle, make a User class and embed it in UserVerifier. It should have all the properties for User according to the CRC. 

package edu.gatech.cs2340.wheresmystuff;

import java.io.*;

/**
 * M5
 * UserVerifier.java
 * 
 * @author Kyle Hosford
 * @version 2/24/2013
 **/

/**
 * This class handles the checking, storing, and verification of usernames and
 * passwords for the "Where's My Stuff" App
 **/

public class UserVerifier {

	private static String[] usernames;
	private static String[] passwords;
	private int userIndex;
	private int loginAttempts;
	private TextFile usersFile;

	// Basic constructor, creates a "default" username and password for demo
	// purposes
	public UserVerifier() {
		try {
		usersFile = new TextFile("file:///android_asset/users.txt");
		usernames = usersFile.getUsernames();
		passwords = usersFile.getPasswords();
		} catch(IOException e) {
			
		}
		
		userIndex = -1;
		loginAttempts = 0;
		try {
		addUser(new User("admin@gatech.edu", "admin1", "Ad", "Min", false, true));
		} catch(IOException e) {
		
		}
	}

	/**
	 * 
	 * @param index
	 *            the index in the array at which the desired username can be
	 *            found
	 * @return returns the found username as a string
	 */
	public String getUser(int index) {
		if (index > usernames.length) {
			System.out.println("Invalid Index");
			return null;
		} else {
			return usernames[index];
		}
	}

	/**
	 * 
	 * @param index
	 *            the index in the array at which the desired password can be
	 *            found
	 * @return returns the found password as a string
	 */
	public String getPassword(int index) {
		if (index > passwords.length) {
			System.out.println("Invalid Index");
			return null;
		} else {
			return passwords[index];
		}
	}

	/**
	 * 
	 * @param user
	 *            loops through list of usernames and checks if the input
	 *            parameter exists in the list
	 * @return returns true if the username exists, false is it doesn't
	 */
	public boolean checkUsername(String user) {
		if(usernames == null) System.out.println("I AM NULL D:");
		for (int i = 0; i < usernames.length; i++) {
			if (usernames[i].equals(user)) {
				userIndex = i;
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param user
	 *            loops through list of passwords and checks if the input
	 *            parameter exists in the list
	 * @return returns true if the password exists, false is it doesn't
	 */
	public boolean checkPassword(String pass) {
		if (passwords[userIndex].equals(pass)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param newUser
	 *            adds this parameter to the usernames array
	 * @param newPassword
	 *            adds this parameter to the passwords array
	 * @return true if the user name and password were successfully added to the
	 *         arrays, false if the user name already exists
	 */
	public Boolean addUser(String newUser, String newPassword) throws IOException {
		for (int i = 0; i < usernames.length; i++) {
			if (usernames[i]!= null && usernames[i].equals(newUser)) {
				System.out.println("Username already exists.");
				return false;
			}
		}
		
		usersFile.addUser(new User(newUser, newPassword));
		
		usernames = usersFile.getUsernames();
		passwords = usersFile.getPasswords();
		return true;
	}
	
	public Boolean addUser(User user) throws IOException {
		for (int i = 0; i < usernames.length; i++) {
			if (usernames[i]!= null && usernames[i].equals(user.getUsername())) {
				System.out.println("Username already exists.");
				return false;
			}
		}
		
		usersFile.addUser(user);
		
		/*String[] tempUsers = user names;
		user names = new String[usernames.length + 1];
		String[] tempPass = passwords;
		passwords = new String[passwords.length + 1];
		for (integer i = 0; i < tempUsers.length; i++) {
			user names[i] = tempUsers[i];
			passwords[i] = tempPass[i];
		}
		user names[usernames.length - 1] = user.getUsername();
		passwords[passwords.length - 1] = user.getPassword();*/
		
		usernames = usersFile.getUsernames();
		passwords = usersFile.getPasswords();
		return true;
	}

	/**
	 * 
	 * @param username
	 *            user name parameter to be checked
	 * @param password
	 *            password parameter to be checked
	 * @return true if the password and user name match, false if not
	 */
	public boolean loginCheck(String username, String password) {
		if (this.checkUsername(username)) {
			if (this.checkPassword(password)) {
				try {
					usersFile.logInUser(userIndex);
				} catch(IOException e) {
					
				}
				userIndex = -1;
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
	 * @param attempts
	 *            a variable describing how many times the user has tried to
	 *            login
	 * @return true if login may proceed, false if the user has failed too many
	 *         times
	 */
	public boolean checkAttempt() {
		if (loginAttempts <= 3) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @return returns the values of the lists of usernames and passwords as a
	 *         string
	 */
	public String toString() {
		String result = new String("");
		for (int i = 0; i < usernames.length; i++) {
			result = result + "User" + i + ": " + usernames[i] + "; Password: "
					+ passwords[i] + "\n";
		}
		return result;
	}

}