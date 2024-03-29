package edu.gatech.cs2340.wheresmystuff;

import java.util.ArrayList;
import java.util.Calendar;

//import edu.gatech.cs2340.wheresmystuff.SearchActivity.SearchItemClickListener;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("unused")
/**
 * 
 * @author Bongsu Kim
 *this is list of lost items.
 *
 */
public class ListActivity extends Activity {
	private Context context;
	private MyAdapter adapter;
	private ListView listViewItems;
	private EditText search;
	private Button button;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button addButton;
	private RadioGroup radioSearchGroup;
	private RadioGroup radioStatusGroup;
	private Spinner spinner;
	private DatePicker datepicker;
	private Button dialogButton;
	private int year;
	private int month;
	private int day;
	private Database db;
	private UserVerifier uv;

	@Override
	/**
	 * link list view items to adapter and onitemlicklistener
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		context = getApplicationContext();
		db = new Database(context);
		uv = (UserVerifier) this.getIntent().getSerializableExtra("VERIFIER");

		ArrayList<Item> items = db.getAllItems();
		adapter = new MyAdapter(this.getApplicationContext());
		listViewItems = (ListView) findViewById(R.id.listview);

		adapter.addAllItems(items);

		listViewItems.setAdapter(adapter);
		listViewItems
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					/**
					 * bring out each position of items
					 */
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						Item item = (Item) listViewItems
								.getItemAtPosition(position);
					}
				});
		// Item item = (Item) this.getIntent().getSerializableExtra("ITEM");
		// if (item != null)

		this.button = (Button) this.findViewById(R.id.liststuff_logout);
		this.button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ListActivity.this, LoginActivity.class);
		//		intent.putExtra("VERIFIER", uv);
				startActivity(intent);
			}
		});
		this.button = (Button) this.findViewById(R.id.liststuff_addItem);
		this.button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ListActivity.this, HomeActivity.class);
			//	intent.putExtra("VERIFIER", uv);
				startActivity(intent);
			}
		});
		this.button2 = (Button) this.findViewById(R.id.liststuff_match);
		this.button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setClass(ListActivity.this, MatchActivity.class);
		//		intent.putExtra("VERIFIER", uv);
				startActivity(intent1);
			}
		});
		this.button3 = (Button) this.findViewById(R.id.liststuff_search);
		this.button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent();
				intent2.setClass(ListActivity.this, SearchActivity.class);
		//		intent.putExtra("VERIFIER", uv);
				startActivity(intent2);
			}
		});
		this.button1 = (Button) this.findViewById(R.id.liststuff_filter);
		this.button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.filter_dialog);
				dialog.setTitle("Filter");

				// set the custom dialog components - text, image and button
				EditText search = (EditText) dialog
						.findViewById(R.id.searchText);

				radioSearchGroup = (RadioGroup) dialog
						.findViewById(R.id.radioSearch);
				
//				Button searchButton = (Button) dialog
//						.findViewById(R.id.btnSearchForItems);
//				// if button is clicked, close the custom dialog
//				dialogButton.setOnClickListener(new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						dialog.dismiss();
//					}
//				});
				
				TextView text1 = (TextView) dialog.findViewById(R.id.text1);
				text1.setText("Status");

				radioStatusGroup = (RadioGroup) dialog
						.findViewById(R.id.radioStatus);

				TextView text2 = (TextView) dialog.findViewById(R.id.text2);
				text2.setText("Category");

				spinner = (Spinner) dialog.findViewById(R.id.spinner);

				TextView text3 = (TextView) dialog.findViewById(R.id.text3);
				text3.setText("Date");

				datepicker = (DatePicker) dialog.findViewById(R.id.datepicker);
				final Calendar c = Calendar.getInstance();
				year = c.get(Calendar.YEAR);
				month = c.get(Calendar.MONTH);
				day = c.get(Calendar.DAY_OF_MONTH);
				datepicker.init(year, month, day, null);

				Button dialogButton = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}

/**
 * 
 * @author Bongsu adapter work for putting items in the list
 */
class MyAdapter extends BaseAdapter {
	private ArrayList<Item> items;
	private TextView tvName, tvCategory, tvStatus, tvDate;
	private LayoutInflater inflater;
	private Context context;

	public MyAdapter(Context context) {
		inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		items = new ArrayList<Item>();
		this.context = context;
	}

	/**
	 * add lost items
	 * 
	 * @param item
	 *            is lost item that user will add on
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	public void addAllItems(ArrayList<Item> inputList) {
		for (Item cur : inputList) {
			items.add(cur);
		}
	}

	/**
	 * count items
	 */
	@Override
	public int getCount() {
		if (items == null)
			return 0;
		else
			return items.size();
	}

	/**
	 * bring item in each position
	 */
	@Override
	public Item getItem(int position) {

		return items.get(position);
	}

	/**
	 * bring item id in each position
	 */
	@Override
	public long getItemId(int position) {

		return position;
	}

	/**
	 * view list of lost items
	 * 
	 * @author Bongsu added name, status, category, and date to list view
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.lostitem, null);
			tvName = (TextView) convertView.findViewById(R.id.tv_name);
			tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
			tvCategory = (TextView) convertView.findViewById(R.id.tv_category);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);

			Item item = (Item) getItem(position);
			tvName.setText(item.getName());
			tvStatus.setText(item.getStatus().toString());
			tvCategory.setText(item.getCategory().toString());
			tvDate.setText (item.getDate().toString());

		}
		return convertView;
	}

}