package edu.gatech.cs2340.wheresmystuff;

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

public class LostActivity extends Activity {

	private EditText stuffname;
	private EditText stuffloc;
	private EditText stuffdisc;
	private EditText stuffdate;
	private Button button,button1;
	/**
	 * On create, this method sets up all the instance variables of text from the UI. And sets up an on click listener
	 * for the Register button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost);
		
		stuffname = (EditText) findViewById(R.id.loststuff_name);
		

		findViewById(R.id.loststuff_submit).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						createNewLost();
					}
				});

		this.button = (Button) this.findViewById(R.id.loststuff_submit);
		this.button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LostActivity.this,HomeActivity.class);
				startActivity(intent);
			}
		});
		this.button1 = (Button) this.findViewById(R.id.loststuff_cancle);
		this.button1.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LostActivity.this,HomeActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lost, menu);
		return true;
	}
	
	/**
	 * Sets the info from the Register UI as attributes for the user. Adds the username and password to the array of all users
	 * in UserVerifier
	 */
	public void createNewLost(){
		//Do something. 
		
		String newName = stuffname.getText().toString();
		
		//Grab singleton object. uv.addUser(username, password);
		
	}

}
