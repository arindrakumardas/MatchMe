package se.kth.csc.iprog.matchme.android;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class GameActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        
        //TODO: Add code for switching screens and starting a view and a controller.
 
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