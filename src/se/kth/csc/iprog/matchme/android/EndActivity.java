package se.kth.csc.iprog.matchme.android;

import se.kth.csc.iprog.matchme.android.controller.EndController;
import se.kth.csc.iprog.matchme.android.view.EndView;
import se.kth.csc.iprog.matchme.model.Level;
import se.kth.csc.iprog.matchme.model.MatchModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
				//				Intent i = new Intent(EndActivity.this, StartActivity.class);
				//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //Clear the activity stack and start from the StartActivity.
				//				startActivity(i);
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
				//				Intent i = new Intent(EndActivity.this, StartActivity.class);
				//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //Clear the activity stack and start from the StartActivity.
				//				startActivity(i);
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
		Button nextBtn = (Button) findViewById(R.id.next_button);
		nextBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (model.getCurrentLevelStatus() == true)
				{
					//Move to the next level
					model.setCurrentLevel(model.getCurrentLevel()+1);
				} 
				//Move to the next view!
				Intent i = new Intent(EndActivity.this, GameActivity.class);
				startActivity(i);
				finish(); // finish current activity 	
			}
		});
	}
}
