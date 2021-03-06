package se.kth.csc.iprog.matchme.android.view;

import java.util.Observable;
import java.util.Observer;

import se.kth.csc.iprog.matchme.android.R;
import se.kth.csc.iprog.matchme.model.MatchModel;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Updates the view of the game according to changes in the model.
 */
public class GameView implements Observer {

	private View view;
	private MatchModel model;
	private TextView levelName;
	private ViewFlipper vf_drop;
	private ViewFlipper vf_drag;
	
	/**
	 * @param view The layout of the screen.
	 * @param model The model of the application.
	 */
	public GameView(View view, MatchModel model) {
		this.model = model;
		// store in the class the reference to the Android View
		this.view = view;

		model.addObserver(this);
		
		levelName = (TextView) findViewById(R.id.level_value);
		vf_drop = (ViewFlipper)findViewById(R.id.game_drop_view_include);
		vf_drag = (ViewFlipper)findViewById(R.id.game_drag_view_include);
		
		update(null, MatchModel.LEVEL); //Load the level from the model and update the view accordingly.
		update(null, MatchModel.TIMELEFT); //Load the timeleft from the model and update the view accordingly.
	}

	@Override
	public void update(Observable observable, Object data) {
		// Setting the Data
		if(data.equals(MatchModel.LEVEL)) {
			int level = model.getCurrentLevel();
			levelName.setText("" + level);
			vf_drop.setDisplayedChild(level-1);
			vf_drag.setDisplayedChild(level-1);
		} else if(data.equals(MatchModel.TIMELEFT)){
			TextView timeLeft = (TextView) findViewById(R.id.time_left_value);
			timeLeft.setText("" + model.getTimeLeft() / 1000);
			ProgressBar m_bar = (ProgressBar) findViewById(R.id.progressbar);
			m_bar.setProgress ( (int) (model.getTimeLeft()/1000) );
		}
	}
	
	/**
	 * Convenience method
	 */
	private View findViewById(int id) {
		return view.findViewById(id);
	}

}
