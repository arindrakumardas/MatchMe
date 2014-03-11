package se.kth.csc.iprog.matchme.android.controller;


import se.kth.csc.iprog.matchme.android.view.StartView;
import android.view.View;
import android.widget.Button;

public class StartController {

	StartView view;
	Button startButton;

	public StartController(StartView view) {

		// store in the class the reference to the Android View
		this.view = view;
	}

}
