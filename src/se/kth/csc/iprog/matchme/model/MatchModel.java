package se.kth.csc.iprog.matchme.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import android.content.Context;
import se.kth.csc.iprog.matchme.model.MatchItem;

/**
 * Represents the model of the application.
 * 
 * This implementation connects the model with the database where needed, 
 * so that the rest of the code does not need to interact with the database.
 */
public class MatchModel extends Observable{
	private ArrayList<MatchItem> matchItems;
	private Level currentLevel;
	private long timeLeft; //In milliseconds.
	private LevelsDataSource ds;
	
	public static int LEVEL = 1, TIMELEFT = 2, SCORE = 3, STATUS = 4;

	/**
	 * @param context The context of the application.
	 */
	public MatchModel(Context context){
		matchItems = new ArrayList<MatchItem>();
		MatchItem matchItem1 = new MatchItem(1, "crayfish" , "crayfish_shadows");
		MatchItem matchItem2 = new MatchItem(2, "fish" , "fish_shadows");
		MatchItem matchItem3 = new MatchItem(3, "penguin" , "penguin_shadows");
		MatchItem matchItem4 = new MatchItem(4, "turtle" , "turtle_shadows");
		MatchItem matchItem5 = new MatchItem(5, "medusa" , "medusa_shadows");
		MatchItem matchItem6 = new MatchItem(4, "octopus" , "octopus_shadows");
		MatchItem matchItem7 = new MatchItem(7, "star" , "star_shadows");
		MatchItem matchItem8 = new MatchItem(8, "seahorse" , "seahorse_shadows");
		MatchItem matchItem9 = new MatchItem(9, "shark" , "shark_shadows");
		MatchItem matchItem10 = new MatchItem(10, "whale" , "whale_shadows");
		MatchItem matchItem11 = new MatchItem(11, "shrimp" , "shrimp_shadows");
		MatchItem matchItem12 = new MatchItem(12, "dolphin" , "dolphin_shadows");

		this.matchItems.add(matchItem1);
		this.matchItems.add(matchItem2);
		this.matchItems.add(matchItem3);
		this.matchItems.add(matchItem4);
		this.matchItems.add(matchItem5);
		this.matchItems.add(matchItem6);
		this.matchItems.add(matchItem7);
		this.matchItems.add(matchItem8);
		this.matchItems.add(matchItem9);
		this.matchItems.add(matchItem10);
		this.matchItems.add(matchItem11);
		this.matchItems.add(matchItem12);

		//Create the levels in the database.
		ds = new LevelsDataSource(context);
		ds.open();
		ds.createLevel(1);
		ds.createLevel(2);
		ds.createLevel(3);
		ds.createLevel(4);
		ds.createLevel(5);
		ds.close();
		setCurrentLevel(1); //Just to have some default level.. Should not be necessary.
	}

	/**
	 * Returns a list of random {@link MatchItem}s, 
	 * so that the images gets placed randomly on the screen.
	 * The size of the returned list is twice the level.
	 * @param level The level to create the list for.
	 * @return The list of level*2 random {@link MatchItem}s.
	 */
	public MatchItem[] getRandomMatchItems(int level){
		int numOfItems = level *2;
		MatchItem[] result = new MatchItem[numOfItems];
		Random rand = new Random();
		int size = this.matchItems.size();
		boolean[] used = new boolean[size];
		
		int k = 0;
		while(k < numOfItems) {
			int i = rand.nextInt(size);
			if(used[i]) {
				continue; //This number has already been used, roll a new random number.
			}
			result[k] = matchItems.get(i);
			
			used[i] = true;
			k++;
		}
		return result;
	}
	
	/**
	 * Loads the specified level to the database and sets it as the current level of the model.
	 * @param level The new current level.
	 */
	public void setCurrentLevel(int level) {
		ds.open();
		currentLevel = ds.loadLevel(level);
		ds.close();
		setChanged();
		notifyObservers(LEVEL);
	}
	
	/**
	 * @return The current level
	 */
	public int getCurrentLevel() {
		return currentLevel.getId();
	}
	
	/**
	 * Sets the new high score of the current level
	 * @param score The new high score
	 */
	public void setCurrentLevelHighScore(int score) {
		currentLevel.setScore(score);
		ds.open();
		ds.updateLevel(currentLevel);
		ds.close();
		setChanged();
		notifyObservers(SCORE);
	}
	
	/**
	 * @return The high score of the current level.
	 */
	public int getCurrentLevelHighScore() {
		return currentLevel.getScore();
	}
	
	/**
	 * Updates the status of the current level in the model and the database.
	 * @param status The new status.
	 */
	public void setCurrentLevelStatus(boolean status) {
		if(status == true) {
			currentLevel.setStatus(1);
		} else {
			currentLevel.setStatus(0);
		}
		ds.open();
		ds.updateLevel(currentLevel);
		ds.close();
		setChanged();
		notifyObservers(STATUS);
	}
	
	/**
	 * Returns the status of the specified level from the database.
	 * @param level The specified level
	 * @return The status of the specified level.
	 */
	public boolean getStatus(int level) {
		ds.open();
		Level l = ds.loadLevel(level);
		ds.close();
		if(l.getStatus() == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return The status of the current level
	 */
	public boolean getCurrentLevelStatus() {
		if(currentLevel.getStatus() == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Sets the time left of the current game.
	 * @param timeLeft The updated time left.
	 */
	public void setTimeLeft(long timeLeft) {
		this.timeLeft = timeLeft;
		setChanged();
		notifyObservers(TIMELEFT);
	}
	
	/**
	 * 
	 * @return The time left of the current game.
	 */
	public long getTimeLeft() {
		return timeLeft;
	}
}
