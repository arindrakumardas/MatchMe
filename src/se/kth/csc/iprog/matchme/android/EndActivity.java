package se.kth.csc.iprog.matchme.android;

import se.kth.csc.iprog.matchme.android.controller.EndController;
import se.kth.csc.iprog.matchme.android.view.EndView;
import se.kth.csc.iprog.matchme.model.MatchModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity for the screen that is displayed after a game is ended. 
 * Keeps track of the viewer and controller for the screen.
 * Shows different screens depending on if the user completed the level or not,
 * and if the highscore was beaten.
 */
public class EndActivity extends Activity {
	private MatchModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_end_view);

		model = ((MatchMeApplication) this.getApplication()).getModel();
		
		EndView endView = new EndView(findViewById(R.id.game_end_view), model);
		EndController endController = new EndController(endView, model);

		// Level Button
		Button winLevelBtn = (Button) findViewById(R.id.win_level_button);
		winLevelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish(); //Finish this activity to go back to the levels.
			}
		}); 

		// Restart Button
		Button winRestartBtn = (Button) findViewById(R.id.win_restart_btn);
		winRestartBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Start a new game on the same level
				Intent i = new Intent(EndActivity.this, GameActivity.class);
				startActivity(i);
				
				finish(); // finish current activity 	
			}
		});
		
		Button loseLevelBtn = (Button) findViewById(R.id.lose_level_button);
		loseLevelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish(); //Finish this activity to go back to the levels.
			}
		}); 

		// Restart Button
		Button loseRestartBtn = (Button) findViewById(R.id.lose_restart_btn);
		loseRestartBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Start a new game on the same level
				Intent i = new Intent(EndActivity.this, GameActivity.class);
				startActivity(i);
				
				finish(); // finish current activity 	
			}
		});

		// Next Button
		if (model.getCurrentLevel() < 5 && model.getCurrentLevelStatus() == true){
		Button nextBtn = (Button) findViewById(R.id.next_button);
		nextBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				//Intent i = new Intent();
//				if (model.getCurrentLevelStatus() == true)
//				{
					 
					//Move to the next level
					model.setCurrentLevel(model.getCurrentLevel()+1);
//					if (model.getCurrentLevel() != 5) {
//						model.setCurrentLevel(model.getCurrentLevel()+1);
//						i = new Intent(EndActivity.this, GameActivity.class);
//					} else {
//						i = new Intent(EndActivity.this, StartActivity.class);
//					}
//			} 
				//Move to the next view!
					Intent i = new Intent(EndActivity.this, GameActivity.class);
				startActivity(i);
				
				finish(); // finish current activity 	
			}
		});
	}
		
		else {
			Button nextBtn = (Button) findViewById(R.id.next_button);
			nextBtn.setVisibility(View.GONE);
		}}
		}
	

