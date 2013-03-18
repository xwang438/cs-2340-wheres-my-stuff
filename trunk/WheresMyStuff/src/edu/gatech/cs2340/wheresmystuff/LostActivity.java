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
/**
 * The LostActivity enable the user to add a new item that 
 * they have lost
 * @author Xinlu
 *
 */
public class LostActivity extends Activity {

	private EditText stuffname;
	private EditText stuffloc;
	private EditText stuffdisc;
	private EditText stuffdate;
	private Button button,button1,button2;
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
        /**
         * Connect the LostActivity with the ListActivity and the HomeActivity
         */
		this.button = (Button) this.findViewById(R.id.loststuff_submit);
		this.button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
		 
				Item newLost = new Item(stuffname.getText().toString());
				Item.item = newLost;
				Intent intent = new Intent();
				intent.setClass(LostActivity.this,ListActivity.class);
				startActivity(intent);
			}
		});
		this.button1 = (Button) this.findViewById(R.id.loststuff_cancel);
		this.button1.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setClass(LostActivity.this,HomeActivity.class);
				startActivity(intent1);
			}
		});
		this.button2 = (Button) this.findViewById(R.id.loststuff_logout);
		this.button2.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent();
				intent2.setClass(LostActivity.this,MainActivity.class);
				startActivity(intent2);
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