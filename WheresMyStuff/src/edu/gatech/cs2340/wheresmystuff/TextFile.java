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
	
	// Basic constructor, creates a "default" username and password for demo
	// purposes
	public TextFile(String file) throws IOException {
		filename = file;
		PrintWriter opener = new PrintWriter(file);
		opener.close();
	}

	/**
	 * 
	 * @param index
	 *            the index in the array at which the desired username can be
	 *            found
	 * @return returns the found username as a string
	 */
	
	public boolean addUser(User user) throws IOException {
		String info = user.getUsername() + ":" + user.getPassword() + ":" + 
				user.getFirstname() + ":" + user.getLastname() + ":";
		
		if(user.isLocked())
			info += "true:";
		else
			info += "false";
		
		if(user.isAdmin())
			info += "true:";
		else
			info += "false";
			
		
		openInput();
		inputFile.println(info);
		closeInput();
		
		return true;
	}
	
	public boolean setLocked(String username, boolean lock) throws IOException {
		String info = getRow(username);
		int indexOfUser = getIndexOfUser(username);
		String[] allLines = new String[getNumberOfLines()];
		String newInfo;
		
		if(getLocked(info) == lock)
			return true;
		else {
			tokenizer = new StringTokenizer(info,":");
			newInfo = tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" + 
					tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":";
			
			if(lock)
				newInfo += "true:" + tokenizer.nextToken();
			else
				newInfo += "false:" + tokenizer.nextToken();
			
		}
		
		openOutput();
		for(int i = 0; i < indexOfUser; i++) {
			allLines[i] = outputFile.nextLine();
			if(i+1 == indexOfUser) {
				i++;
				allLines[i] = newInfo;
				while(outputFile.hasNext()) {
					i++;
					allLines[i] = outputFile.nextLine();
				}
			}
		}
		closeOutput();
		
		inputFile = new PrintWriter(filename);
		for(int i = 0; i < allLines.length; i++) {
				inputFile.println(allLines[i]);
		}
		closeInput();
		
		return true;
	}
	
	public boolean setAdmin(String username, boolean admin) throws IOException {
		String info = getRow(username);
		int indexOfUser = getIndexOfUser(username);
		String[] allLines = new String[getNumberOfLines()];
		String newInfo;
		
		if(getAdmin(info) == admin)
			return true;
		else {
			tokenizer = new StringTokenizer(info,":");
			newInfo = tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" + 
					tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":";
			
			if(admin)
				newInfo += tokenizer.nextToken() + ":true";
			else
				newInfo += tokenizer.nextToken() + ":false";
			
		}
		
		openOutput();
		for(int i = 0; i < indexOfUser; i++) {
			allLines[i] = outputFile.nextLine();
			if(i+1 == indexOfUser) {
				i++;
				allLines[i] = newInfo;
				while(outputFile.hasNext()) {
					i++;
					allLines[i] = outputFile.nextLine();
				}
			}
		}
		closeOutput();
		
		inputFile = new PrintWriter(filename);
		for(int i = 0; i < allLines.length; i++) {
				inputFile.println(allLines[i]);
		}
		closeInput();
		
		return true;
	}
	
	public boolean removeUser(String username) throws IOException {
		int indexOfUser = getIndexOfUser(username);
		
		if(indexOfUser == -1)
			return true;
		
		String[] allLines = new String[getNumberOfLines()-1];
		
		openOutput();
		for(int i = 0; i < indexOfUser; i++) {
			allLines[i] = outputFile.nextLine();
			if(i+1 == indexOfUser) {
				outputFile.nextLine();
				while(outputFile.hasNext()) {
					i++;
					allLines[i] = outputFile.nextLine();
				}
			}
		}
		closeOutput();
		
		inputFile = new PrintWriter(filename);
		for(int i = 0; i < allLines.length; i++) {
				inputFile.println(allLines[i]);
		}
		closeInput();
		
		return true;
	}
	
	public boolean logInUser(int index) throws IOException {
		String info = getRow(index);
		int indexOfUser = index;
		String[] allLines = new String[getNumberOfLines()];
		String newInfo;
		
		tokenizer = new StringTokenizer(info,":");
		newInfo = tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" + 
				tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" +
				tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":loggedIn";
		
		openOutput();
		for(int i = 0; i < indexOfUser; i++) {
			allLines[i] = outputFile.nextLine();
			if(i+1 == indexOfUser) {
				i++;
				allLines[i] = newInfo;
				while(outputFile.hasNext()) {
					i++;
					allLines[i] = outputFile.nextLine();
				}
			}
		}
		closeOutput();
		
		inputFile = new PrintWriter(filename);
		for(int i = 0; i < allLines.length; i++) {
				inputFile.println(allLines[i]);
		}
		closeInput();
		
		return true;
	}
	
	public boolean logOutUser(String username) throws IOException {
		String info = getRow(username);
		int indexOfUser = getIndexOfUser(username);
		String[] allLines = new String[getNumberOfLines()];
		String newInfo;
		
		tokenizer = new StringTokenizer(info,":");
		newInfo = tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" + 
				tokenizer.nextToken() + ":" + tokenizer.nextToken() + ":" +
				tokenizer.nextToken() + ":" + tokenizer.nextToken();
		
		openOutput();
		for(int i = 0; i < indexOfUser; i++) {
			allLines[i] = outputFile.nextLine();
			if(i+1 == indexOfUser) {
				i++;
				allLines[i] = newInfo;
				while(outputFile.hasNext()) {
					i++;
					allLines[i] = outputFile.nextLine();
				}
			}
		}
		closeOutput();
		
		inputFile = new PrintWriter(filename);
		for(int i = 0; i < allLines.length; i++) {
				inputFile.println(allLines[i]);
		}
		closeInput();
		
		return true;
	}
	
	public String getLoggedInUser() throws IOException {
		String info;
		String loggedInUser = "";
		
		openOutput();
		while(outputFile.hasNext()) {
			info = outputFile.nextLine();
			tokenizer = new StringTokenizer(info,":");
			if(tokenizer.countTokens() == 7)
				loggedInUser = tokenizer.nextToken();
		}
		closeOutput();
		
		return loggedInUser;
	}
	
	
	
	
	
	
	
	
	
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
	
	public int getIndexOfUser(String username) throws IOException {
		int i = 0;
		boolean found = false;
		openOutput();
		while(outputFile.hasNext() && !found) {
			if(username.equals(getUsername(outputFile.nextLine())))
				found = true;
			i++;
		}
		closeOutput();
		
		if(!found) i = -1;
		
		return i;
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
		while(outputFile.hasNext() && i < rowIndex) {
			if(i+1 == rowIndex) {
				rowData = outputFile.nextLine();
			}
			i++;
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
	

	/**
	 * 
	 * @param user
	 *            loops through list of usernames and checks if the input
	 *            parameter exists in the list
	 * @return returns true if the username exists, false is it doesn't
	 */
	

	/**
	 * 
	 * @param user
	 *            loops through list of passwords and checks if the input
	 *            parameter exists in the list
	 * @return returns true if the password exists, false is it doesn't
	 */
	

	/**
	 * 
	 * @param newUser
	 *            adds this parameter to the usernames array
	 * @param newPassword
	 *            adds this parameter to the passwords array
	 * @return true if the username and password were successfully added to the
	 *         arrays, false if the username already exists
	 */
	

	/**
	 * 
	 * @param username
	 *            username parameter to be checked
	 * @param password
	 *            password parameter to be checked
	 * @return true if the password and username match, false if not
	 */
	

	/**
	 * 
	 * @param attempts
	 *            a variable describing how many times the user has tried to
	 *            login
	 * @return true if login may proceed, false if the user has failed too many
	 *         times
	 */

	/**
	 * 
	 * @return returns the values of the lists of usernames and passwords as a
	 *         string
	 */
	

}