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
	public int GetId(){
		return this.id;
		
	}
	
	public String GetImgShadow(){
		
		return this.imgShadow;
	}
	
	public String GetImgReal(){
		
		return this.imgReal;
	}
}
