package se.kth.csc.iprog.matchme.android;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import se.kth.csc.iprog.matchme.model.Level;
import se.kth.csc.iprog.matchme.model.LevelsDataSource;
import se.kth.csc.iprog.matchme.model.MatchModel;

public class MatchMeApplication extends Application {

		private MatchModel model = new MatchModel(this);
		private Level model_level = new Level();
		private LevelsDataSource ds = new LevelsDataSource(this);
	
		public MatchModel getModel() {
			return model;
		}
	
		public Level getLevel() {
			return model_level;
		}
		
		public void setModel(MatchModel model) {
			this.model = model;
		}
	
		public LevelsDataSource getDatabase() {
			return ds;
		}
	
		public static int getImageResId(Context context, String name){
			return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
		}


}
