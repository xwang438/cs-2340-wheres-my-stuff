package edu.gatech.cs2340.wheresmystuff;

import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressWarnings("unused")
/**
 * 
 * @author Bongsu Kim
 *this is list of lost items.
 *
 */

public class ListActivity extends Activity {
	private MyAdapter adapter;
	private ListView listViewItems;
    private Button button;
	@Override
	/**
	 * link list view items to adapter and onitemlicklistener
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		adapter = new MyAdapter(this.getApplicationContext());
		listViewItems = (ListView) findViewById(R.id.listview);
		listViewItems.setAdapter(adapter);
		listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			/**
			 * bring out each position of items
			 */
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Item item = (Item) listViewItems.getItemAtPosition(position);
			}
		});

		if(Item.item != null) adapter.addItem(Item.item);
		this.button = (Button) this.findViewById(R.id.liststuff_logout);
		this.button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ListActivity.this,MainActivity.class);
				startActivity(intent);
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
 * @author Bongsu
 *adapter work for putting items in the list
 */
class MyAdapter extends BaseAdapter {
	private ArrayList<Item> items;
	private TextView tvName, tvCategory, tvStatus;
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
 * @param item is lost item that user will add on
 */
	public void addItem(Item item) {
		items.add(item);
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
 * @author Emily 
 * added status + category to list view
 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.lostitem, null);
			tvName = (TextView) convertView.findViewById(R.id.tv_name);
			tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
			tvCategory = (TextView) convertView.findViewById(R.id.tv_category);
			
			Item item = (Item) getItem(position);
			tvName.setText(item.getName());
			tvStatus.setText(item.getStatus());
			tvCategory.setText(item.getCategory().toString());
			

		}
		return convertView;
	}

}