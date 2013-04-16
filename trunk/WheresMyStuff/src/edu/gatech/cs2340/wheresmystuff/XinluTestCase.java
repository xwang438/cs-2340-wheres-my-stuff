package edu.gatech.cs2340.wheresmystuff;

import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import edu.gatech.cs2340.wheresmystuff.LoginActivity;

import junit.framework.TestCase;
/**
 * This is a JUnit test for the getAllItems method,
 * test to see if the method can return all of the items in the system.
 * 
 * @author Xinlu Wang
 *
 */
public class XinluTestCase extends ActivityInstrumentationTestCase2<LoginActivity> {

		private Item item;
		private Database db;
		private LoginActivity la;
		
		/**
		 * constructor for testing
		 */
		public XinluTestCase() {
			super(LoginActivity.class);
		}

		/**
		 * get the LoginView Activity and add a new item named test
		 */
		protected void setUp() throws Exception {
			la = getActivity();
			item = new Item("test");
			db.insertItem(item);
		}
		
		/**
		 * This method test to see if the item is present
		 */
		public void testGetAllItem(){
			db.getAllItems();
			if (db.getItem(db.getLength() - 1) == null) {
				System.out.println("Database cannot get all items");
			} else {
				System.out
						.println("Database successfully get all items)");
			}
		}
}
