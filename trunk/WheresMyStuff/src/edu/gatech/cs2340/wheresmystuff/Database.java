package edu.gatech.cs2340.wheresmystuff;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.wheresmystuff.Item.ItemCategory;
import edu.gatech.cs2340.wheresmystuff.Item.ItemStatus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	private SQLiteDatabase database;

	// Database Version
	private static final int DB_Version = 9;

	// Database Name
	private static final String DB_Name = "DBItems";

	public Database(Context context) {
		super(context, DB_Name, null, DB_Version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String Create_Itable = "CREATE TABLE items (itemID Integer primary key autoincrement, name, description,userID, status, category, resolved, date, location)";
		db.execSQL(Create_Itable);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + "items");
		// Create tables again
		onCreate(db);

	}

	public void insertItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newCon = new ContentValues();
//		newCon.put("itemID", item.getIid());
		newCon.put("name", item.getName());
		newCon.put("description", item.getDescription());
		newCon.put("userID", item.getUid());
		newCon.put("status", String.valueOf(item.getStatus()));
		newCon.put("category", String.valueOf(item.getCategory()));
		newCon.put("date", item.getDate());
		newCon.put("location", item.getLocation());

		db.insert("items", null, newCon);
		db.close();
	}

	public int updateItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues newCon = new ContentValues();
//		newCon.put("itemID", item.getIid());
		newCon.put("name", item.getName());
		newCon.put("description", item.getDescription());
		newCon.put("userID", item.getUid());
		newCon.put("status", String.valueOf(item.getStatus()));
		newCon.put("category", String.valueOf(item.getCategory()));
		newCon.put("resolved", item.getResolved());
		newCon.put("date", item.getDate());
		newCon.put("location", item.getLocation());
		return db.update("items", newCon, "itemID",
				new String[] { String.valueOf(item.getIid()) });
	}

	public ArrayList<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		// Cursor cursor = database.query("DBItems", new String[] {"itemID",
		// "name", "userID", "status", "category", "resolved", "date",
		// "location"}, null, null, null, null, null);

		String selectQuery = "SELECT  * FROM " + "items";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Item item = new Item();
				item.setIid(Integer.parseInt(cursor.getString(0)));
				item.setName(cursor.getString(1));
				item.setDescription(cursor.getString(2));
				item.setUid(cursor.getString(3));
				
				String status = cursor.getString(4);
				if (status.equals("Lost")) item.setStatus(ItemStatus.Lost);
				else if (status.equals("Found")) item.setStatus(ItemStatus.Found);
				else if (status.equals("Returned")) item.setStatus(ItemStatus.Returned);
				else item.setStatus(ItemStatus.Donation);
				
				String category = cursor.getString(5);
				if (category.equals("Personal Item")) item.setCategory(ItemCategory.PERSONAL_ITEM);
				else if (category.equals("Appliance")) item.setCategory(ItemCategory.APPLIANCE);
				else item.setCategory(ItemCategory.FURNITURE);
				
				item.setResolved(Boolean.valueOf(cursor.getString(6)));
				item.setDate(cursor.getString(7));
				item.setLocation(cursor.getString(8));
				// Adding items to list
				items.add(item);
			} while (cursor.moveToNext());
		}
		// return item list
		db.close();
		
		return items;

	}
	
	private ArrayList<Item> filterByName(){//NEW - NEW - NEW
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Item> currentItems = this.getAllItems();
		int index = 0;
		String firstItem = currentItems.get(0).getName();
		

			for(int x = 0; x < currentItems.size(); x++){//looping through the list and removing used items
				for(int y = 0; y < currentItems.size(); y++){//comparing each name
					for(int z = 0; z < firstItem.length(); z++){//comparing each letter
						if(currentItems.get(y).getName().substring(z,z+1).equalsIgnoreCase(firstItem.substring(z,z+1)) == false){
							if(currentItems.get(y).getName().substring(z,z+1).compareToIgnoreCase(firstItem.substring(z,z+1)) > 0){
								firstItem = currentItems.get(y).getName();
								index = y;
							}
							else{break;}
						}
						
					}
				}
				items.add(currentItems.get(index));
				currentItems.remove(index);
			}

		return items;
	}
	
	private ArrayList<Item> filterByDate(){//NEW - NEW - NEW
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Item> currentItems = this.getAllItems();
		int lowDate = 0;
		String low;
		int lowIndex = 0;
		
		
		for(int x = 0; x < currentItems.size(); x++){
			for(int i = 0; i < currentItems.size(); i++){
				if(i == 0){
					low = currentItems.get(i).getDate();//sample date: 2013/02/02
					lowDate = Integer.parseInt(low.substring(0,4) + low.substring(5,7) + low.substring(8));
					lowIndex = i;}
				else{
					if(lowDate > Integer.parseInt(currentItems.get(i).getDate())){
						lowDate = Integer.parseInt(currentItems.get(i).getDate());
						lowIndex = i;}
				}
			}
			items.add(currentItems.get(lowIndex));
			currentItems.remove(lowIndex);
		}
		return items;
	}
	
	private ArrayList<Item> filterByCategory(){//NEW - NEW - NEW
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Item> currentItems = this.getAllItems();
		ArrayList<String> categs = new ArrayList<String>();
		categs.add("Personal Item");
		categs.add("Appliance");
		categs.add("Furniture");
		
		
		
		for(int x = 0; x < categs.size(); x++){
			for(int i = 0; i < currentItems.size(); i++){
				for(int y = 0; y < currentItems.size(); y++){
					if(categs.get(x).toString().equals(currentItems.get(y).getCategory().toString())){
						items.add(currentItems.get(y));
						currentItems.remove(y);
					}
				}				
			}
		}
		return items;
	}
	
	public Item searchByName(String find){//NEW - NEW - NEW
		ArrayList<Item> searchList = this.filterByName();
		Item found = null;
		
		for(int i = 0; i < searchList.size(); i++){
			if(find.equalsIgnoreCase(searchList.get(i).getName())){
				found = searchList.get(i);
				break;
			}
			
		}
		return found;
	}
	
	public Item searchByCategory(String category){//NEW - NEW - NEW
		ArrayList<Item> searchList = this.filterByCategory();
		Item found = null;
		
		for(int i =0; i < searchList.size(); i++){
			if(category.equalsIgnoreCase(searchList.get(i).getCategory().toString())){
				found = searchList.get(i);
				break;
			}
		}
		return found;
	}

	private Item cursorToItem(Cursor cursor) {
		Item item = new Item();
		if (cursor != null) {

			Boolean resolved;
			if (cursor.getString(6).equals("false")) {
				resolved = false;
			} else {
				resolved = true;
			}

			item.setUid(cursor.getString(0));
			item.setName(cursor.getString(1));
			item.setDescription(cursor.getString(2));
			item.setIid(cursor.getInt(3));
			item.setStatus(ItemStatus.valueOf(cursor.getString(4)));
			item.setCategory(ItemCategory.valueOf(cursor.getString(5)));
			item.setResolved(resolved);
			item.setDate(cursor.getString(7));
			item.setLocation(cursor.getString(8));
		}
		return item;

	}

	// itemID Integer primary key, name, description,userID, status, category,
	// resolved, date, location

	Item getItem(int Iid) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query("items", new String[] { "itemID", "name",
				"description", "userID", "status", "category", "resolved",
				"date", "location" }, "itemID" + "=?",
				new String[] { String.valueOf(Iid) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		// return item
		return cursorToItem(cursor);

	}

	public void deleteItem(Item item) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("items", "itemID",
				new String[] { String.valueOf(item.getIid()) });
		db.close();
	}
}
