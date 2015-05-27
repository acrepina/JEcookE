package model;

public class RecipeModel {
	private String title;
	private String description;
	private int expertise;
	private int nbpeople;
	private int duration;
	private String type;
	public RecipeModel() {}
	public RecipeModel(String title,String description,int expertise,int
			duration,int nbpeople,String type) {
		this.title = title;
		this.description = description;
		this.expertise = expertise;
		this.duration = duration;
		this.nbpeople = nbpeople;
		this.type = type;
	}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getExpertise() {return expertise;}
	public void setExpertise(int expertise) {this.expertise = expertise;}
	public int getNbpeople() {return nbpeople;}
	public void setNbpeople(int nbpeople) {this.nbpeople = nbpeople;}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public int getDuration() {return duration;}
	public void setDuration(int duration) {this.duration = duration; }
	@Override
	public String toString() {
		return "RecipeModelBean [title=" + title + ", description="
				+ description + ", expertise=" + expertise + ", nbpeople="
				+ nbpeople + ", duration=" + duration + ", type=" + type + "]";
	}
	
}