package se.kth.csc.iprog.matchme.android;


import se.kth.csc.iprog.matchme.model.Level;
import se.kth.csc.iprog.matchme.model.LevelsDataSource;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levels_view);

		model = ((MatchMeApplication) getApplication()).getModel();
		datasource = new LevelsDataSource(this);
		datasource.open();
		model_level = ((MatchMeApplication) this.getApplication()).getLevel();


		// Home button
		Button backbtn = (Button) findViewById(R.id.back_btn);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish(); //Kill this activity and go to the parent, which is the StartActivity.
			}
		}); 
//
//		// Level 1 Button
//		Button level1_button = (Button) findViewById(R.id.level1_button);
//
//		level1_button.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//
//				Intent i = new Intent(LevelActivity.this, GameActivity.class);
//				i.putExtra("level_value", "1");
//				model.setCurrentLevel(1);
//				startActivity(i);
//			}
//		}); 
//
//		// Level 2 Button
//
//		if (model.getCurrentLevel() > 1 && model_level.getStatus() ==0)
//		{
//			final Button level2_button = (Button) findViewById(R.id.level2_button);
//			Drawable locked = getResources().getDrawable( R.drawable.level2	);
//			level2_button.setBackground(locked);	
//			level2_button.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//
//					Intent i = new Intent(LevelActivity.this, GameActivity.class);
//					i.putExtra("level_value", "2");	
//					model.setCurrentLevel(2);
//					startActivity(i);
//				}
//			}); 
//		}
//		else {
//
//			final Button level2_button_a = (Button) findViewById(R.id.level2_button);
//			Drawable locked1 = getResources().getDrawable( R.drawable.locked_level);
//			level2_button_a.setBackground(locked1);
//			level2_button_a.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					System.err.println("HELLO");
//
//				}
//
//			}); 
//
//		}
//
//
//		
//		if (model.getCurrentLevel() > 2 && model_level.getStatus() ==0)
//		{
//			final Button level3_button = (Button) findViewById(R.id.level3_button);
//			Drawable locked = getResources().getDrawable( R.drawable.level3	);
//			level3_button.setBackground(locked);	
//			level3_button.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//
//					Intent i = new Intent(LevelActivity.this, GameActivity.class);
//					i.putExtra("level_value", "3");	
//					model.setCurrentLevel(3);
//					startActivity(i);
//				}
//			}); 
//		}
//		else {
//
//			final Button level3_button_a = (Button) findViewById(R.id.level3_button);
//			Drawable locked1 = getResources().getDrawable( R.drawable.locked_level);
//			level3_button_a.setBackground(locked1);
//			level3_button_a.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					System.err.println("HELLO");
//
//				}
//
//			}); 
//
//		}
//		
//		
//		if (model.getCurrentLevel() >3 && model_level.getStatus() ==0)
//		{
//			final Button level4_button = (Button) findViewById(R.id.level4_button);
//			Drawable locked = getResources().getDrawable( R.drawable.level4	);
//			level4_button.setBackground(locked);	
//			level4_button.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//
//					Intent i = new Intent(LevelActivity.this, GameActivity.class);
//					i.putExtra("level_value", "4");	
//					model.setCurrentLevel(4);
//					startActivity(i);
//				}
//			}); 
//		}
//		else {
//
//			final Button level4_button_a = (Button) findViewById(R.id.level4_button);
//			Drawable locked1 = getResources().getDrawable( R.drawable.locked_level);
//			level4_button_a.setBackground(locked1);
//			level4_button_a.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					System.err.println("HELLO");
//
//				}
//
//			}); 
//
//		}
//		
//		
//		
//		if (model.getCurrentLevel() > 4 && model_level.getStatus() ==0)
//		{
//			final Button level5_button = (Button) findViewById(R.id.level5_button);
//			Drawable locked = getResources().getDrawable( R.drawable.level5	);
//			level5_button.setBackground(locked);	
//			level5_button.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//
//					Intent i = new Intent(LevelActivity.this, GameActivity.class);
//					i.putExtra("level_value", "5");	
//					model.setCurrentLevel(5);
//					startActivity(i);
//				}
//			}); 
//		}
//		else {
//
//			final Button level5_button_a = (Button) findViewById(R.id.level5_button);
//			Drawable locked1 = getResources().getDrawable( R.drawable.locked_level);
//			level5_button_a.setBackground(locked1);
//			level5_button_a.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					System.err.println("HELLO");
//
//				}
//
//			}); 
//
//		}

		createButton(1, R.id.level1_button);
		createButton(2, R.id.level2_button);
		createButton(3, R.id.level3_button);
		createButton(4, R.id.level4_button);
		createButton(5, R.id.level5_button);
	}
	
	public void createButton(int level, int id) {
		Button button = (Button) findViewById(R.id.level2_button);
		button.setTag(level);
		if(level == 1 || model.getStatus(level-1)) { 
			button.setOnClickListener(this);
		}
	}

	public void onClick(View view) {
		// add clicked level to database
		int level = (Integer) view.getTag();
		datasource.loadLevel(level);

		Intent game = new Intent(LevelActivity.this, GameActivity.class);
		model.setCurrentLevel(level);
		startActivity(game);

		finish(); // finish current activity
	}

}
