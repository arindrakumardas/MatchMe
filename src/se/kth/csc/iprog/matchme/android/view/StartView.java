package se.kth.csc.iprog.matchme.android.view;

import se.kth.csc.iprog.matchme.android.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartView {

	View view;
	Button startButton;

	public StartView(View view) {

		// store in the class the reference to the Android View
		this.view = view;

//		TextView example = (TextView) view.findViewById(R.id.example_text);
//		example.setText("Hello world");

		// Setup the rest of the view layout
    	//Button in activity_main view
    	startButton = (Button) view.findViewById(R.id.start_game);
    	startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//TODO: Assign this to move to the next view!
				//Intent i = new Intent(MainActivity.this, MenuActivity.class);
	    		//startActivity(i);
			}
    	});
	}

}
