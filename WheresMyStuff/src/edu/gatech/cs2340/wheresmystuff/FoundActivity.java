package edu.gatech.cs2340.wheresmystuff;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * The FoundActivity enable the user to add a new item that 
 * they have Found
 * @author Xinlu
 *
 */
public class FoundActivity extends Activity {
	private EditText findname;
	private EditText findloc;
	private EditText finddate;
	private Button submitButton,cancelButton,button2;
	private static final String TAG = "FoundActivity";
	/**
	 * On create, this method sets up all the instance variables of text from the UI. And sets up an on click listener
	 * for the Register button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_found);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Category");
        adapter.add("Electronics");
        adapter.add("Toys & Tools");
        adapter.add("Clothing, Shoes & Jewelry");
        adapter.add("Automotive & Industrial");
        adapter.add("Grocery, Health & Beauty");
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.add("Status");
        adapter1.add("Available");
        adapter1.add("Returned");
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

     @Override

     public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


     Spinner spinner = (Spinner)adapterView;


     String itemContent = (String)adapterView.getItemAtPosition(position);

     }

     @Override

     public void onNothingSelected(AdapterView<?> view) {


     Log.i(TAG,  view.getClass().getName());

     }
             });

		findname = (EditText) findViewById(R.id.find_name);
		findloc = (EditText) findViewById(R.id.find_loc);
		finddate = (EditText) findViewById(R.id.find_date);
		
		findViewById(R.id.find_submit).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						createNewLost();
					}
				});

        /**
         * Connect the LostActivity with the ListActivity and the HomeActivity
         */
		//I don't understand what newLost is and does. I think it should just create an item, set the status to lost and then
		// add that item to the database, where the database will create the list. So connecting it to list activity doesn't
		// really matter. 
		this.submitButton = (Button) this.findViewById(R.id.find_submit);
		this.submitButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
		 
				Item newFound = new Item(findname.getText().toString());
				Item.item = newFound;
				Intent intent = new Intent();
				intent.setClass(FoundActivity.this,ListActivity.class);
				startActivity(intent);
			}
		});
		this.cancelButton = (Button) this.findViewById(R.id.find_cancel);
		this.cancelButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setClass(FoundActivity.this,HomeActivity.class);
				startActivity(intent1);
			}
		});

		this.button2 = (Button) this.findViewById(R.id.find_logout);
		this.button2.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent();
				intent2.setClass(FoundActivity.this,MainActivity.class);
				startActivity(intent2);
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_found, menu);
		return true;
	}
	
	/**
	 * Sets the info from the Register UI as attributes for the user. Adds the username and password to the array of all users
	 * in UserVerifier
	 */
	public void createNewLost(){
		//Do something. 
		
		String newName = findname.getText().toString();
		
		//Add lost item to the database???
		//Create new database connector
		//dc.insertItem()
		
	}

}
