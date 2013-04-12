package edu.gatech.cs2340.wheresmystuff;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SearchActivity extends Activity {

	private EditText editText;

	// used in the ListViews
	private ArrayAdapter<String> adapterItems;
	private ArrayAdapter<String> adapterTemp;
	private ListView itemsList;
	private Database db;//NEW - NEW - NEW
	private UserVerifier uv;//NEW - NEW - NEW
	private Context context;//NEW - NEW - NEW
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		context = getApplicationContext();//NEW - NEW - NEW
		db = new Database(context);//NEW - NEW - NEW
		uv = (UserVerifier) this.getIntent().getSerializableExtra("VERIFIER");//NEW - NEW - NEW

		// item List array of strings that appears on screen
		itemsList = (ListView) findViewById(R.id.listview);
		itemsList.setAdapter(adapterItems);

		// Button listener for Search
		findViewById(R.id.btnSearchForItems).setOnClickListener(
				new SearchItemClickListener());
	}

	/**
	 * This class is a listener for the Search button and when it is clicked it
	 * searches the database based on the radio button (either name or category)
	 * and produces a list below.
	 */
	public class SearchItemClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String searchCriteria = editText.getText().toString();

			RadioButton name = (RadioButton) findViewById(R.id.radioName);
			RadioButton category = (RadioButton) findViewById(R.id.radioCategory);

			if (name.isChecked()) {
				Item foundItem = db.searchByName(searchCriteria);
				if(foundItem == null){}
				else{
					adapterTemp.clear();
					adapterTemp.add(foundItem);
				}
				// search by name
				//ArrayList<Item> tempNames = SearchHelper
				//		.searchByName(searchCriteria);
				//for (Item item : tempNames) {
					//adapterTemp.add(item.getName());
				//}
			}
			if (category.isChecked()) {
				Item foundItem = db.searchByCategory(searchCriteria);
				if(foundItem == null){}
				else{
					adapterTemp.clear();
					adapterTemp.add(foundItem);
				}
				// search by category
			//	ArrayList<Item> tempCategories = SearchHelper
				//		.searchByCategory(searchCriteria);
				//for (Item item : tempCategories) {
				//	adapterTemp.add(item.getName());
				}
			
            
			// gets currently selected search type
			RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioSearch);
			int radioButtonID = radioGroup.getCheckedRadioButtonId();
			RadioButton rad = (RadioButton) findViewById(radioButtonID);
			String type = rad.getText().toString();

			adapterItems = adapterTemp;
			itemsList.setAdapter(adapterItems);

			finish();
		}
	}

}
