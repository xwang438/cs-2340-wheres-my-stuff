package edu.gatech.cs2340.wheresmystuff;

import android.database.sqlite.SQLiteDatabase;
import junit.framework.TestCase;

//author: Kenneth

public class InsertUserTest extends TestCase {

	protected void setUp() {
		
	}

	private UserDatabase database;

	public void testInsertUser() {
		User user = new User("test", "test");
		database.insertUser(user);
		if (database.getUser(database.getLength() - 1) == null) {
			System.out.println("No user was added to the database");
		} else {
			System.out
					.println("The user was successfully added to the database.");
		}
	}
}
