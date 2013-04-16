package edu.gatech.cs2340.wheresmystuff;

import edu.gatech.cs2340.wheresmystuff.LoginActivity.UserLoginTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.io.*;

/**
 * The RegisterActivity enable the user to register a new account
 * 
 * @author Emily Jackson
 * 
 */
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
	private Button registerButton;
	private AlertDialog.Builder alertBuilder;
	//private DatabaseConnector db = new DatabaseConnector(RegisterActivity.this);
	private UserVerifier uv;
	private User loggedInUser;

	/**
	 * On create, this method sets up all the instance variables of text from
	 * the UI. And sets up an on click listener for the Register button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		//if(this.getIntent().hasExtra("VERIFIER"))
			//uv = (UserVerifier) this.getIntent().getSerializableExtra("VERIFIER");
		//else
			uv = new UserVerifier(this);
		emailView = (EditText) findViewById(R.id.edit_email);
		passwordView = (EditText) findViewById(R.id.edit_password);
		firstNameView = (EditText) findViewById(R.id.edit_firstName);
		lastNameView = (EditText) findViewById(R.id.edit_lastName);
		View focusView = null;
		boolean cancel = false;

		alertBuilder = new AlertDialog.Builder(this);
		alertBuilder.setMessage("Are you sure?").setNegativeButton("No", new DialogClickListener())
	    .setPositiveButton("Yes", new DialogClickListener());
		
		this.registerButton = (Button) this.findViewById(R.id.register_button);
	    this.registerButton.setOnClickListener(new OnClickListener() {
			   @Override
			   public void onClick(View v) {
				    firstName = firstNameView.getText().toString();
					lastName = lastNameView.getText().toString();
					username = emailView.getText().toString();
					password = passwordView.getText().toString();
					View focusView = null;
					boolean cancel = false;
					if (TextUtils.isEmpty(password)) {
						passwordView.setError(getString(R.string.error_field_required));
						focusView = passwordView;
						cancel = true;
					}
          
					User newUser = new User(2, username, password, firstName, lastName, false,
							false);
					if (TextUtils.isEmpty(username)) {
						emailView.setError(getString(R.string.error_field_required));
						focusView = emailView;
						cancel = true;
					} 
					// Check for a valid and non-duplicate email address.
					
					else {			
						    alertBuilder.show();
							//startActivity(load);
					}
		    
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
		   
		    User newUser = new User(username, password, firstName, lastName, false,
					false);
		    //Adds the users credentials to the arrays in User Verifier
		    uv.addUser(newUser);
		    loggedInUser = uv.getLoggedInUser();
	    }
		private class DialogClickListener implements DialogInterface.OnClickListener{

			/*
			 * If the user clicks yes on the display box, the activity
			 * creates a new member and starts the login activity
			 * If they click no, then the message goes away  and nothing
			 * happens
			 */
			@Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
					createNewAccount();
				    Intent intent1 = new Intent();
				    intent1.setClass(RegisterActivity.this, HomeActivity.class);
				    //intent1.putExtra("VERIFIER", uv);
				    intent1.putExtra("LOGGED_IN_USER", loggedInUser);
				    startActivity(intent1);
		        	break;
		        case DialogInterface.BUTTON_NEGATIVE:
		        	break;
		        }
		    }
		}
}