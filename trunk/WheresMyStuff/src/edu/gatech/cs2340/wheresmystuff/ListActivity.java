package edu.gatech.cs2340.wheresmystuff;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 
 * @author Bongsu Kim
 *this is list of lost items.
 *
 */
public class ListActivity extends Activity {
	private MyAdapter adapter;
	private ListView lvitems;

	@Override
	/**
	 * link list view items to adapter and onitemlicklistener
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		adapter = new MyAdapter(this.getApplicationContext());
		lvitems = (ListView) findViewById(R.id.listview);
		lvitems.setAdapter(adapter);
		lvitems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			/**
			 * bring out each position of items
			 */
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				LostItem item = (LostItem) lvitems.getItemAtPosition(position);
			}
		});

		if(LostItem.item != null) adapter.addItem(LostItem.item);

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
	private ArrayList<LostItem> items;
	private TextView tvname, tvdescription;
	private LayoutInflater inflater;
	private Context context;

	public MyAdapter(Context context) {
		inflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		items = new ArrayList<LostItem>();
		this.context = context;
	}
/**
 * add lost items
 * @param item is lost item that user will add on
 */
	public void addItem(LostItem item) {
		items.add(item);
	}
/**
 * count items
 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (items == null)
			return 0;
		else
			return items.size();
	}
/**
 * bring item in each position
 */
	@Override
	public LostItem getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}
/**
 * bring item id in each position
 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
/**
 * view list of lost items
 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.lostitem, null);
			tvname = (TextView) convertView.findViewById(R.id.tv_name);

			LostItem item = (LostItem) getItem(position);
			tvname.setText(item.getName());

		}
		return convertView;
	}

}