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
 * Author: Xinlu Wang
 */

public class HomeActivity extends Activity {

	// private DatabaseConnector DB;
	private Button lostButton, adminButton, logoutButton, button1, findButton;
	private UserVerifier uv;
	private User loggedInUser;

	@Override
	/**
	 * * @param savedInstanceState bundle to be loaded into the app
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		uv = new UserVerifier(this);
		loggedInUser = (User)this.getIntent().getSerializableExtra("LOGGED_IN_USER");
		
		/**
		 * Set the intent between HomeActivity and LostActivity. The user could
		 * be able to input their lost stuff information after they confirm that
		 * they have lost something
		 */
		this.lostButton = (Button) this.findViewById(R.id.btnHome);
		this.lostButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, LostActivity.class);
				//intent.putExtra("VERIFIER", uv);
				intent.putExtra("LOGGED_IN_USER", loggedInUser);
				startActivity(intent);
			}
		});
		/**
		 * Set the intent between HomeActivity and FoundActivity. The user could
		 * be able to input their lost stuff information after they confirm that
		 * they have found something
		 */
	
		this.findButton = (Button) this.findViewById(R.id.btnHome3);
		this.findButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, FoundActivity.class);
				//intent.putExtra("VERIFIER", uv);
				intent.putExtra("LOGGED_IN_USER", loggedInUser);
				startActivity(intent);
			}
		});
		
		/**
		 * Set the intent between HomeActivity and ListActivity. The user could
		 * be able to see the current list of items 
		 */
	
		findViewById(R.id.button_list).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						Intent intent = new Intent();
						intent.setClass(HomeActivity.this, ListActivity.class);
						//intent.putExtra("VERIFIER", uv);
						intent.putExtra("LOGGED_IN_USER", loggedInUser);
						startActivity(intent);
					}
				});
		/**
		 * Set the intent between HomeActivity and AdminActivity.
		 * 
		 * 
		 */
		this.adminButton = (Button) this.findViewById(R.id.adminButton);
		this.adminButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (loggedInUser.isAdmin()) {
					Intent intent = new Intent();
					intent.setClass(HomeActivity.this, AdminActivity.class);
					//intent.putExtra("VERIFIER", uv);
					intent.putExtra("LOGGED_IN_USER", loggedInUser);
					startActivity(intent);
				}
			}
		});

		/**
		 * Set the intent between HomeActivity and LoginActivity.
		 * 
		 * 
		 */
		this.logoutButton = (Button) this.findViewById(R.id.logOutButton);
		this.logoutButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Needs changing so that this will account for more than just
				// the first user account.
				Intent intent = new Intent();
				intent.setClass(HomeActivity.this, LoginActivity.class);
				//intent.putExtra("VERIFIER", uv);
				//intent.putExtra("LOGGED_IN_USER", loggedInUser);
				startActivity(intent);
			}
		});

	}
}
