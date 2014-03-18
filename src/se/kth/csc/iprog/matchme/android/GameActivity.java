package se.kth.csc.iprog.matchme.android;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Collections;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Rect;
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

import se.kth.csc.iprog.matchme.model.MatchModel;
import se.kth.csc.iprog.matchme.model.MatchItem;


public class GameActivity extends Activity {
	
	private static long countDownInterval = 1000;
	long millisInFuture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_view);
		Intent intent = getIntent();
        
        MatchModel model = ((MatchMeApplication) this.getApplication()).getModel();
        

		//TODO: Add code for switching screens and starting a view and a controller.
		TextView levelName = (TextView) findViewById(R.id.level_value);
		ViewFlipper vf_drop = (ViewFlipper)findViewById(R.id.game_drop_view_include);
		ViewFlipper vf_drag = (ViewFlipper)findViewById(R.id.game_drag_view_include);

		// Receiving the Data
		final String level = intent.getStringExtra("level_value");

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
		
		
		MatchItem[] images = model.getRandomMatchItems(vflevel);
		

		//Load the correct level
		RelativeLayout game_drop_view_include = (RelativeLayout) vf_drop.getChildAt(vf_drop.getDisplayedChild());
		RelativeLayout game_drag_view_include = (RelativeLayout) vf_drag.getChildAt(vf_drag.getDisplayedChild());
		System.err.println("CHILDCOUNT DROP: " + game_drop_view_include.getChildCount());
		System.err.println("CHILDCOUNT DRAG: " + game_drag_view_include.getChildCount());

		Random rand = new Random();
		boolean usedImages[] = new boolean[images.length];
		for(boolean b : usedImages) {
			b = false;
		}
		for(int i = 0; i < game_drop_view_include.getChildCount(); i++) {
			ImageView current = (ImageView)game_drop_view_include.getChildAt(i);
		
			//Get first (X) imageSets here 
				MatchItem matchItem = images[i];

				//Set image to view
				int imgResId = MatchMeApplication.getImageResId(this, matchItem.getImgShadow());
				current.setImageResource(imgResId);
				current.setTag(matchItem.getImgReal()); //So we can identify them later and check tags for matches.
				
			
			current.setOnDragListener(new MyDragListener());
		}
		
		//Shuffle the list (only the real images, shadow doesn't need)
		List<MatchItem> itemList = new ArrayList<MatchItem>();
		for(MatchItem item : images) {
			itemList.add(item);
		}
		Collections.shuffle(itemList); //Maybe skip, it's already random.
		Iterator<MatchItem> imgIt2 = itemList.iterator();
		for(int i = 0; i < game_drag_view_include.getChildCount(); i++) {
			ImageView current = (ImageView)game_drag_view_include.getChildAt(i);
			
			//Get all and set images
			if(imgIt2.hasNext()){
				MatchItem matchItem = (MatchItem)imgIt2.next();

				//Set image to view
				int imgResId = MatchMeApplication.getImageResId(this, matchItem.getImgReal());
				current.setImageResource(imgResId);
				current.setTag(matchItem.getImgReal()); //So we can identify them later and check tags for matches.
			}
			
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
            	i.putExtra("level_value", level);
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
      				i.putExtra("level_value", level);
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
					dropped.setImageResource(MatchMeApplication.getImageResId(view.getContext(), dragged.getTag().toString()));
//					dropped.setBackground(dragged.getDrawable());
					v.setTag(true); //symbolizes that the image is matched.
					if(isWin()) {
						//You win. Go to endActivity to show this.
					}
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

		private boolean isWin() {
			ViewFlipper vf = ((ViewFlipper) findViewById(R.id.game_drop_view_include));
			//System.err.println("VIEWFLIPPER: " + vf);
			
			RelativeLayout layout = (RelativeLayout) vf.getChildAt(vf.getDisplayedChild());
			for(int i = 0; i < layout.getChildCount(); i++) {
				if(layout.getChildAt(i).getTag().equals(false)) return false;
			}
			return true;
		}
	}
   
}
