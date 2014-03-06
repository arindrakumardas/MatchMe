package se.kth.csc.iprog.dinnerplanner.android;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	Button startButton;
	
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
        
    	//Button in activity_main view
    	startButton = (Button) this.findViewById(R.id.start_button);
    	startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainActivity.this, MenuActivity.class);
	    		startActivity(i);
			}
    	});
    	
    }

}
