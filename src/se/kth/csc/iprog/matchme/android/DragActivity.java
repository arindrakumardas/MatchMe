package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

public class DragActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	class MyDragListener implements OnDragListener {
		//	    Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		//	    Drawable normalShape = getResources().getDrawable(R.drawable.shape);

		@Override
		public boolean onDrag(View v, DragEvent event) {
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// do nothing
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				//v.setBackgroundDrawable(enterShape);
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				//v.setBackgroundDrawable(normalShape);
				break;
			case DragEvent.ACTION_DROP:
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				//v.setBackgroundDrawable(normalShape);
			default:
				break;
			}
			return true;
		}
	}

}
