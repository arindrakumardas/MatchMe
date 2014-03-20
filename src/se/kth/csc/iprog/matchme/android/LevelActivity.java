package se.kth.csc.iprog.matchme.android;

<<<<<<< HEAD
import se.kth.csc.iprog.matchme.model.Level;
=======
import se.kth.csc.iprog.matchme.model.LevelsDataSource;
>>>>>>> origin/DiffLevel
import se.kth.csc.iprog.matchme.model.MatchModel;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LevelActivity extends Activity implements OnClickListener {

	private LevelsDataSource datasource;
	private MatchModel model;

	private Level model_level;
	private MatchModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levels_view);

<<<<<<< HEAD
		model = ((MatchMeApplication) this.getApplication()).getModel();
		model_level = ((MatchMeApplication) this.getApplication()).getLevel();
=======
		model = ((MatchMeApplication) getApplication()).getModel();
		datasource = new LevelsDataSource(this);
		datasource.open();
>>>>>>> origin/DiffLevel


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
<<<<<<< HEAD
		level1_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(LevelActivity.this, GameActivity.class);
				i.putExtra("level_value", "1");
				model.setCurrentLevel(1);
				startActivity(i);
			}
		}); 

		// Level 2 Button

		if (model.getCurrentLevel() > 1 && model_level.getStatus() ==false)
		{
			final Button level2_button = (Button) findViewById(R.id.level2_button);
			Drawable locked = getResources().getDrawable( R.drawable.level2	);
			level2_button.setBackground(locked);	
			level2_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					Intent i = new Intent(LevelActivity.this, GameActivity.class);
					i.putExtra("level_value", "2");	
					model.setCurrentLevel(2);
					startActivity(i);
				}
			}); 
		}
		else {

			final Button level2_button_a = (Button) findViewById(R.id.level2_button);
			Drawable locked1 = getResources().getDrawable( R.drawable.locked_level);
			level2_button_a.setBackground(locked1);
			level2_button_a.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					System.err.println("HELLO");

				}

			}); 

		}


		
		if (model.getCurrentLevel() > 2 && model_level.getStatus() ==false)
		{
			final Button level3_button = (Button) findViewById(R.id.level3_button);
			Drawable locked = getResources().getDrawable( R.drawable.level3	);
			level3_button.setBackground(locked);	
			level3_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					Intent i = new Intent(LevelActivity.this, GameActivity.class);
					i.putExtra("level_value", "3");	
					model.setCurrentLevel(3);
					startActivity(i);
				}
			}); 
		}
		else {

			final Button level3_button_a = (Button) findViewById(R.id.level3_button);
			Drawable locked1 = getResources().getDrawable( R.drawable.locked_level);
			level3_button_a.setBackground(locked1);
			level3_button_a.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					System.err.println("HELLO");

				}

			}); 

		}
		
		
		if (model.getCurrentLevel() >3 && model_level.getStatus() ==false)
		{
			final Button level4_button = (Button) findViewById(R.id.level4_button);
			Drawable locked = getResources().getDrawable( R.drawable.level4	);
			level4_button.setBackground(locked);	
			level4_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					Intent i = new Intent(LevelActivity.this, GameActivity.class);
					i.putExtra("level_value", "4");	
					model.setCurrentLevel(4);
					startActivity(i);
				}
			}); 
		}
		else {

			final Button level4_button_a = (Button) findViewById(R.id.level4_button);
			Drawable locked1 = getResources().getDrawable( R.drawable.locked_level);
			level4_button_a.setBackground(locked1);
			level4_button_a.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					System.err.println("HELLO");

				}

			}); 

		}
		
		
		
		if (model.getCurrentLevel() > 4 && model_level.getStatus() ==false)
		{
			final Button level5_button = (Button) findViewById(R.id.level5_button);
			Drawable locked = getResources().getDrawable( R.drawable.level5	);
			level5_button.setBackground(locked);	
			level5_button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					Intent i = new Intent(LevelActivity.this, GameActivity.class);
					i.putExtra("level_value", "5");	
					model.setCurrentLevel(5);
					startActivity(i);
				}
			}); 
		}
		else {

			final Button level5_button_a = (Button) findViewById(R.id.level5_button);
			Drawable locked1 = getResources().getDrawable( R.drawable.locked_level);
			level5_button_a.setBackground(locked1);
			level5_button_a.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					System.err.println("HELLO");

				}

			}); 

		}
		

//
//
//		// Level 3 Button
//		Button level3_button = (Button) findViewById(R.id.level3_button);
//		level3_button.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//
//				Intent i = new Intent(LevelActivity.this, GameActivity.class);
//				i.putExtra("level_value", "3");	
//				model.setCurrentLevel(3);	
//				startActivity(i);
//			}
//		}); 
//
//		// Level 4 Button
//		Button level4_button = (Button) findViewById(R.id.level4_button);
//		level4_button.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//
//				Intent i = new Intent(LevelActivity.this, GameActivity.class);
//				i.putExtra("level_value", "4");	
//				model.setCurrentLevel(4);	
//				startActivity(i);
//			}
//		}); 
//
//		// Level 5 Button
//		Button level5_button = (Button) findViewById(R.id.level5_button);
//		level5_button.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//
//				Intent i = new Intent(LevelActivity.this, GameActivity.class);
//				i.putExtra("level_value", "5");	
//				model.setCurrentLevel(5);	
//				startActivity(i);
//			}
//		}); 
=======
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
		// add clicked level to database
		int level = (Integer) view.getTag();
		datasource.loadLevel(level);

		Intent game = new Intent(LevelActivity.this, GameActivity.class);
		model.setCurrentLevel(level);
		startActivity(game);
>>>>>>> origin/DiffLevel

		finish(); // finish current activity
	}

}
