package se.kth.csc.iprog.matchme.android;

import android.app.Application;
import android.content.Context;
import se.kth.csc.iprog.matchme.model.Level;
import se.kth.csc.iprog.matchme.model.MatchModel;

public class MatchMeApplication extends Application {

		private MatchModel model;
		private Level model_level = new Level();
	
		@Override
		public void onCreate() {
			super.onCreate();
			model = new MatchModel(getApplicationContext());
		}
		
		public MatchModel getModel() {
			return model;
		}
	
		public Level getLevel() {
			return model_level;
		}
		
		public void setModel(MatchModel model) {
			this.model = model;
		}
	
		public static int getImageResId(Context context, String name){
			return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
		}


}
