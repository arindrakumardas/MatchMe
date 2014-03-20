package se.kth.csc.iprog.matchme.model;

public class Level {
	
	private int status;
	private int id;
	private int score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// checks if level is completed or not
	public int getStatus() {
		return status;
	}

	// sets if level is completed or not
	public void setStatus(int status) {
		this.status = status;
	}

	// Can be used by intents between views
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
