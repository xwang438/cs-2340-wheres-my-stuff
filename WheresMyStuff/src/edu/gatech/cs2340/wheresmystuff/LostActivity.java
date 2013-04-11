package edu.gatech.cs2340.wheresmystuff;

import edu.gatech.cs2340.wheresmystuff.Item.ItemCategory;
import edu.gatech.cs2340.wheresmystuff.Item.ItemStatus;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * The LostActivity enable the user to add a new item that they have lost
 * 
 * @author Xinlu
 * 
 */
public class LostActivity extends Activity {

	private EditText stuffname;
	private EditText stuffloc;
	private EditText stuffdate;
	private Spinner categorySpinner, spinner3;
	private Button submitButton, cancelButton, button2;
	private static final String TAG = "LostActivity";
	private Database db;
	private Context context;

	private UserVerifier uv;

	/**
	 * On create, this method sets up all the instance variables of text from
	 * the UI. And sets up an on click listener for the Register button
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lost);
		context = getApplicationContext();
		
		db = new Database(context);
		uv = (UserVerifier) this.getIntent().getSerializableExtra("VERIFIER");

		stuffname = (EditText) findViewById(R.id.loststuff_name);
		stuffloc = (EditText) findViewById(R.id.loststuff_loc);
		stuffdate = (EditText) findViewById(R.id.loststuff_date);

		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2.add("Category");
		// adapter2.add("Electronics");
		// adapter2.add("Toys & Tools");
		// adapter2.add("Clothing, Shoes & Jewelry");
		adapter2.add("Personal Items");
		// adapter2.add("Automotive & Industrial");
		adapter2.add("Appliance");
		// adapter2.add("Grocery, Health & Beauty");
		adapter2.add("Furniture");
		categorySpinner = (Spinner) findViewById(R.id.spinner2);
		categorySpinner.setAdapter(adapter2);


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
		// I don't understand what newLost is and does. I think it should just
		// create an item, set the status to lost and then
		// add that item to the database, where the database will create the
		// list. So connecting it to list activity doesn't
		// really matter.
		this.submitButton = (Button) this.findViewById(R.id.loststuff_submit);
		this.submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Item newLost = new Item(stuffname.getText().toString());
				ItemCategory category;
				switch (categorySpinner.getSelectedItemPosition()) {
				case 1:
					category = ItemCategory.PERSONAL_ITEM;
					break;
				case 2:
					category = ItemCategory.APPLIANCE;
					break;
				case 3:
					category = ItemCategory.FURNITURE;
					break;
				default:
					category = ItemCategory.PERSONAL_ITEM;
					break;

				}

				newLost.setCategory(category);
				newLost.setStatus(ItemStatus.Lost);
				newLost.setDate(stuffdate.getText().toString());
				newLost.setLocation(stuffloc.getText().toString());
				
				/* now save to DB */
				db.insertItem(newLost);
				
				Intent intent = new Intent();
				intent.setClass(LostActivity.this, ListActivity.class);
				//intent.putExtra("ITEM", newLost);
				intent.putExtra("VERIFIER", uv);
				startActivity(intent);
			}
		});
		this.cancelButton = (Button) this.findViewById(R.id.loststuff_cancel);
		this.cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setClass(LostActivity.this, HomeActivity.class);
				intent1.putExtra("VERIFIER", uv);
				startActivity(intent1);
			}
		});
		this.button2 = (Button) this.findViewById(R.id.loststuff_logout);
		this.button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent();
				intent2.setClass(LostActivity.this, LoginActivity.class);
				intent2.putExtra("VERIFIER", uv);
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
	 * Sets the info from the Register UI as attributes for the user. Adds the
	 * username and password to the array of all users in UserVerifier
	 */
	public void createNewLost() {
		// Do something.

		String newName = stuffname.getText().toString();

	}

}