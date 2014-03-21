package se.kth.csc.iprog.matchme.android.view;


import se.kth.csc.iprog.matchme.android.R;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class StartView {

	View view;
	private ImageView mScanner;
	private ImageView mScanner_1;
	private Animation mAnimation;
	private Animation mAnimation_1;

	/**
	 * @param view The layout of the screen.
	 */
	public StartView(View view) {
		// store in the class the reference to the Android View
		this.view = view;

		// Setup the rest of the view layout
		mScanner = (ImageView) view.findViewById(R.id.animate_bubble);
		mScanner.setVisibility(View.VISIBLE);
		mAnimation = new TranslateAnimation(0, 0, 200, 0);
		mAnimation.setDuration(4000);
		mAnimation.setStartOffset(100);
		//mAnimation.setFillAfter(true);
		mAnimation.setRepeatCount(20);
		mAnimation.setRepeatMode(Animation.INFINITE);
		mScanner.setAnimation(mAnimation);
		mScanner.setVisibility(View.VISIBLE);


		mScanner_1 = (ImageView) view.findViewById(R.id.animate_bubble_1);
		mScanner_1.setVisibility(View.VISIBLE);
		mAnimation_1 = new TranslateAnimation(0, 0, 400, 0);
		mAnimation_1.setDuration(7000);
		mAnimation_1.setStartOffset(8000);
		//mAnimation_1.setFillAfter(true);
		mAnimation_1.setRepeatCount(20);
		mAnimation_1.setRepeatMode(Animation.INFINITE);
		mScanner_1.setAnimation(mAnimation);
		mScanner_1.setVisibility(View.VISIBLE);
	}

}
