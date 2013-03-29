// TODO: Kyle, make a User class and embed it in UserVerifier. It should have all the properties for User according to the CRC. 

package edu.gatech.cs2340.wheresmystuff;

import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

/**
 * M7
 * TextFile.java
 * 
 * @author Kenneth Craig
 * @version 3/29/2013
 **/

/**
 * This class handles the checking, storing, and verification of usernames and
 * passwords for the "Where's My Stuff" App
 **/

public class TextFile {

	private String filename;
	private FileWriter fwriter;
	private PrintWriter inputFile;
	
	private File ourFile;
	private Scanner outputFile;
	
	private StringTokenizer tokenizer;
	private int userIndex;
	
	// Basic constructor, creates a "default" username and password for demo
	// purposes
	public TextFile(String file) throws IOException {
		filename = file;
	}

	/**
	 * 
	 * @param index
	 *            the index in the array at which the desired username can be
	 *            found
	 * @return returns the found username as a string
	 */
	
	
	
	public String[] getUsernames() throws IOException {
		String[] usernames = new String[getNumberOfLines()];
		
		openOutput();
		for(int i = 0; i < usernames.length; i++) {
			usernames[i] = getUsername(outputFile.nextLine());
		}
		closeOutput();
		
		return usernames;
	}
	
	public String[] getPasswords() throws IOException {
		String[] passwords = new String[getNumberOfLines()];
		
		openOutput();
		for(int i = 0; i < passwords.length; i++) {
			passwords[i] = getPassword(outputFile.nextLine());
		}
		closeOutput();
		
		return passwords;
	}
	
	public int getNumberOfLines() throws IOException {
		int i = 0;
		
		openOutput();
		while(outputFile.hasNext()) {
			i++;
		}
		closeOutput();
		
		return i;
	}
	
	public String getRow(int rowIndex) throws IOException {
		String rowData = null;
		int i = 0;
		
		openOutput();
		while(outputFile.hasNext() && i <= rowIndex) {
			i++;
			if(i == rowIndex) {
				rowData = outputFile.nextLine();
			}
		}
		closeOutput();
		
		return rowData;
	}
	
	public String getRow(String name) throws IOException {
		String rowData = null;
		boolean found = false;
		String nameInRow;
		
		openOutput();
		while(outputFile.hasNext() && !found) {
			nameInRow = getUsername(outputFile.nextLine());
			if(name == nameInRow) {
				rowData = outputFile.nextLine();
				found = true;
			}
		}
		closeOutput();
		
		return rowData;
	}
	
	public String getUsername(String line) {
		String username;
		tokenizer = new StringTokenizer(line,":");
		
		username = tokenizer.nextToken();
		
		return username;
	}
	
	public String getPassword(String line) {
		String password;
		tokenizer = new StringTokenizer(line,":");
		
		tokenizer.nextToken();
		password = tokenizer.nextToken();
		
		return password;
	}
	
	public String getFirstName(String line) {
		String firstName;
		tokenizer = new StringTokenizer(line,":");
		
		tokenizer.nextToken();
		tokenizer.nextToken();
		firstName = tokenizer.nextToken();
		
		return firstName;
	}
	
	public String getLastName(String line) {
		String lastName;
		tokenizer = new StringTokenizer(line,":");
		
		tokenizer.nextToken();
		tokenizer.nextToken();
		tokenizer.nextToken();
		lastName = tokenizer.nextToken();
		
		return lastName;
	}
	
	public boolean getLocked(String line) {
		boolean locked;
		String status;
		tokenizer = new StringTokenizer(line,":");
		
		tokenizer.nextToken();
		tokenizer.nextToken();
		tokenizer.nextToken();
		tokenizer.nextToken();
		status = tokenizer.nextToken();
		
		if(status.equals("true"))
			locked = true;
		else
			locked = false;
		
		return locked;
	}
	
	public boolean getAdmin(String line) {
		boolean admin;
		String status;
		tokenizer = new StringTokenizer(line,":");
		
		tokenizer.nextToken();
		tokenizer.nextToken();
		tokenizer.nextToken();
		tokenizer.nextToken();
		tokenizer.nextToken();
		status = tokenizer.nextToken();
		
		if(status.equals("true"))
			admin = true;
		else
			admin = false;
		
		return admin;
	}
	
	public User getUser(String line) {
		User user = new User(getUsername(line), getPassword(line),
				getFirstName(line), getLastName(line), getLocked(line),
				getAdmin(line));
		
		return user;
	}
	
	public void openOutput() throws IOException {
		ourFile = new File(filename);
		outputFile = new Scanner(ourFile);
	}
	
	public void openInput() throws IOException {
		fwriter = new FileWriter(filename, true);
		inputFile = new PrintWriter(fwriter);
	}
	
	public void closeInput() throws IOException {
		inputFile.close();
	}
	
	public void closeOutput() throws IOException {
		outputFile.close();
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
	 * @return true if the username and password were successfully added to the
	 *         arrays, false if the username already exists
	 */
	public Boolean addUser(String newUser, String newPassword) {
		for (int i = 0; i < usernames.length; i++) {
			if (usernames[i].equals(newUser)) {
				System.out.println("Username already exists.");
				return false;
			}
		}
		String[] tempUsers = usernames;
		usernames = new String[usernames.length + 1];
		String[] tempPass = passwords;
		passwords = new String[passwords.length + 1];
		for (int i = 0; i < tempUsers.length; i++) {
			usernames[i] = tempUsers[i];
			passwords[i] = tempPass[i];
		}
		usernames[usernames.length - 1] = newUser;
		passwords[passwords.length - 1] = newPassword;
		return true;
	}

	/**
	 * 
	 * @param username
	 *            username parameter to be checked
	 * @param password
	 *            password parameter to be checked
	 * @return true if the password and username match, false if not
	 */
	public boolean loginCheck(String username, String password) {
		if (this.checkUsername(username)) {
			if (this.checkPassword(password)) {
				//dbc.logInUser(dbc.findID(username));
				return true;
			}
			System.out.println("Incorrect Password. Please try again.");
		}
		System.out.println("Incorrect Username. Please try again.");
		//loginAttempts++;
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
//	public boolean checkAttempt() {
//		if (loginAttempts <= 3) {
//			return false;
//		} else {
//			return true;
//		}
//	}

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