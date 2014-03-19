package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_end_view);
		final String lost = (getResources().getString(R.string.end_msg_lose));
		final String win = (getResources().getString(R.string.end_msg_won));
		Intent intent = getIntent();
		final String level = intent.getStringExtra("level_value");
		final int WinTime = intent.getIntExtra("Win_Time", 0);


		//TODO: Score algorithm
		int baseScoreValue = 100;
		TextView scorevalue = (TextView) findViewById(R.id.scorevalue);
		int score = baseScoreValue * WinTime ; 
		String strI = String.valueOf(score);
		scorevalue.setText(strI);
		if (score==0){
			TextView game_end_msg = (TextView)findViewById(R.id.game_end_msg);
			game_end_msg.setText(lost);
		}
		else{
			TextView game_end_msg = (TextView)findViewById(R.id.game_end_msg);
			game_end_msg.setText(win);
		}
		// Home Button
		Button backbtn = (Button) findViewById(R.id.back_btn);
		backbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Move to the next view!
				Intent i = new Intent(EndActivity.this, StartActivity.class);
				startActivity(i);
			}
		});  


		// Restart Button
		Button playbtn = (Button) findViewById(R.id.play_btn);
		playbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Move to the next view!
				Intent i = new Intent(EndActivity.this, GameActivity.class);
				i.putExtra("resumeTime", 30000);	// reset timer
				i.putExtra("level_value", level);
				startActivity(i);
				finish(); // finish current activity 
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
