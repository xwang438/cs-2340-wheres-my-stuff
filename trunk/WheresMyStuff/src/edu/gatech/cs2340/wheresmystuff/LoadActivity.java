package edu.gatech.cs2340.wheresmystuff;
import android.app.Activity;  
import android.os.Bundle;  
import android.content.Intent;  
import android.graphics.PixelFormat;  
import android.os.Handler;   

/**
 * The LoadActivity enable the app to show a tornado picture
 * when the user launch the app	  
 * @author Xinlu
 *
 */
public class LoadActivity extends Activity {  
	      
	    //time for picture display  
		private static final int LOAD_DISPLAY_TIME = 1500;  
		private UserVerifier uv;
		
		    /** Called when the activity is first created. */  
		    @Override  
		    public void onCreate(Bundle savedInstanceState) {  
		        super.onCreate(savedInstanceState);  
		          
		        getWindow().setFormat(PixelFormat.RGBA_8888);    
		        setContentView(R.layout.load);  
		        
		        uv = new UserVerifier(this);
		        
		        new Handler().postDelayed(new Runnable() {  
		            public void run() {  
		                //Go to main activity, and finish load activity  
		                Intent mainIntent = new Intent(LoadActivity.this, LoginActivity.class);  
		     //           mainIntent.putExtra("VERIFIER", uv);
		                LoadActivity.this.startActivity(mainIntent);  
		                LoadActivity.this.finish();  
		            }  
		    }, LOAD_DISPLAY_TIME);   
        }  
} 	    
