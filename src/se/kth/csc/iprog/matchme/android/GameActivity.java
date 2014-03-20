package se.kth.csc.iprog.matchme.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import se.kth.csc.iprog.matchme.android.view.GameView;
import se.kth.csc.iprog.matchme.model.Level;
import se.kth.csc.iprog.matchme.model.MatchModel;
import se.kth.csc.iprog.matchme.model.MatchItem;


public class GameActivity extends Activity {

	private static long countDownInterval = 1000;
	long millisInFuture;
	int Win_Time = 0;
	private CountDownTimer cdt;
	private String level = "1"; //Default level.
	//private MediaPlayer earcon;
	Intent intent;
	private MatchModel model;
	private Level model_level;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_view);
		intent = getIntent();
		//earcon = MediaPlayer.create(this, R.raw.timesup);
//		try {
//			earcon.prepare();
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		model = ((MatchMeApplication) this.getApplication()).getModel();
		model.setTimeLeft(30000);
		model_level = ((MatchMeApplication) this.getApplication()).getLevel();
		
		
		GameView gameView = new GameView(findViewById(R.id.game_view), model);

//		//TODO: Add code for switching screens and starting a view and a controller.
//		TextView levelName = (TextView) findViewById(R.id.level_value);
		ViewFlipper vf_drop = (ViewFlipper)findViewById(R.id.game_drop_view_include);
		ViewFlipper vf_drag = (ViewFlipper)findViewById(R.id.game_drag_view_include);
//
//		// Receiving the Data
//		level = intent.getStringExtra("level_value");
//
//		// Setting the Data
//		levelName.setText(level);
//
//		int vflevel = 0;
//
//		try {
//			vflevel = Integer.parseInt(level);
//		} catch(NumberFormatException nfe) {
//			// Handle parse error.
//		}
//
//		//int vflevel = Integer.parseInt(level);
//		vf_drop.setDisplayedChild(vflevel-1);
//		vf_drag.setDisplayedChild(vflevel-1);


		MatchItem[] images = model.getRandomMatchItems(model.getCurrentLevel());

		

		//Load the correct level
		RelativeLayout game_drop_view_include = (RelativeLayout) vf_drop.getChildAt(vf_drop.getDisplayedChild());
		RelativeLayout game_drag_view_include = (RelativeLayout) vf_drag.getChildAt(vf_drag.getDisplayedChild());
//		System.err.println("CHILDCOUNT DROP: " + game_drop_view_include.getChildCount());
//		System.err.println("CHILDCOUNT DRAG: " + game_drag_view_include.getChildCount());

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
		Collections.shuffle(itemList);
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


		// Pause Button
		Button pausebtn = (Button) findViewById(R.id.pause_btn);
		pausebtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cdt.cancel();
				//earcon.pause();
				// Show paused screen options
				Intent i = new Intent(GameActivity.this, PauseActivity.class);
				i.putExtra("resumeTime", (int) (millisInFuture));
				i.putExtra("level_value", level);
				i.putExtra("Win_Time", 0);
				startActivityForResult(i, 1);
				//finish(); // finish current activity
			}
		});  
		
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if(resultCode == RESULT_OK){ //Symbolizes that we should keep running.
				//Do nothing.
			}
			if (resultCode == RESULT_CANCELED) { //Symbolizes that we should finish the activity.
				cdt.cancel(); //Just in case it restarted.
				finish();
			}
		}
	}

	@Override
	public void onPause() {
		cdt.cancel();
		super.onPause();
	}

	
	@Override
	public void onResume() {
		
//		final ProgressBar m_bar = (ProgressBar) findViewById(R.id.progressbar);

		// Implements CountdownTimer
		cdt = new CountDownTimer(model.getTimeLeft(), countDownInterval) {
//			TextView timeLeft = (TextView) findViewById(R.id.time_left_value);

			public void onTick(long millisUntilFinished) {
//				timeLeft.setText("" + millisUntilFinished / 1000);
//				millisInFuture = millisUntilFinished;
				if (millisUntilFinished <= 10000) {
					//earcon.start();
				}
				model.setTimeLeft(millisUntilFinished);
			}
		

			public void onFinish() {
				//earcon.release();
				// Display screen after finishing a level
				model.setTimeLeft(0);
				model_level.setStatus(true);
				Intent i = new Intent(GameActivity.this, EndActivity.class);
				//The extras should not be needed any more. Simply use the model to get the level and timeLeft.
				i.putExtra("resumeTime", 30000);
				i.putExtra("level_value", level);
				startActivity(i);
				finish();
			}
		}.start();
		
		super.onResume();
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

						model_level.setStatus(false);
						//You win. Go to endActivity to show this.
						//Intent intent = getIntent();
						//final String level = intent.getStringExtra("level_value");
						Intent i = new Intent(GameActivity.this, EndActivity.class);
						//i.putExtra("Win_Time", (int) model.getTimeLeft()/1000);//Integer.parseInt(timeLeft.getText().toString()));
						//i.putExtra("level_value", level);
						startActivity(i);
						cdt.cancel();
						finish();
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
				if(!layout.getChildAt(i).getTag().equals(true)) {
					return false;
				}
			}
			return true;
		}
	}

}
