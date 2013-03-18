package edu.gatech.cs2340.wheresmystuff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public Database(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createQuery = "CREATE TABLE user (_id integer primary key autoincrement, email, password, firstName, lastName, locked boolean, type boolean, loggedIn integer);";
		db.execSQL(createQuery);
		String createString = "CREATE TABLE item (_id integer primary key autoincrement,name, description, date, location);";
		db.execSQL(createString);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + "user");
		db.execSQL("DROP TABLE IF EXISTS " + "item");
		// Create tables again
		onCreate(db);

	}

}
