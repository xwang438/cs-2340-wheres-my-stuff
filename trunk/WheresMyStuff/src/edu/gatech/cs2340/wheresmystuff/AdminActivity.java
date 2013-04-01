package edu.gatech.cs2340.wheresmystuff;

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
import java.io.*;
/**
 * The AdminActivity enables admins to modify user accounts. 
 * @author Kenneth
 *
 */
public class AdminActivity extends Activity {

	private EditText userToModifyField;
	private Button submitButton,cancelButton; //STOP CALLING YOUR BUTTONS "button"
	private UserVerifier uv;
	/**
	 * On create, this method sets up all the instance variables of text from the UI. And sets up an on click listener
	 * for the Submit button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		userToModifyField = (EditText) findViewById(R.id.admin_textField);
		uv = (UserVerifier) this.getIntent().getSerializableExtra("VERIFIER");

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
		this.submitButton = (Button) this.findViewById(R.id.admin_submit);
		this.submitButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AdminActivity.this,ListActivity.class);
				startActivity(intent);
			}
		});
		this.cancelButton = (Button) this.findViewById(R.id.admin_cancel);
		this.cancelButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setClass(AdminActivity.this,HomeActivity.class);
				startActivity(intent1);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_admin, menu);
		return true;
	}
	
	/**
	 * 
	 * 
	 */
	public void modifyUser(EditText userToModifyField){
		//Do something. 
		try {
		TextFile usersFile = new TextFile("file:///android_asset/users.txt");
		String userToModify = userToModifyField.getText().toString();
		String[] usernames = usersFile.getUsernames();
		
			for(int i = 0; i < usernames.length; i++) {
				if(usernames[i].equals(userToModify)) {
					if(((RadioButton)findViewById(R.id.admin_lockUserButton)).isChecked())
						usersFile.setLocked(usernames[i], true);
					else if(((RadioButton)findViewById(R.id.admin_unlockUserButton)).isChecked())
						usersFile.setLocked(usernames[i], false);
					else if(((RadioButton)findViewById(R.id.admin_makeAdminButton)).isChecked())
						usersFile.setAdmin(usernames[i], true);
					else if(((RadioButton)findViewById(R.id.admin_removeUserButton)).isChecked())
						usersFile.removeUser(usernames[i]);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
