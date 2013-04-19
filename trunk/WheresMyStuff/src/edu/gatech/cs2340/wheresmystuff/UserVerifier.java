
package edu.gatech.cs2340.wheresmystuff;

import java.util.ArrayList;
import java.io.*;
import android.content.Context;
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
	
	private static String[] usernames = {"admin@gatech.edu", "user1@gmail.com", "user2@gmail.com", "newAdmin@gatech.edu"};
	private static String[] passwords = {"admin1", "user1", "user2", "newAdmin"};
	private static String[] firstNames = {"ad", "User", "User", "New"};
	private static String[] lastNames = {"min", "1", "2", "Admin"};
	private static boolean[] locked = {false, false, true, false};
	private static boolean[] admins = {true, false, false, true};
	private ArrayList<User> users;
	private int userIndex;
	private int loginAttempts;
	@SuppressWarnings("unused")
	private User loggedInUser;
	private User defaultAdminUser;
	private UserDatabase udb;
	
	// Basic constructor, creates a "default" username and password for demo
	// purposes
	public UserVerifier(Context context) {
			defaultAdminUser = new User(0,"admin@gatech.edu", "admin1", "Ad", "Min", false, true);
			
			udb = new UserDatabase(context);
			//usersFile = new TextFile("file:///android_asset/users.txt");
			//usersFile.addUser(defaultAdminUser);
			users = udb.getAllUsers();
			addUser(defaultAdminUser);
			//addUser(defaultAdminUser);
			
			userIndex = -1;
			loginAttempts = 0;
			loggedInUser = new User("", "");
	}

	/**
	 * 
	 * @param index
	 *            the index in the array at which the desired username can be
	 *            found
	 * @return returns the found username as a string
	 */
	public String getUser(int index) {
		if (index > users.size()) {
			System.out.println("Invalid Index");
			return null;
		} else {
			return users.get(index).getUsername();
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
		if (index > users.size()) {
			System.out.println("Invalid Index");
			return null;
		} else {
			return users.get(index).getPassword();
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
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(user)) {
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
		if (users.get(userIndex).getPassword().equals(pass)) {
			userIndex = -1;
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
	public Boolean addUser(String newUser, String newPassword) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i) != null && users.get(i).getUsername().equals(newUser)) {
				System.out.println("Username already exists.");
				return false;
			}
		}
		//try {
			//usersFile.addUser(new User(newUser, newPassword));
		
		udb.insertUser(new User(newUser, newPassword));
		users = udb.getAllUsers();
		//	usernames = usersFile.getUsernames();
		//	passwords = usersFile.getPasswords();
		//} catch (IOException e) {
		//	System.out.println(e.getMessage());
		//}
		return true;
	}
	
	/**
	 * @param user
	 *            user to be removed from database.
	 * @return true if successfully removed, false otherwise.
	 */
	public Boolean removeUser(String username) {
		
		udb.deleteUser(udb.searchByUsername(username));
		users = udb.getAllUsers();
		//for (int i = 0; i < usernames.length; i++) {
		//	if (usernames[i]!= null && usernames[i].equals(username)) {
		//		String[] tempUsers = usernames;
		//		usernames = new String[usernames.length - 1];
		//		String[] tempPass = passwords;
		//		passwords = new String[passwords.length - 1];
		//		
		//		int k = 0;
		//		for (int j = 0; j < tempUsers.length; j++) {
		//			
		//			if(i == j) j++;
		//			else {
		//				usernames[k] = tempUsers[j];
		//				passwords[k] = tempPass[j];
		//			}
		//			k++;
		//		}
		//		return true;
		//	}
		//}
		return true;
	}
	
	/**
	 * @param user
	 *            user to be added to database.
	 * @return true if successfully added, false otherwise.
	 */
	public Boolean addUser(User user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i)!= null && users.get(i).getUsername().equals(user.getUsername())) {
				System.out.println("Username already exists.");
				return false;
			}
		}
		System.out.println("Well, it made it here.");
		udb.insertUser(user);
		users = udb.getAllUsers();
		//String[] tempUsers = usernames;
		//usernames = new String[usernames.length + 1];
		//String[] tempPass = passwords;
		//passwords = new String[passwords.length + 1];
		//for (int i = 0; i < tempUsers.length; i++) {
		//	usernames[i] = tempUsers[i];
		//	passwords[i] = tempPass[i];
		//}
		//usernames[usernames.length - 1] = user.getUsername();
		//passwords[passwords.length - 1] = user.getPassword();
		
		//usernames = usersFile.getUsernames();
		//passwords = usersFile.getPasswords();
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
				loggedInUser = makeLoggedInUser(username);
				return true;
			}
			System.out.println("Incorrect Password. Please try again.");
		}
		System.out.println("Incorrect Username. Please try again.");
		loginAttempts++;
		return false;
	}

	/**
	 * @param username
	 *            username of logged in user.
	 * @return the user that is logged in.
	 */
	public User makeLoggedInUser(String username) {
		User user = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				user = users.get(i);
			}
		}
		return user;
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
	 * @return list of users.
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * @return the user that is logged in.
	 */
	public User getLoggedInUser() {
		return loggedInUser;
	}
	
	/**
	 * @return array of usernames.
	 */
	public String[] getUsernames() {
		String[] uns = new String[users.size()];
		for(int i = 0; i < users.size(); i++)
			uns[i] = users.get(i).getUsername();
		return uns;
	}
	
	/**
	 * @return array of passwords.
	 */
	public String[] getPasswords() {
		String[] pws = new String[users.size()];
		for(int i = 0; i < users.size(); i++)
			pws[i] = users.get(i).getPassword();
		return pws;
	}
	
	/**
	 * @param i
	 *            index of user to be altered.
	 * @param lock
	 *            true to lock, false to unlock.
	 */
	public void setLocked(int i, boolean lock) {
		//locked[i] = lock;
		udb.updateUser(new User(i, users.get(i).getUsername(),
				users.get(i).getPassword(), users.get(i).getFirstName(),
				users.get(i).getLastName(), lock, users.get(i).isAdmin()));
	}
	
	/**
	 * @param i
	 *            index of user to be altered.
	 * @param lock
	 *            true to make admin, false to remove admin privileges.
	 */
	public void setAdmin(int i, boolean admin) {
		//admins[i] = admin;
		udb.updateUser(new User(i, users.get(i).getUsername(),
				users.get(i).getPassword(), users.get(i).getFirstName(),
				users.get(i).getLastName(), users.get(i).isLocked(), admin));
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