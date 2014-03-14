package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
<<<<<<< HEAD

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_view);

		//TODO: Add code for switching screens and starting a view and a controller.

		TextView levelName = (TextView) findViewById(R.id.level_value);

		Intent intent = getIntent();
		// Receiving the Data
		String level = intent.getStringExtra("level_value");
		String game_drop = intent.getStringExtra("game_drop_view_include");
		
		//Load the correct level
		RelativeLayout game_drop_view_include = (RelativeLayout) findViewById(R.id.game_drop_view_include);
		RelativeLayout game_drag_view_include = (RelativeLayout) findViewById(R.id.game_drag_view_include);
		System.err.println("CHILDCOUNT DROP: " + game_drop_view_include.getChildCount());
		for(int i = 0; i < game_drop_view_include.getChildCount(); i++) {
			View current = game_drop_view_include.getChildAt(i);
			current.setOnDragListener(new MyDragListener());
		}
		System.err.println("CHILDCOUNT DRAG: " + game_drag_view_include.getChildCount());
		for(int i = 0; i < game_drag_view_include.getChildCount(); i++) {
			View current = game_drag_view_include.getChildAt(i);
			current.setOnTouchListener(new MyTouchListener());
		}

		levelName.setText(level);
		game_drop_view_include.setTag(game_drop);

		// implements CountdownTimer
		CountDownTimer cdt = new CountDownTimer(30000, 1000) {
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
				//Move to the next view!
				Intent i = new Intent(GameActivity.this, PauseActivity.class);
				startActivity(i);
			}
		});  

	}

	private final class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				view.startDrag(data, shadowBuilder, view, 0);
				view.setVisibility(View.INVISIBLE);
				return true;
			} else {
				return false;
			}
		}
	}

	private final class MyDragListener implements OnDragListener {
		//	    Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		//	    Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		@Override
		public boolean onDrag(View v, DragEvent event) {
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// do nothing
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				//do nothing
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				//do nothing
				break;
			case DragEvent.ACTION_DROP:
				// Dropped, reassign View to ViewGroup
				//Get dragging view:
				View view = (View) event.getLocalState();
				//v is the view that we dropped on.
				
				//System.err.println("v.getTag(): " + v.getTag() + ", view.getTag(): " + view.getTag());
				if(v.getTag() != null && v.getTag().equals(view.getTag())) {
					view.setVisibility(View.INVISIBLE);
					ImageView dragged = (ImageView) view;
					ImageView dropped = (ImageView) v;
					dropped.setBackground(dragged.getDrawable());
					v.setTag(true); //symbolizes that the image is matched.
				} else {
					view.setVisibility(View.VISIBLE);
					return false;
				}
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				if(event.getResult() != true) {
					v.setVisibility(View.VISIBLE);

					view = (View) event.getLocalState();
					view.setVisibility(View.VISIBLE);
				} //Else it was successful, so keep the view invisible!
			default:
				break;
			}
			return true;
		}
	}

=======
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
            	Intent i = new Intent(GameActivity.this, PauseActivity.class);
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
 
>>>>>>> level-branch
}