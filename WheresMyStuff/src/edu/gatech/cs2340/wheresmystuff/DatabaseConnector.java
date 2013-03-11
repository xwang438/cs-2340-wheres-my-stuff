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
	}
	
	   public void open() throws SQLException 
	   {
	      //open database in reading/writing mode
	      database = DB.getWritableDatabase();
	   } 

	   public void close() 
	   {
	      if (database != null)
	         database.close();
	   }	   
	   
	   public void insertUser(String email, String password, String firstName, String lastName, int type, int locked) 
			   {
			      ContentValues newCon = new ContentValues();
			      newCon.put("email", email);
			      newCon.put("password", password);
			      newCon.put("firstName", firstName);
			      newCon.put("lastName", lastName);
			      newCon.put("type", type);
			      newCon.put("locked", locked);

			      open();
			      database.insert("user", null, newCon);
			      close();
			   }

			
			   public void updateUser(int _id, String email, String password, String firstName, String lastName, int type, int locked) 
			   {
			      ContentValues editCon = new ContentValues();
			      editCon.put("email", email);
			      editCon.put("password", password);
			      editCon.put("firstName", firstName);
			      editCon.put("lastName", lastName);
			      editCon.put("type", type);
			      editCon.put("locked", locked);


			      open();
			      database.update("user", editCon, "_id=" + _id, null);
			      close();
			   }

			  
			   public Cursor getAllUser() 
			   {
			      return database.query("user", new String[] {"_id", "email"}, 
			         null, null, null, null, null);
			   }

			   public Cursor getOneUser(long _id) 
			   {
			      return database.query("user", null, "_id=" + _id, null, null, null, null);
			   }
			   
			   public void deleteUser(long _id) 
			   {
			      open(); 
			      database.delete("user", "_id=" + _id, null);
			      close();
			   }
			   public void insertItem(String name, String description, String date, String location) 
			   {
			      ContentValues newCon = new ContentValues();
			      newCon.put("name", name);
			      newCon.put("description", description);
			      newCon.put("date", date);
			      newCon.put("location", location);

			      open();
			      database.insert("item", null, newCon);
			      close();
			   }

			
			   public void updateItem(int _id, String name, String description, String date, String location) 
			   {
			      ContentValues editCon = new ContentValues();
			      editCon.put("name", name);
			      editCon.put("description", description);
			      editCon.put("date", date);
			      editCon.put("location", location);


			      open();
			      database.update("item", editCon, "_id=" + _id, null);
			      close();
			   }

			  
			   public Cursor getAllItem() 
			   {
			      return database.query("item", new String[] {"_id", "name"}, 
			         null, null, null, null, null);
			   }

			   public Cursor getOneItem(long _id) 
			   {
			      return database.query("item", null, "_id=" + _id, null, null, null, null);
			   }
			   
			   public void deleteItem(long _id) 
			   {
			      open(); 
			      database.delete("item", "_id=" + _id, null);
			      close();
			   }
}
