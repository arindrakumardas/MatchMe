package se.kth.csc.iprog.matchme.android;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
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
import android.widget.ViewFlipper;
import se.kth.csc.iprog.matchme.android.view.GameView;
import se.kth.csc.iprog.matchme.model.MatchModel;
import se.kth.csc.iprog.matchme.model.MatchItem;

/**
 * The activity that controls everything while playing the actual game.
 * Keeps track of the sounds, the time, and updates the screen when an image is matched.
 */
public class GameActivity extends Activity {

	private static long countDownInterval = 1000;
	long millisInFuture;
	int Win_Time = 0;
	private CountDownTimer cdt;
	private MatchModel model;
	private Vibrator viber;

	
	// MediaPlayer should have subtitle controller (NO FIXES REQUIRED FOR AUDIO FILES)
    // http://stackoverflow.com/questions/20087804/should-have-subtitle-controller-already-set-mediaplayer-error-android
	private boolean earconIsRunning;
	private MediaPlayer correctEarcon;
	private MediaPlayer earcon;
	private MediaPlayer rightMatch;
	private MediaPlayer wrongEarcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_view);

		model = ((MatchMeApplication) this.getApplication()).getModel();
		model.setTimeLeft(30000);
		
		GameView gameView = new GameView(findViewById(R.id.game_view), model);

		// Set up sounds for the game
		correctEarcon = MatchMeApplication.getMediaPlayer(this, R.raw.correct);
		earcon = MatchMeApplication.getMediaPlayer(this, R.raw.timesup);
		earconIsRunning = false;
		rightMatch = MatchMeApplication.getMediaPlayer(this, R.raw.click);
		wrongEarcon = MatchMeApplication.getMediaPlayer(this, R.raw.wrong);
		viber = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		//		TextView levelName = (TextView) findViewById(R.id.level_value);
		ViewFlipper vf_drop = (ViewFlipper)findViewById(R.id.game_drop_view_include);
		ViewFlipper vf_drag = (ViewFlipper)findViewById(R.id.game_drag_view_include);
		
		MatchItem[] images = model.getRandomMatchItems(model.getCurrentLevel());

		//Load the correct level
		RelativeLayout game_drop_view_include = (RelativeLayout) vf_drop.getChildAt(vf_drop.getDisplayedChild());
		RelativeLayout game_drag_view_include = (RelativeLayout) vf_drag.getChildAt(vf_drag.getDisplayedChild());
		

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

				// Show paused screen options
				Intent i = new Intent(GameActivity.this, PauseActivity.class);
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
				releaseEarcons();
				finish();
			}
		}
	}

	@Override
	public void onPause() {
		cdt.cancel();
		if(earconIsRunning) {
			earcon.pause();
			earconIsRunning = false;
		}
		super.onPause();
	}


	@Override
	public void onResume() {
		// Implements CountdownTimer
		cdt = new CountDownTimer(model.getTimeLeft(), countDownInterval) {

			public void onTick(long millisUntilFinished) {
				if (millisUntilFinished <= 10000) {
					earcon.start();
					earconIsRunning = true;
				}
				model.setTimeLeft(millisUntilFinished);
			}

			public void onFinish() {
				// Display screen after finishing a level
				model.setTimeLeft(0);
				earconIsRunning = false;
				Intent i = new Intent(GameActivity.this, EndActivity.class);
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
					
					rightMatch.start();
					if(isWin()) {
						//You win. Go to endActivity to show this.
						correctEarcon.start();
						earconIsRunning = false;
					
						Intent i = new Intent(GameActivity.this, EndActivity.class);
						startActivity(i);
						cdt.cancel();
						finish();
					}
				} else {
					wrongEarcon.start();
					viber.vibrate(50);
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
	
	private void releaseEarcons() {
		correctEarcon.release();
		earcon.release();
		rightMatch.release();
		wrongEarcon.release();
	}
	
	@Override
	public void onDestroy() {
		releaseEarcons();
		super.onDestroy();
	}

}
