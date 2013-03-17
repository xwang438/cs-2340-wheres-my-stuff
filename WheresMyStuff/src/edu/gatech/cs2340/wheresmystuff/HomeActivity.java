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
	
	private DatabaseConnector DB = new DatabaseConnector(this);
	private Button button, buttonA, buttonL,button1;
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
					if(DB.isLoggedInAsAdmin()) {
						Intent intent = new Intent();
						intent.setClass(HomeActivity.this,AdminActivity.class);
						startActivity(intent);
					}
				}
			});
			this.button1 = (Button) this.findViewById(R.id.btnHome2);
			this.button1.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View v) {
					Intent intent1 = new Intent();
					intent1.setClass(HomeActivity.this,MainActivity.class);
					startActivity(intent1);
				}
			});
			
			
			
			/**
		     * Set the intent between HomeActivity and LoginActivity. 
		     * 
		     * 
		     */
				this.buttonL = (Button) this.findViewById(R.id.logOutButton);
				this.buttonL.setOnClickListener(new OnClickListener() 
				{
					@Override
					public void onClick(View v) {
						//Needs changing so that this will account for more than just the first user account.
						DB.logOutUser(0);
						Intent intent = new Intent();
						intent.setClass(HomeActivity.this,LoginActivity.class);
						startActivity(intent);
					}
				});
			
	}
}
