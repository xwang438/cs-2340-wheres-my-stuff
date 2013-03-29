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
import java.io.*;

@SuppressWarnings("unused")


public class RegisterActivity extends Activity {

	private EditText emailView;
	private EditText passwordView;
	private EditText firstNameView;
	private EditText lastNameView;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	//private DatabaseConnector db = new DatabaseConnector(RegisterActivity.this);
	private UserVerifier uv = new UserVerifier();

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

		firstName = firstNameView.getText().toString();
		lastName = lastNameView.getText().toString();
		username = emailView.getText().toString();
		password = passwordView.getText().toString();
		
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
		User newUser = new User(username, password, firstName, lastName, false,
					false);
		//Adds the users credentials to the arrays in User Verifier
		try {
		uv.addUser(newUser);
		} catch(IOException e) {
			System.out.println("IOException encountered. D:");
		}
		startActivity(intent);
	}

}
