package edu.gatech.cs2340.wheresmystuff;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * Activity which displays a screen to the user, the user can indicate they have
 * lost something.
 */

public class HomeActivity extends Activity {
   private Button button, buttonA;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
    /**
     * Set the intent between HomeActivity and LostActivity. The user could be able
     * to input their lost stuff information after they confirm that they have lost
     * something
     */
		this.button = (Button) this.findViewById(R.id.btnHome);
		this.button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this,LostActivity.class);
				startActivity(intent);
			}
		});
		
		/**
	     * Set the intent between HomeActivity and AdminActivity. 
	     * 
	     * 
	     */
			this.buttonA = (Button) this.findViewById(R.id.adminButton);
			this.buttonA.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(HomeActivity.this,AdminActivity.class);
					startActivity(intent);
				}
			});
	}
}