package edu.gatech.cs2340.wheresmystuff;

import android.database.Cursor;
import android.widget.RadioButton;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * The AdminActivity enables admins to modify user accounts. 
 * @author Kenneth
 *
 */
public class AdminActivity extends Activity {

	private EditText userToModifyField;
	private Button button,button1;
	/**
	 * On create, this method sets up all the instance variables of text from the UI. And sets up an on click listener
	 * for the Submit button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		userToModifyField = (EditText) findViewById(R.id.admin_textField);
		

		findViewById(R.id.admin_submit).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						modifyUser(userToModifyField);
					}
				});
        /**
         * Connect the AdminActivity with the ListActivity and the HomeActivity
         */
		this.button = (Button) this.findViewById(R.id.admin_submit);
		this.button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
		 
				//LostItem newLost = new LostItem(userToModifyField.getText().toString());
				//LostItem.item = newLost;
				Intent intent = new Intent();
				intent.setClass(AdminActivity.this,ListActivity.class);
				startActivity(intent);
			}
		});
		this.button1 = (Button) this.findViewById(R.id.admin_cancel);
		this.button1.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setClass(AdminActivity.this,HomeActivity.class);
				startActivity(intent1);
			}
		});
	}

	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_admin, menu);
		//return true;
	//}
	
	/**
	 * 
	 * 
	 */
	public void modifyUser(){
		//Do something. 
		DatabaseConnector DB = new DatabaseConnector(this);
		String userToModify = userToModifyField.getText().toString();
		Cursor cursor = DB.getAllUser();
		String[] usernames = new String[cursor.getCount()];
		
		for(int i = 0; !cursor.isAfterLast(); i++) {
			usernames[i] = cursor.getString(1);
			
			if(usernames[i].equals(userToModify)) {
				if(((RadioButton)findViewById(R.id.admin_lockUserButton)).isChecked())
					DB.updateUser(true, cursor.getInt(0));
				else if(((RadioButton)findViewById(R.id.admin_unlockUserButton)).isChecked())
					DB.updateUser(false, cursor.getInt(0));
				else if(((RadioButton)findViewById(R.id.admin_makeAdminButton)).isChecked())
					DB.updateUser(cursor.getInt(0), true);
			}
			cursor.moveToNext();
		}
			
		//Grab singleton object. uv.addUser(username, password);
		
	}

}
