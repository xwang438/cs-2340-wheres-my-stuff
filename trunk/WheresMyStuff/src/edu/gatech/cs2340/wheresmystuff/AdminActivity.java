package edu.gatech.cs2340.wheresmystuff;

import android.widget.*;
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
	private RadioButton makeAdminButton, removeUserButton, lockUserButton, unlockUserButton;
	private RadioGroup radioGroup;
	private UserVerifier uv;
	private User loggedInUser;
	/**
	 * On create, this method sets up all the instance variables of text from the UI. And sets up an on click listener
	 * for the Submit button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		userToModifyField = (EditText) findViewById(R.id.admin_textField);
		uv = new UserVerifier(this);
		loggedInUser = (User)this.getIntent().getSerializableExtra("LOGGED_IN_USER");

		//findViewById(R.id.admin_submit).setOnClickListener(
		//		new View.OnClickListener() {
		//			@Override
		//			public void onClick(View view) {
		//				modifyUser(userToModifyField);
		//			}
		//		});
        /**
         * Connect the AdminActivity with the ListActivity and the HomeActivity
         */
		this.submitButton = (Button) this.findViewById(R.id.admin_submit);
		this.submitButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				modifyUser(userToModifyField);
				Intent intent = new Intent();
				intent.setClass(AdminActivity.this,HomeActivity.class);
				intent.putExtra("LOGGED_IN_USER", loggedInUser);
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
				intent1.putExtra("LOGGED_IN_USER", loggedInUser);
				startActivity(intent1);
			}
		});
		
		this.makeAdminButton = (RadioButton) this.findViewById(R.id.admin_makeAdminButton);
		this.removeUserButton = (RadioButton) this.findViewById(R.id.admin_removeUserButton);
		this.lockUserButton = (RadioButton) this.findViewById(R.id.admin_lockUserButton);
		this.unlockUserButton = (RadioButton) this.findViewById(R.id.admin_unlockUserButton);
		this.radioGroup = (RadioGroup) this.findViewById(R.id.admin_radioGroup);
		
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
		//try {
		//TextFile usersFile = new TextFile("file:///android_asset/users.txt");
		String userToModify = userToModifyField.getText().toString();
		//String[] usernames = usersFile.getUsernames();
		String[] usernames = uv.getUsernames();
		
			for(int i = 0; i < usernames.length; i++) {
				if(usernames[i].equals(userToModify)) {
					if(lockUserButton.isChecked())
						uv.setLocked(i, true);
					else if(unlockUserButton.isChecked())
						uv.setLocked(i, false);
					else if(makeAdminButton.isChecked())
						uv.setAdmin(i, true);
					else if(removeUserButton.isChecked())
						uv.removeUser(usernames[i]);
				}
			}
		//} catch (IOException e) {
			//System.out.println(e.getMessage());
		//}
		
		
	}

}
