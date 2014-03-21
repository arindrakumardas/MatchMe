package se.kth.csc.iprog.matchme.android;

import java.io.IOException;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import se.kth.csc.iprog.matchme.model.Level;
import se.kth.csc.iprog.matchme.model.MatchModel;

/**
 * The main application. Holds a global model that are to be accessed whenever needed,
 * along with helper methods.
 */
public class MatchMeApplication extends Application {

		private MatchModel model;
	
		@Override
		public void onCreate() {
			super.onCreate();
			model = new MatchModel(getApplicationContext());
		}
		
		/**
		 * Returns the model of the application.
		 */
		public MatchModel getModel() {
			return model;
		}
		
		/**
		 * Sets the model of the application to the specified model.
		 */
		public void setModel(MatchModel model) {
			this.model = model;
		}
	
		/**
		 * Returns the image integer id for the image with the specified name.
		 * @param context The context of the image
		 * @param name Image file name
		 */
		public static int getImageResId(Context context, String name){
			return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
		}
		
		/**
		 * Returns a MediaPlayer that plays the sound specified by the id of the audiofile.
		 * @param context Context of the MediaPlayer
		 * @param audiofile Id of the audio file to be played.
		 */
		public static MediaPlayer getMediaPlayer(Context context, int audiofile) {
			MediaPlayer sound = MediaPlayer.create(context, audiofile);
			
			return sound;
		}

}
