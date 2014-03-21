package se.kth.csc.iprog.matchme.model;

/**
 * Class for symbolizing a database object.
 */
public class Level {
	
	public static int COMPLETED = 1, NOT_COMPLETED = 0;
	private int status;
	private int id;
	private int score;

	/**
	 * @return The level of this level object
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the level.
	 * @param id The level of this level object.
	 */
	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	/**
	 * Sets the highscore of the level
	 * @param score The new highscore.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return 1 if the level is completed, 0 otherwise.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Set the status to {@link Level.COMPLETED} or {@link Level.NOT_COMPLETED}
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	// Can be used by intents between views
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
