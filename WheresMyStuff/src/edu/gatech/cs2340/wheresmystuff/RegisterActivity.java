/**
 **
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
import android.content.DialogInterface;
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
	private Button registerButton;
	private AlertDialog.Builder builder;
	//private DatabaseConnector db = new DatabaseConnector(RegisterActivity.this);
	private UserVerifier uv;

	/**
	 * On create, this method sets up all the instance variables of text from
	 * the UI. And sets up an on click listener for the Register button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);	
		uv = (UserVerifier) this.getIntent().getSerializableExtra("VERIFIER");
		emailView = (EditText) findViewById(R.id.edit_email);
		passwordView = (EditText) findViewById(R.id.edit_password);
		firstNameView = (EditText) findViewById(R.id.edit_firstName);
		lastNameView = (EditText) findViewById(R.id.edit_lastName);
		View focusView = null;
		boolean cancel = false;

		builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure?").setPositiveButton("Yes", new DialogClickListener())
	    .setNegativeButton("No", new DialogClickListener());
		
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
                    else if (password.length() < 4) {
						passwordView.setError(getString(R.string.error_invalid_password));
						focusView = passwordView;
						cancel = true;
					}
					User newUser = new User(username, password, firstName, lastName, false,
							false);
					if (TextUtils.isEmpty(username)) {
						emailView.setError(getString(R.string.error_field_required));
						focusView = emailView;
						cancel = true;
					} else if (!username.contains("@") || !username.contains(".com")) {
						emailView.setError(getString(R.string.error_invalid_email));
						focusView = emailView;
						cancel = true;
					} 
					// Check for a valid and non-duplicate email address.
					if (cancel) {
						// There was an error; don't attempt login and focus the first
						// form field with an error.
						focusView.requestFocus();
					}
					else {			
							builder.show();
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
				    intent1.putExtra("VERIFIER", uv);
				    startActivity(intent1);
		        	break;
		        case DialogInterface.BUTTON_NEGATIVE:
		        	break;
		        }
		    }
		}
}