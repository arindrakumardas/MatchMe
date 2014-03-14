package se.kth.csc.iprog.matchme.android;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;

public class DragActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
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
	        // Dropped, reassign View to ViewGroup
//	        View view = (View) event.getLocalState();
//	        ViewGroup owner = (ViewGroup) view.getParent();
//	        owner.removeView(view);
//	        LinearLayout container = (LinearLayout) v;
//	        container.addView(view);
//	        view.setVisibility(View.VISIBLE);
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
