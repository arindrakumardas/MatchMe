package se.kth.csc.iprog.matchme.android;

import se.kth.csc.iprog.matchme.model.LevelsDataSource;
import se.kth.csc.iprog.matchme.model.MatchModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LevelActivity extends Activity implements OnClickListener {

	private LevelsDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levels_view);
		
		final MatchModel model = ((MatchMeApplication) getApplication()).getModel();
		datasource = new LevelsDataSource(this);
		datasource.open();
		

		// Home button
		Button backbtn = (Button) findViewById(R.id.back_btn);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish(); //Kill this activity and go to the parent, which is the StartActivity.
			}
		}); 

		// Level 1 Button
		Button level1_button = (Button) findViewById(R.id.level1_button);
		level1_button.setTag(1);
		level1_button.setOnClickListener(this);

		// Level 2 Button
		Button level2_button = (Button) findViewById(R.id.level2_button);
		level2_button.setTag(2);
		level2_button.setOnClickListener(this);

		// Level 3 Button
		Button level3_button = (Button) findViewById(R.id.level3_button);
		level3_button.setTag(3);
		level3_button.setOnClickListener(this);

		// Level 4 Button
		Button level4_button = (Button) findViewById(R.id.level4_button);
		level4_button.setTag(4);
		level4_button.setOnClickListener(this);

		// Level 5 Button
		Button level5_button = (Button) findViewById(R.id.level5_button);
		level5_button.setTag(5);
		level5_button.setOnClickListener(this);
	}

		public void onClick(View view) {
			// TODO: initialize id  
			 
			// add clicked level to database
			datasource.loadLevel(id);
			
			Intent game = new Intent(LevelActivity.this, GameActivity.class);
			model.setCurrentLevel(id);
			startActivity(game);

			finish(); // finish current activity
		}

	}

}