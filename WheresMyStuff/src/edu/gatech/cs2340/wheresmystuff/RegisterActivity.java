/**
 * @author Emily
 */
package edu.gatech.cs2340.wheresmystuff;

import edu.gatech.cs2340.wheresmystuff.LoginActivity.UserLoginTask;
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
public class RegisterActivity extends Activity {

	private EditText emailView;
	private EditText passwordView;
	private EditText firstNameView;
	private EditText lastNameView;
	
	/**
	 * On create, this method sets up all the instance variables of text from the UI. And sets up an on click listener
	 * for the Register button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		emailView = (EditText) findViewById(R.id.edit_email);
		passwordView = (EditText) findViewById(R.id.edit_password);
		firstNameView = (EditText) findViewById(R.id.edit_firstName);
		lastNameView = (EditText) findViewById(R.id.edit_lastName);
		

		findViewById(R.id.register_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						createNewAccount();
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_register, menu);
		return true;
	}
	
	/**
	 * Sets the info from the Register UI as attributes for the user. Adds the username and password to the array of all users
	 * in UserVerifier
	 */
	public void createNewAccount(){
		//Do something. 
		
		String firstName = firstNameView.getText().toString();
		String lastName = lastNameView.getText().toString();
		String username = emailView.getText().toString();
		String password = passwordView.getText().toString();
		
		//Grab singleton object. uv.addUser(username, password);
		Intent intent = new Intent();
		intent.setClass(RegisterActivity.this,HomeActivity.class);
		startActivity(intent);
		
	}
	
	private void saveUser() 
	   {
	      DatabaseConnector DB = new DatabaseConnector(this);
	      //in type, 0 means regular user and 1 is administrator.
	      //in locked, 0 means unlocked and 1 is locked.
	      DB.insertUser(emailView.getText().toString(), passwordView.getText().toString(),firstNameView.getText().toString(), lastNameView.getText().toString(), 0, 0);
	      /*if (getIntent().getExtras() == null)
	      {
	    	  DB.insertContact(emailView.getText().toString(),
	    			  passwordView.getText().toString(),
	    			  lastNameView.getText().toString(), firstNameView.getText().toString());
	      }
	      else
	      {
	         DB.updateContact(rowID,
	        		 emailView.getText().toString(),
	    			  passwordView.getText().toString(),
	    			  lastNameView.getText().toString(), firstNameView.getText().toString());
	      }
*/	   }


}
