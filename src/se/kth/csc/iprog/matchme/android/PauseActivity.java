package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PauseActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pause_dialog_view);
		Intent intent = getIntent();
		final int resumeTime = intent.getIntExtra("resumeTime", 30000);
		
		Button playBtn = (Button) findViewById(R.id.play_btn);
		playBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Move to the next view!
				Intent i = new Intent(PauseActivity.this, GameActivity.class);

				i.putExtra("resumeTime", resumeTime);
				startActivity(i);
				finish(); // finish the current activity
			}
		});
		
		Button restartBtn = (Button) findViewById(R.id.restart_btn);
		restartBtn.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
    				//Move to the next view!
    				Intent i = new Intent(PauseActivity.this, GameActivity.class);
    				i.putExtra("resumeTime", 30000);
    				startActivity(i);
    				finish(); // finish the current activity 
    			}
    		});
		
		Button backBtn = (Button) findViewById(R.id.back_btn);
		backBtn.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
    				//Move to the next view!
    				Intent i = new Intent(PauseActivity.this, StartActivity.class);
    				startActivity(i);
    				finish(); // finish the current activity
    			}
    		}); 
	} 	
}