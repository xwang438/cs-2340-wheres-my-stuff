package edu.gatech.cs2340.wheresmystuff;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * The first page after launching the app. 
 * It is connected with the LoginActivity and the RegisterActivity
 * @author Xinlu
 *
 */

public class MainActivity extends Activity {  
	private Button button,button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
		this.button = (Button) this.findViewById(R.id.button_register);
		this.button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
		this.button1 = (Button) this.findViewById(R.id.sign_in_button);
		this.button1.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent();
				intent1.setClass(MainActivity.this,LoginActivity.class);
				startActivity(intent1);
			}
		});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    
}