package se.kth.csc.iprog.matchme.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Helper for the MySQLite database. 
 * Handles the creation and upgrade of the database.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_LEVELS = "levels";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_SCORE = "score";
	public static final String COLUMN_STATUS = "status";

	private static final String DATABASE_NAME = "levels.db";
	private static final int DATABASE_VERSION = 5;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "CREATE TABLE "
			+ TABLE_LEVELS + "(" 
			+ COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_SCORE + " INTEGER NOT NULL, " 
			+ COLUMN_STATUS + " INTEGER NOT NULL);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVELS);
		onCreate(db);
	}

} 