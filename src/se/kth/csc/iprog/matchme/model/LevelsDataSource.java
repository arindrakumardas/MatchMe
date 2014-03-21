package se.kth.csc.iprog.matchme.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * A DataSource for the levels with helper methods to control the database.
 */
public class LevelsDataSource extends Observable {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_SCORE, MySQLiteHelper.COLUMN_STATUS };

	public LevelsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	/**
	 * Opens the database for writing
	 * @throws SQLException
	 */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	/**
	 * Closes the database.
	 */
	public void close() {
		dbHelper.close();
	}

	/**
	 * Load a level with the specified id from the database
	 * @param id the id of the level
	 * @return The {@link Level} object representing a level in the game.
	 */
	public Level loadLevel(int id) {

		Cursor cursor = database.query(MySQLiteHelper.TABLE_LEVELS, allColumns,
				MySQLiteHelper.COLUMN_ID + "=" + id, null, null, null,
				null);
		cursor.moveToFirst();
		Level level = cursorToLevel(cursor);
		cursor.close();

		// return level
		return level;
	}
	
	/**
	 * Create a new level with default values and put it into the database.
	 * @param id The id of the new level.
	 */
	public void createLevel(int id) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID, id);
		values.put(MySQLiteHelper.COLUMN_SCORE, 0);
		values.put(MySQLiteHelper.COLUMN_STATUS, 0);

		database.insert(MySQLiteHelper.TABLE_LEVELS, null, values);
	}

	/**
	 * Retrieves all of the levels in the database.
	 * @return All levels contained in the database.
	 */
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

	/**
	 * Updates the row in the database that corresponds to the specified {@link Level} object.
	 * @param level The level that should be updated.
	 * @return The number of rows affected
	 */
	public long updateLevel(Level level) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_SCORE, level.getScore());
		values.put(MySQLiteHelper.COLUMN_STATUS, level.getStatus());

		// updating
		long updateId = database.update(MySQLiteHelper.TABLE_LEVELS, values,
				MySQLiteHelper.COLUMN_ID + " = " + level.getId(), null);
		return updateId;
	}

	/**
	 * Transforms a {@link Cursor} object to a {@link Level} object.
	 * @param cursor The cursor that points to the row in the database.
	 * @return The retrieved {@link Level} object.
	 */
	private Level cursorToLevel(Cursor cursor) {
		Level level = new Level();
		level.setId(cursor.getInt(0));
		level.setScore(cursor.getInt(1));
		level.setStatus(cursor.getInt(2));
		return level;
	}
}
