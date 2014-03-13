package se.kth.csc.iprog.matchme.android;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class GameActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        
        //TODO: Add code for switching screens and starting a view and a controller.
 
        TextView levelName = (TextView) findViewById(R.id.level_value);
        View game_drop_view_include = (View) findViewById(R.id.game_drop_view_include);
        
        Intent i = getIntent();
        // Receiving the Data
        String level = i.getStringExtra("level_value");
        String game_drop = i.getStringExtra("game_drop_view_include");
        
        levelName.setText(level);
        game_drop_view_include.setTag(game_drop);
        
        
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