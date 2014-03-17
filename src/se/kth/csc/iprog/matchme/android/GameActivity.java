package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;



public class GameActivity extends Activity {
	
	private static long countDownInterval = 1000;
	long millisInFuture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_view);
		Intent intent = getIntent();
        

		//TODO: Add code for switching screens and starting a view and a controller.
		TextView levelName = (TextView) findViewById(R.id.level_value);
		ViewFlipper vf_drop = (ViewFlipper)findViewById(R.id.game_drop_view_include);
		ViewFlipper vf_drag = (ViewFlipper)findViewById(R.id.game_drag_view_include);

		// Receiving the Data
		String level = intent.getStringExtra("level_value");

		// Setting the Data
		levelName.setText(level);
		
		int vflevel = 0;

		try {
			vflevel = Integer.parseInt(level);
		} catch(NumberFormatException nfe) {
		  // Handle parse error.
		}
		
		//int vflevel = Integer.parseInt(level);
		vf_drop.setDisplayedChild(vflevel-1);
		vf_drag.setDisplayedChild(vflevel-1);

		//Load the correct level
		RelativeLayout game_drop_view_include = (RelativeLayout) vf_drop.getChildAt(vf_drop.getDisplayedChild());
		RelativeLayout game_drag_view_include = (RelativeLayout) vf_drag.getChildAt(vf_drag.getDisplayedChild());
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
		
		// Implements CountdownTimer
		millisInFuture = intent.getIntExtra("resumeTime", 30000);
        final CountDownTimer cdt = new CountDownTimer(millisInFuture, countDownInterval) {
        	TextView timeLeft = (TextView) findViewById(R.id.time_left_value);

            public void onTick(long millisUntilFinished) {
            	timeLeft.setText("" + millisUntilFinished / 1000);
            	millisInFuture = millisUntilFinished;
            }

            public void onFinish() {
            	// // Display screen after finishing a level
            	Intent i = new Intent(GameActivity.this, EndActivity.class);
            	i.putExtra("resumeTime", 30000);
  				startActivity(i);
            }
         }.start();
         
         // Pause Button
         Button pausebtn = (Button) findViewById(R.id.pause_btn);
         pausebtn.setOnClickListener(new OnClickListener() {
      			@Override
      			public void onClick(View arg0) {
      				cdt.cancel();
      				
    				// Show paused screen options
      				Intent i = new Intent(GameActivity.this, PauseActivity.class);
      				i.putExtra("resumeTime", (int) (millisInFuture));
      				startActivity(i);
      				
      				finish(); // finish current activity
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
   
}
