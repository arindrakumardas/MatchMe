package se.kth.csc.iprog.matchme.android;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class PauseActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pause_dialog_view);
        
        //TODO: Add code for switching screens and starting a view and a controller.
 
        Button refreshbtn = (Button) findViewById(R.id.restart_btn);
        refreshbtn.setOnClickListener(new OnClickListener() {
      			@Override
      			public void onClick(View arg0) {
      				//Move to the next view!
      				Intent i = new Intent(PauseActivity.this, LevelActivity.class);
      				startActivity(i);
      			}
      		});  
        
        Button backbtn = (Button) findViewById(R.id.back_btn);
        backbtn.setOnClickListener(new OnClickListener() {
      			@Override
      			public void onClick(View arg0) {
      				//Move to the next view!
      				Intent i = new Intent(PauseActivity.this, StartActivity.class);
      				startActivity(i);
      			}
      		});  
        
        Button playbtn = (Button) findViewById(R.id.play_btn);
        playbtn.setOnClickListener(new OnClickListener() {
      			@Override
      			public void onClick(View arg0) {
      				//Move to the next view!
      				finish();
      			}
      		});  
        
    }
 
}