package se.kth.csc.iprog.matchme.android;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;
 
public class GameActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        
        //TODO: Add code for switching screens and starting a view and a controller.
        
        
        
        TextView levelName = (TextView) findViewById(R.id.level_value);
        ViewFlipper vf_drop = (ViewFlipper)findViewById(R.id.game_drop_view_include);
        ViewFlipper vf_drag = (ViewFlipper)findViewById(R.id.game_drag_view_include);
        
        Intent i = getIntent();
        // Receiving the Data
        String level = i.getStringExtra("level_value");
        
        levelName.setText(level);
        int vflevel = Integer.parseInt(level);
        vf_drop.setDisplayedChild(vflevel-1);
        vf_drag.setDisplayedChild(vflevel-1);
        
        // implements CountdownTimer
        
        new CountDownTimer(30000, 1000) {
        	TextView timeLeft = (TextView) findViewById(R.id.time_left_value);

            public void onTick(long millisUntilFinished) {
            	timeLeft.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
            	// TODO: set intent to next screen
            	Intent i = new Intent(GameActivity.this, EndActivity.class);
  				startActivity(i);
            }
         }.start();

        
        Button pausebtn = (Button) findViewById(R.id.pause_btn);
        pausebtn.setOnClickListener(new OnClickListener() {
      			@Override
      			public void onClick(View arg0) {
      				//Move to the next view!
      				Intent i = new Intent(GameActivity.this, PauseActivity.class);
      				startActivity(i);
      			}
      		});  
        
    }
 
}