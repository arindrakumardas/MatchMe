package se.kth.csc.iprog.matchme.android.controller;


import se.kth.csc.iprog.matchme.android.R;
import se.kth.csc.iprog.matchme.android.view.EndView;
import se.kth.csc.iprog.matchme.model.MatchModel;
import android.widget.TextView;

public class EndController {

	EndView view;

	public EndController(EndView view, MatchModel model) {
		this.view = view;

		final String lost = (view.view.getResources().getString(R.string.end_msg_lose));
		final String win = (view.view.getResources().getString(R.string.end_msg_won));
		final String pass = (view.view.getResources().getString(R.string.end_msg_pass));

		//Score algorithm
		int baseScoreValue = 100;
		int score = (int) (baseScoreValue * (model.getTimeLeft()/1000) * model.getCurrentLevel()) ; 

		TextView game_end_msg = (TextView) view.view.findViewById(R.id.game_end_msg);
		TextView scoreView = (TextView) view.view.findViewById(R.id.scorevalue);
		scoreView.setText("" + score);
		if (score==0){ //The user lost.
			game_end_msg.setText(lost);
		} else { //The user won.
			model.setCurrentLevelStatus(true);
			if (score < model.getCurrentLevelHighScore()) {
				game_end_msg.setText(pass);
			} else {
				// TODO: Add new high score message
				model.setCurrentLevelHighScore(score);
				game_end_msg.setText(win);
			}
		}
		
	}

}
