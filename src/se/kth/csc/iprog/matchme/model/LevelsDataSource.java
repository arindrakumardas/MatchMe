package se.kth.csc.iprog.matchme.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LevelsDataSource extends Observable {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_SCORE, MySQLiteHelper.COLUMN_STATUS };

	public LevelsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Level loadLevel(int id) {

		// Adding information about the level and if it's completed
//		values.put(MySQLiteHelper.COLUMN_ID, id);
//
//		long insertId = database.insert(MySQLiteHelper.TABLE_LEVELS, null,
//				values);

		Cursor cursor = database.query(MySQLiteHelper.TABLE_LEVELS, allColumns,
				MySQLiteHelper.COLUMN_ID + " = " + id, null, null, null,
				null);
		if(cursor.moveToFirst()==false) { //The database is empty... Fill it with starting values!
			createLevel(id);
			cursor = database.query(MySQLiteHelper.TABLE_LEVELS, allColumns,
					MySQLiteHelper.COLUMN_ID + " = " + id, null, null, null,
					null);
		}
		Level level = new Level();
		level.setId(id);
		level.setScore(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_SCORE)));
		level.setStatus(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_STATUS)));

//		Level level = cursorToLevel(cursor);
		cursor.close();
		database.close();

		// return level
		return level;
	}
	
	private void createLevel(int id) {

		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID, id);
		values.put(MySQLiteHelper.COLUMN_SCORE, 0);
		values.put(MySQLiteHelper.COLUMN_STATUS, 0);

		long insertId = database.insert(MySQLiteHelper.TABLE_LEVELS, null, values);
	}

	// Getting all levels (could be used for LevelView)
	public List<Level> getAllLevels() {
		List<Level> levelsList = new ArrayList<Level>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_LEVELS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Level level = cursorToLevel(cursor);
			levelsList.add(level);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();

		return levelsList;
	}

	public long updateLevel(Level level) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_SCORE, level.getScore());
		values.put(MySQLiteHelper.COLUMN_STATUS, level.getStatus());

		// updating
		long updateId = database.update(MySQLiteHelper.TABLE_LEVELS, values,
				MySQLiteHelper.COLUMN_ID + " = " + level.getId(), null);
		return updateId;
	}

	private Level cursorToLevel(Cursor cursor) {
		Level level = new Level();
		level.setId(cursor.getInt(0));
		level.setScore(cursor.getInt(1));
		level.setStatus(1);
		return level;
	}
}
