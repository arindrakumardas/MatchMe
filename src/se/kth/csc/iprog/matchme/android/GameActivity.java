package se.kth.csc.iprog.matchme.android;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class GameActivity extends Activity {
 
	protected static final int REQUEST_CODE = 1;
	long millisInFuture = 30000;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        
        //TODO: Add code for switching screens and starting a view and a controller.
 
        TextView txtName = (TextView) findViewById(R.id.level_value);
        
        Intent i = getIntent();
        // Receiving the Data
        String level = i.getStringExtra("level_value");
        txtName.setText(level);
        
        // implements CountdownTimer
        
        final CountDownTimer cdt = new CountDownTimer(millisInFuture, 1000) {
        	TextView timeLeft = (TextView) findViewById(R.id.time_left_value);

            public void onTick(long millisUntilFinished) {
            	timeLeft.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
            	// TODO: set intent to next screen
            	Intent i = new Intent(GameActivity.this, PauseActivity.class);
  				startActivity(i);
            }
         }.start();
         
         Button pausebtn = (Button) findViewById(R.id.pause_btn);
         pausebtn.setOnClickListener(new OnClickListener() {
      			@Override
      			public void onClick(View arg0) {
      				cdt.cancel();
      				String timeLeft = getText(R.id.time_left_value).toString();
      				/* THIS CAUSES THE CRASH: */
      		        int resumeTime = Integer.valueOf(timeLeft);

      		        //Move to the next view!
      				Intent i = new Intent(GameActivity.this, PauseActivity.class);
      				i.putExtra("resumeTime", resumeTime);
      				startActivityForResult(i, REQUEST_CODE);
      			}
      		});  
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == REQUEST_CODE && resultCode == PauseActivity.RESULT_OK) {
    		String savedTime = data.getExtras().getString("resumeTime");
    		millisInFuture = Long.valueOf(savedTime);
    	}
   	}
}