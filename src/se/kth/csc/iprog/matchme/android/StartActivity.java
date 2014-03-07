package se.kth.csc.iprog.matchme.android;

import se.kth.csc.iprog.matchme.android.view.StartView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class StartActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default call to load previous state
    	super.onCreate(savedInstanceState);
    	
    	// Set the view for the main activity screen
    	// it must come before any call to findViewById method
        setContentView(R.layout.start_view);
        
    	// Creating the view class instance
    	StartView startView = new StartView(findViewById(R.layout.start_view));
    	//TODO: Add a controller for the startView if necessary (maybe to control the volume mute)

    	//Buttons in start_view
    	Button startButton = (Button) findViewById(R.id.start_game);
    	startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//TODO: Assign this to move to the next view!
				//Intent i = new Intent(MainActivity.this, MenuActivity.class);
	    		//startActivity(i);
			}
    	});
    	Button highScoreBtn = (Button) findViewById(R.id.view_highscore);
    	highScoreBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//TODO: Assign this to move to the next view!
				//Intent i = new Intent(MainActivity.this, MenuActivity.class);
	    		//startActivity(i);
			}
    	});
    }

}
