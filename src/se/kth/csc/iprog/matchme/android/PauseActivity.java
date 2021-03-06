package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This activity controls the screen of a paused game. At this point,
 * the user can either resume the game, restart the game, or go back to choose
 * a new level.
 */
public class PauseActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pause_dialog_view);

		// Receiving the time data
		Intent intent = getIntent();
		final String level = intent.getStringExtra("level_value");

		Intent returnIntent = new Intent();
		setResult(RESULT_OK, returnIntent); //As default.

		// Play (Resume) Button
		Button playBtn = (Button) findViewById(R.id.next_button);
		playBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Simply kill this activity to go back to the game.
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				
				finish(); // finish current activity
			}
		});

		// Restart Button
		Button restartBtn = (Button) findViewById(R.id.restart_btn);
		restartBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) { 
				Intent returnIntent = new Intent();
				setResult(RESULT_CANCELED, returnIntent);//Kill the previous game, and create a new game on the same level.
				Intent i = new Intent(PauseActivity.this, GameActivity.class);
				startActivity(i);
				finish(); // finish current activity 
			}
		});

		// Home Button
		Button backBtn = (Button) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(PauseActivity.this, StartActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //Clear the activity stack and start from the StartActivity.
				startActivity(i);
			}
		}); 
	} 
}