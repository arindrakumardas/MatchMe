package se.kth.csc.iprog.matchme.model;

/**
 * Represents an image in the game, with the name of an image and its matching shadow.
 */
public class MatchItem {
	String imgShadow;
	String imgReal;
	int id;
	
	/**
	 * @param id The id of the image.
	 * @param imgReal The name of the image in the file system
	 * @param imgShadow The name of the shadow corresponding to the image.
	 */
	public MatchItem(int id, String imgReal, String imgShadow){
		this.imgReal = imgReal;
		this.imgShadow = imgShadow;
		this.id = id;
	}
	
	//Getters 
	public int getId(){
		return id;
	}
	
	public String getImgShadow(){
		return imgShadow;
	}
	
	public String getImgReal(){
		return imgReal;
	}
	
	@Override
	public String toString() {
		return id + ", " + imgReal + ", " + imgShadow;
	}
}
