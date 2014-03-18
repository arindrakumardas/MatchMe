package se.kth.csc.iprog.matchme.android;

import android.app.Application;
import android.content.Context;
import se.kth.csc.iprog.matchme.model.MatchModel;

public class MatchMeApplication extends Application {

		private MatchModel model = new MatchModel();
	
		public MatchModel getModel() {
			return model;
		}
	
		public void setModel(MatchModel model) {
			this.model = model;
		}
	
	
		public static int getImageResId(Context context, String name){
			return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
		}


}
