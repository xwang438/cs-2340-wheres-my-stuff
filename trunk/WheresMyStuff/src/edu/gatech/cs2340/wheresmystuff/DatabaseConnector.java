package edu.gatech.cs2340.wheresmystuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnector {

	private static final String DB_NAME = "WMS";
	private SQLiteDatabase database;
	private Database DB;

	public DatabaseConnector(Context context) {
		DB = new Database(context, DB_NAME, null, 1);
		insertUser("admin@gatech.edu", "admin1", "Admin", "Admin", false, true);
	}

	public void open() throws SQLException {
		// open database in reading/writing mode
		database = DB.getWritableDatabase();
	}

	public void close() {
		if (database != null)
			database.close();
	}

	public void insertUser(User newUser) {
		insertUser(newUser.getUsername(), newUser.getPassword(),
				newUser.getFirstname(), newUser.getLastname(),
				newUser.isLocked(), newUser.isAdmin());
	}

	public void insertUser(String email, String password, String firstName,
			String lastName, boolean locked, boolean admin) {
		ContentValues newCon = new ContentValues();
		newCon.put("email", email);
		newCon.put("password", password);
		newCon.put("firstName", firstName);
		newCon.put("lastName", lastName);
		newCon.put("type", admin);
		newCon.put("locked", locked);
		newCon.put("loggedIn", 0);

		open();
		database.insert("user", null, newCon);
		close();
	}

	public void updateUser(int _id, String email, String password,
			String firstName, String lastName, boolean locked, boolean admin) {
		ContentValues editCon = new ContentValues();
		editCon.put("email", email);
		editCon.put("password", password);
		editCon.put("firstName", firstName);
		editCon.put("lastName", lastName);
		editCon.put("type", admin);
		editCon.put("locked", locked);
		editCon.put("loggedIn", 0);
		
		open();
		database.update("user", editCon, "_id=" + _id, null);
		close();
	}
	
	public void updateUser(boolean locked, int _id) {
		ContentValues editCon = new ContentValues();
		editCon.put("locked", locked);

		open();
		database.update("user", editCon, "_id=" + _id, null);
		close();
	}
	
	public void updateUser(int _id, boolean admin) {
		ContentValues editCon = new ContentValues();
		editCon.put("type", admin);

		open();
		database.update("user", editCon, "_id=" + _id, null);
		close();
	}

	public void logInUser(int _id) {
		ContentValues editCon = new ContentValues();
		editCon.put("loggedIn", 1);
		
		open();
		database.update("user", editCon, "_id=" + _id, null);
		close();
	}
	
	public void logOutUser(int _id) {
		ContentValues editCon = new ContentValues();
		editCon.put("loggedIn", 0);
		
		open();
		database.update("user", editCon, "_id=" + _id, null);
		close();
	}
	
	public boolean isLoggedInAsAdmin() {
		Cursor cursor = database.query("user", new String[] { "_id", "email", "type", "loggedIn"}, null,
				null, null, null, null);
		
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			  if(cursor.getInt(3) == 1)
				  if(cursor.getString(2).equals("true"))
					  return true;
				  
			  cursor.moveToNext();
		  }
		return false;
	}
	
	public boolean isAdmin() {
		Cursor cursor = database.query("user", new String[] { "_id", "email", "type"}, null,
				null, null, null, null);
		
		cursor.moveToFirst();
		
		  while(!cursor.isAfterLast()) {
			  if(cursor.getString(2).equals("true"))
				  return true;
			  cursor.moveToNext();
		  }
		return false;
	}
	
	public int findID(String user) {
		  Cursor cursor = database.query("user", new String[] { "_id", "email" }, null,
					null, null, null, null);
		  
		  cursor.moveToFirst();
		  
		  while(!cursor.isAfterLast()) {
			  if(user.equals(cursor.getString(1)))
				  return cursor.getInt(0);
			  cursor.moveToNext();
		  }
		return 0;
	  }
	
	public Cursor getAllUser() {
		return database.query("user", new String[] { "_id", "email" }, null,
				null, null, null, null);
	}
	
	public Cursor getUsersForLoginCheck() {
		return database.query("user", new String[] { "_id", "email", "password" }, null,
				null, null, null, null);
	}
	
	public String[] getUsernames(DatabaseConnector DB) {
		Cursor cursor = DB.getUsersForLoginCheck();
		String[] usernames = new String[cursor.getCount()];
		
		cursor.moveToFirst();
		
		for(int i = 0; !cursor.isAfterLast(); i++) {
			usernames[i] = cursor.getString(1);
			cursor.moveToNext();
		}
			
		
		return usernames;
	}
	
	public String[] getPasswords(DatabaseConnector DB) {
		Cursor cursor = DB.getUsersForLoginCheck();
		String[] passwords = new String[cursor.getCount()];
		
		cursor.moveToFirst();
		
		for(int i = 0; !cursor.isAfterLast(); i++) {
			passwords[i] = cursor.getString(2);
			cursor.moveToNext();
		}
			
		
		return passwords;
	}
	
	
	
	
	public Cursor getOneUser(long _id) {
		return database.query("user", null, "_id=" + _id, null, null, null,
				null);
	}

	public void deleteUser(long _id) {
		open();
		database.delete("user", "_id=" + _id, null);
		close();
	}

	public void insertItem(String name, String description, String date,
			String location) {
		ContentValues newCon = new ContentValues();
		newCon.put("name", name);
		newCon.put("description", description);
		newCon.put("date", date);
		newCon.put("location", location);

		open();
		database.insert("item", null, newCon);
		close();
	}

	public void updateItem(int _id, String name, String description,
			String date, String location) {
		ContentValues editCon = new ContentValues();
		editCon.put("name", name);
		editCon.put("description", description);
		editCon.put("date", date);
		editCon.put("location", location);

		open();
		database.update("item", editCon, "_id=" + _id, null);
		close();
	}

	public Cursor getAllItem() {
		return database.query("item", new String[] { "_id", "name" }, null,
				null, null, null, null);
	}

	public Cursor getOneItem(long _id) {
		return database.query("item", null, "_id=" + _id, null, null, null,
				null);
	}

	public void deleteItem(long _id) {
		open();
		database.delete("item", "_id=" + _id, null);
		close();
	}
}
