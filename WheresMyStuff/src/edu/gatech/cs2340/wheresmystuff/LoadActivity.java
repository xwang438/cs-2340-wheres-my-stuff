package edu.gatech.cs2340.wheresmystuff;
import android.app.Activity;  
import android.os.Bundle;  
import android.content.Intent;  
import android.graphics.PixelFormat;  
import android.os.Handler;   
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
	  
public class LoadActivity extends Activity {  
	      
	    //time for picture display  
		private static final int LOAD_DISPLAY_TIME = 1500;  
		      
		    /** Called when the activity is first created. */  
		    @Override  
		    public void onCreate(Bundle savedInstanceState) {  
		        super.onCreate(savedInstanceState);  
		          
		        getWindow().setFormat(PixelFormat.RGBA_8888);    
		        setContentView(R.layout.load);  
 
		        new Handler().postDelayed(new Runnable() {  
		            public void run() {  
		                //Go to main activity, and finish load activity  
		                Intent mainIntent = new Intent(LoadActivity.this, MainActivity.class);  
		                LoadActivity.this.startActivity(mainIntent);  
		                LoadActivity.this.finish();  
		            }  
		    }, LOAD_DISPLAY_TIME);   
        }  
} 	    
