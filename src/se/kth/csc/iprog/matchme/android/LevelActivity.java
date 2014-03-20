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

		createButton(1, R.id.level1_button);
		createButton(2, R.id.level2_button);
		createButton(3, R.id.level3_button);
		createButton(4, R.id.level4_button);
		createButton(5, R.id.level5_button);
	}
	
	public void createButton(int level, int id) {
		Button button = (Button) findViewById(id);
		button.setTag(level);
		if(level == 1 || model.getStatus(level-1) == true) {
			button.setOnClickListener(this);
		} else {
			
		}
		switch(level) {
		case 1:
			button.setOnClickListener(this);
			break;
		case 2:
			if(model.getStatus(level-1) == true) {
				button.setOnClickListener(this);
				button.setBackground(getResources().getDrawable( R.drawable.level2 ));
			} else { 
				button.setBackground(getResources().getDrawable( R.drawable.locked_level)); 
			}
			break;
		case 3:
			if(model.getStatus(level-1) == true) {
				button.setOnClickListener(this);
				button.setBackground(getResources().getDrawable( R.drawable.level3 ));
			} else { 
				button.setBackground(getResources().getDrawable( R.drawable.locked_level)); 
			}
			break;
		case 4:
			if(model.getStatus(level-1) == true) {
				button.setOnClickListener(this);
				button.setBackground(getResources().getDrawable( R.drawable.level4 ));
			} else { 
				button.setBackground(getResources().getDrawable( R.drawable.locked_level)); 
			}
			break;
		case 5:
			if(model.getStatus(level-1) == true) {
				button.setOnClickListener(this);
				button.setBackground(getResources().getDrawable( R.drawable.level5 ));
			} else { 
				button.setBackground(getResources().getDrawable( R.drawable.locked_level)); 
			}
			break;
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
