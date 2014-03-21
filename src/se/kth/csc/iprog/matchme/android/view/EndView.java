package se.kth.csc.iprog.matchme.android.view;

import java.util.Observable;
import java.util.Observer;

import se.kth.csc.iprog.matchme.android.R;
import se.kth.csc.iprog.matchme.model.MatchModel;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class EndView implements Observer {

	public View view;
	private MatchModel model;
	ViewFlipper vf;

	public EndView(View view, MatchModel model) {
		this.model = model;
		this.view = view;
		model.addObserver(this);
		
		vf = (ViewFlipper)findViewById(R.id.game_end_view_flipper);
		
		// Setup the rest of the view layout
		update(null, MatchModel.SCORE); //Load the score from the model and update the view accordingly.
		update(null, MatchModel.STATUS); //Load the status from the model and update the view accordingly.
	}

	@Override
	public void update(Observable observable, Object data) {
		// Setting the Data
		if(data.equals(MatchModel.SCORE)) {
			System.err.println("FOUND NEW HIGHSCORE TO UPDATE!");
			TextView scorevalue = (TextView) findViewById(R.id.bestscorevalue);
			scorevalue.setText("" + model.getCurrentLevelHighScore());
		} else if(data.equals(MatchModel.STATUS)) {
			if(model.getCurrentLevelStatus() == true) {
				vf.setDisplayedChild(1); //Winning screen
			} else {
				vf.setDisplayedChild(0); //Losing screen
			}
		}
	}
	
	private View findViewById(int id) {
		return view.findViewById(id);
	}

}
