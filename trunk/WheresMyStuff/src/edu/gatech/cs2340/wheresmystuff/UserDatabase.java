package edu.gatech.cs2340.wheresmystuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.wheresmystuff.Item.ItemCategory;
import edu.gatech.cs2340.wheresmystuff.Item.ItemStatus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * UserDatabase.java
 * Database for users.
 * @author Kenneth Craig
 **/

/**
 * This class handles the saving/loading of user information
 * for the "Where's My Stuff" App
 **/

public class UserDatabase extends SQLiteOpenHelper { 

	private SQLiteDatabase database;
	// Database Version
	private static final int DB_Version = 9;

	// Database Name
	private static final String DB_Name = "DBUsers";

	public UserDatabase(Context context) {
		super(context, DB_Name, null, DB_Version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String Create_Itable = "CREATE TABLE users (id Integer primary key autoincrement, username, password, firstName, lastName, locked, admin)";
		db.execSQL(Create_Itable);

	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + "users");
		// Create tables again
		onCreate(db);

	}

	/**
	 * @return returns the length of the list of users.
	 */
	public int getLength(){
		ArrayList<User> users = this.getAllUsers();
		return users.size();
	}
	
	
	/**
	 * @param user
	 *            the user to be added to the database.
	 */
	public void insertUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newCon = new ContentValues();
		//newCon.put("id", user.getID());
		newCon.put("username", user.getUsername());
		newCon.put("password", user.getPassword());
		newCon.put("firstName", user.getFirstName());
		newCon.put("lastName", user.getLastName());
		newCon.put("locked", user.isLocked() ? "true" : "false");
		newCon.put("admin", user.isAdmin() ? "true" : "false");

		db.insert("users", null, newCon);
		db.close();
	}
	
	/**
	 * @param user
	 *            user whose information is being updated.
	 * @return index of row updated in database.
	 */
	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newCon = new ContentValues();
		//newCon.put("id", user.getID());
		newCon.put("username", user.getUsername());
		newCon.put("password", user.getPassword());
		newCon.put("firstName", user.getFirstName());
		newCon.put("lastName", user.getLastName());
		newCon.put("admin", user.isAdmin());
		newCon.put("locked", user.isLocked());
		return db.update("users", newCon, "id",
				new String[] { String.valueOf(user.getID()) });
	}

	/**
	 * @return list of users from database.
	 */
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<User>();

		String selectQuery = "SELECT  * FROM " + "users";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				User user = new User();
				user.setID(cursor.getInt(0)); //changed from parseInt to getInt
				user.setUsername(cursor.getString(1));
				user.setPassword(cursor.getString(2));
				user.setFirstName(cursor.getString(3));
				user.setLastName(cursor.getString(4));
				user.setLocked(Boolean.valueOf(cursor.getString(5)));
				user.setAdmin(Boolean.valueOf(cursor.getString(6)));
				
				// Adding users to list
				users.add(user);
			} while (cursor.moveToNext());
		}
		// return item list
		db.close();
		
		return users;

	}
	
	/**
	 * @return users filtered by username.
	 */
	private ArrayList<User> filterByUsername(){//NEW - NEW - NEW
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<User> currentUsers = this.getAllUsers();
		int index = 0;
		String firstUser = currentUsers.get(0).getUsername();
		String firstPassword = currentUsers.get(0).getPassword();
		String firstFirstName = currentUsers.get(0).getFirstName();
		String firstLastName = currentUsers.get(0).getLastName();
		boolean firstLocked = currentUsers.get(0).isLocked();
		boolean firstAdmin = currentUsers.get(0).isAdmin();

			for(int x = 0; x < currentUsers.size(); x++){//looping through the list and removing used items
				for(int y = 0; y < currentUsers.size(); y++){//comparing each name
					for(int z = 0; z < firstUser.length(); z++){//comparing each letter
						if(currentUsers.get(y).getUsername().substring(z,z+1).equalsIgnoreCase(firstUser.substring(z,z+1)) == false){
							if(currentUsers.get(y).getUsername().substring(z,z+1).compareToIgnoreCase(firstUser.substring(z,z+1)) > 0){
								firstUser = currentUsers.get(y).getUsername();
								firstPassword = currentUsers.get(y).getPassword();
								firstFirstName = currentUsers.get(y).getFirstName();
								firstLastName = currentUsers.get(y).getLastName();
								firstLocked = currentUsers.get(y).isLocked();
								firstAdmin = currentUsers.get(y).isAdmin();
								index = y;
							}
							else{break;}
						}
						
					}
				}
				users.add(new User(firstUser, firstPassword, firstFirstName, firstLastName, firstLocked, firstAdmin));
				currentUsers.remove(index);
			}

		return users;
	}
	
	/**
	 * 
	 * @param find
	 * 				username being searched for.
	 * @return the user with a matching username.
	 */
	
	public User searchByUsername(String find){//NEW - NEW - NEW
		ArrayList<User> searchList = this.filterByUsername();
		User found = new User();
		
		for(int i = 0; i < searchList.size(); i++){
			if(find.equals(searchList.get(i).getUsername())) {
				found = searchList.get(i);
				break;
			}
			
		}
		return found;
	}
	
	/**
	 * 	
	 * @param cursor
	 * 					indicates the users' locations in the database
	 * @return user made from the cursor.
	 */
	private User cursorToUser(Cursor cursor) {
		User user = new User();
		if (cursor != null) {
			user.setID(cursor.getInt(0));
			user.setUsername(cursor.getString(1));
			user.setPassword(cursor.getString(2));
			user.setFirstName(cursor.getString(3));
			user.setLastName(cursor.getString(4));
			user.setLocked(cursor.getString(5).equals("true") ? true : false);
			user.setAdmin(cursor.getString(6).equals("true") ? true : false);
		}
		return user;

	}

	/**
	 * 
	 * @param uid
	 * 				index to find user at in database.
	 * @return the user at the specified index in the database.
	 */

	User getUser(int uid) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query("users", new String[] { "id", "username",
				"password", "firstName", "lastName", "locked", "admin", },
				"id" + "=?", new String[] { String.valueOf(uid) }, null,
				null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		// return user
		return cursorToUser(cursor);
	}

	/**
	 * removes a user from the database.
	 * @param user
	 * 				user to be removed from the database.
	 */
	
	public void deleteUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("users", "id",
				new String[] { String.valueOf(user.getID()) });
		db.close();
	}
}
