package se.kth.csc.iprog.matchme.android;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	
	Handler handler = new Handler();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
    	super.onCreate(savedInstanceState);
    	
    	// Set the view for the main activity screen
    	// it must come before any call to findViewById method
        setContentView(R.layout.activity_main);
        
    	// Creating the view class instance
    	//ExampleView mainView = new ExampleView(findViewById(R.id.this_is_example_view_id));

        // From the Dinner package
        // setContentView(R.layout.activity_main);
        

    }

}
