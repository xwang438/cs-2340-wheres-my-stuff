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
	private String firstName = firstNameView.getText().toString();
	private String lastName = lastNameView.getText().toString();
	private String username = emailView.getText().toString();
	private String password = passwordView.getText().toString();

	/**
	 * On create, this method sets up all the instance variables of text from
	 * the UI. And sets up an on click listener for the Register button
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
	 * Sets the info from the Register UI as attributes for the user. Adds the
	 * username and password to the array of all users in UserVerifier
	 */
	public void createNewAccount() {

		Intent intent = new Intent();
		intent.setClass(RegisterActivity.this, HomeActivity.class);
		startActivity(intent);
		saveUser();

	}

	private void saveUser() {
		DatabaseConnector DB = new DatabaseConnector(this);
		// in type, false means regular user and true is administrator.
		// in locked, false means unlocked and true is locked.
		User newUser = new User(username, password, firstName, lastName, false,
				false);
		DB.insertUser(newUser);
		/*
		 * if (getIntent().getExtras() == null) {
		 * DB.insertContact(emailView.getText().toString(),
		 * passwordView.getText().toString(), lastNameView.getText().toString(),
		 * firstNameView.getText().toString()); } else { DB.updateContact(rowID,
		 * emailView.getText().toString(), passwordView.getText().toString(),
		 * lastNameView.getText().toString(),
		 * firstNameView.getText().toString()); }
		 */}

}
