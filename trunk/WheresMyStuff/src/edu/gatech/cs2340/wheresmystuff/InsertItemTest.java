package edu.gatech.cs2340.wheresmystuff;

import android.database.sqlite.SQLiteDatabase;
import junit.framework.TestCase;

public class InsertItemTest extends TestCase {

	protected void setUp() {
		
	}

	private Database database;

	public void testInsertItem() {
		Item item = new Item("test");
		database.insertItem(item);
		if (database.getItem(database.getLength() - 1) == null) {
			System.out.println("No object was added to the database");
		} else {
			System.out
					.println("THe item was successfully added to the database. :)");
		}
	}
}
