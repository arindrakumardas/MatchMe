package se.kth.csc.iprog.matchme.android;

import se.kth.csc.iprog.matchme.android.view.StartView;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

public class StartActivity extends Activity {

	private AudioManager mAudioManager;
	private boolean mPhoneIsSilent;
	public MediaPlayer mPlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Default call to load previous state
		super.onCreate(savedInstanceState);
		//Play sound
		mPlay = MediaPlayer.create(this, R.raw.audio_file);

		// Set the view for the main activity screen
		// it must come before any call to findViewById method
		setContentView(R.layout.start_view);

		// get info for audio manager
		mAudioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
		checkIfPhoneIsSilent();

		// Creating the view class instance
		StartView startView = new StartView(findViewById(R.id.start_view));
		//TODO: Add a controller for the startView if necessary (maybe to control the volume mute)
		// I think we need to delete the following line:
/*		StartController startController = new StartController(startView);		*/
		
		//Buttons in start_view
		Button startButton = (Button) findViewById(R.id.start_game);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Move to the next view!
				Intent i = new Intent(StartActivity.this, LevelActivity.class);
				startActivity(i);
			}
		});


		Button toggleSoundBtn = (Button) findViewById(R.id.volume);
		toggleSoundBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if (mPhoneIsSilent) {
					mPlay.start();
					mPhoneIsSilent = false;
				} else {
					mPlay.pause();
					mPhoneIsSilent = true;
				}
				toggleUI();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		checkIfPhoneIsSilent();
		toggleUI();
	}

	private void checkIfPhoneIsSilent() {
		int ringerMode = mAudioManager.getRingerMode();
		if (ringerMode == AudioManager.RINGER_MODE_SILENT) {
			mPhoneIsSilent = true;
			mPlay.pause();
		} else {
			mPhoneIsSilent = false;
			mPlay.start();
		}
	}

	// Toggles the UI images from silent to normal and vice-versa
	private void toggleUI() {
		Button imageView = (Button) findViewById(R.id.volume);
		Drawable soundIcon;

		if (mPhoneIsSilent) {
			soundIcon = getResources().getDrawable(R.drawable.volume_off);
		} else {
			soundIcon = getResources().getDrawable(R.drawable.volume_on);
		}
		//Using deprecated method to support minSDKlevel below 16. Maybe change minSDKlevel in manifest and use setBackground instead?
		imageView.setBackgroundDrawable(soundIcon);

	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPlay.release();
	}

}
