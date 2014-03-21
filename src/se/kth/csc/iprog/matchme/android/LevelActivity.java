package se.kth.csc.iprog.matchme.android;


import java.util.Observable;
import java.util.Observer;

import se.kth.csc.iprog.matchme.model.MatchModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Shows all of the levels of the game, and keeps track of which of the levels are locked
 * and unlocked by checking their status in the model. The locked levels will be symbolized by 
 * an image of a lock instead of a level number.
 */
public class LevelActivity extends Activity implements OnClickListener, Observer {

	private MatchModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levels_view);

		model = ((MatchMeApplication) getApplication()).getModel();
		model.addObserver(this);
		// Home button
		Button backbtn = (Button) findViewById(R.id.back_btn);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish(); //Kill this activity and go to the parent, which is the StartActivity.
			}
		}); 

		update(null, MatchModel.STATUS); //Initiate the buttons
	}
	
	/**
	 * Creates the UI for the specified button depending on if the level is locked or not.
	 * @param level The level
	 * @param id The id of the button
	 */
	public void toggleUi(int level, int id) {
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
				button.setBackgroundDrawable(getResources().getDrawable( R.drawable.level2 ));
			} else { 
				button.setBackgroundDrawable(getResources().getDrawable( R.drawable.locked_level)); 
			}
			break;
		case 3:
			if(model.getStatus(level-1) == true) {
				button.setOnClickListener(this);
				button.setBackgroundDrawable(getResources().getDrawable( R.drawable.level3 ));
			} else { 
				button.setBackgroundDrawable(getResources().getDrawable( R.drawable.locked_level)); 
			}
			break;
		case 4:
			if(model.getStatus(level-1) == true) {
				button.setOnClickListener(this);
				button.setBackgroundDrawable(getResources().getDrawable( R.drawable.level4 ));
			} else { 
				button.setBackgroundDrawable(getResources().getDrawable( R.drawable.locked_level)); 
			}
			break;
		case 5:
			if(model.getStatus(level-1) == true) {
				button.setOnClickListener(this);
				button.setBackgroundDrawable(getResources().getDrawable( R.drawable.level5 ));
			} else { 
				button.setBackgroundDrawable(getResources().getDrawable( R.drawable.locked_level)); 
			}
			break;
		}
	}

	/**
	 * Called when a button is clicked. Will launch the corresponding level to the gameActivity.
	 */
	public void onClick(View view) {
		// add clicked level to database
		int level = (Integer) view.getTag();

		Intent game = new Intent(LevelActivity.this, GameActivity.class);
		model.setCurrentLevel(level);
		startActivity(game);
	}

	@Override
	public void update(Observable arg0, Object data) {
		if(data.equals(MatchModel.STATUS)){
			toggleUi(1, R.id.level1_button);
			toggleUi(2, R.id.level2_button);
			toggleUi(3, R.id.level3_button);
			toggleUi(4, R.id.level4_button);
			toggleUi(5, R.id.level5_button);
		}
	}

}
