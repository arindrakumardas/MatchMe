package se.kth.csc.iprog.matchme.model;

public class MatchItem {
	String imgShadow;
	String imgReal;
	int id;
	
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
