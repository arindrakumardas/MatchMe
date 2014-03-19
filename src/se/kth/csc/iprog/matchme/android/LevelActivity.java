package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LevelActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.levels_view);

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
		level1_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				Intent i = new Intent(LevelActivity.this, GameActivity.class);
				i.putExtra("level_value", "1");	
				startActivity(i);
			}
		}); 
		
		// Level 2 Button
		Button level2_button = (Button) findViewById(R.id.level2_button);
		level2_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				Intent i = new Intent(LevelActivity.this, GameActivity.class);
				i.putExtra("level_value", "2");	
				startActivity(i);
			}
		}); 
		
		// Level 3 Button
		Button level3_button = (Button) findViewById(R.id.level3_button);
		level3_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				Intent i = new Intent(LevelActivity.this, GameActivity.class);
				i.putExtra("level_value", "3");		
				startActivity(i);
			}
		}); 
		
		// Level 4 Button
		Button level4_button = (Button) findViewById(R.id.level4_button);
		level4_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				Intent i = new Intent(LevelActivity.this, GameActivity.class);
				i.putExtra("level_value", "4");		
				startActivity(i);
			}
		}); 
		
		// Level 5 Button
		Button level5_button = (Button) findViewById(R.id.level5_button);
		level5_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				Intent i = new Intent(LevelActivity.this, GameActivity.class);
				i.putExtra("level_value", "5");		
				startActivity(i);
			}
		}); 

	}

}