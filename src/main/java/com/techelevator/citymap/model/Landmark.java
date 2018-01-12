package com.techelevator.citymap.model;


public class Landmark {
	private String landmarkId; 
	private String landmarkName;
	private String landmarkCity;
	private String landmarkDescription;
	private String landmarkLocation;
	private String landmarkLink;
	private String nameAndCity;
	
	public String getNameAndCity() {
		this.nameAndCity = this.landmarkName + ", " + this.landmarkCity; 
		return nameAndCity;
	}
	public void setNameAndCity() {
		this.nameAndCity = this.landmarkName + ", " + this.landmarkCity;
	}
	
	public String getLandmarkId() {
		return landmarkId;
	}
	public void setLandmarkId(String landmarkId) {
		this.landmarkId = landmarkId;
	}
	public String getLandmarkName() {
		return landmarkName;
	}
	public void setLandmarkName(String landmarkName) {
		this.landmarkName = landmarkName;
	}
	public String getLandmarkDescription() {
		return landmarkDescription;
	}
	public void setLandmarkDescription(String landmarkDescription) {
		this.landmarkDescription = landmarkDescription;
	}
	public String getLandmarkLocation() {
		return landmarkLocation;
	}
	public void setLandmarkLocation(String landmarkLocation) {
		this.landmarkLocation = landmarkLocation;
	}
	public String getLandmarkLink() {
		return landmarkLink;
	}
	public void setLandmarkLink(String landmarkLink) {
		this.landmarkLink = landmarkLink;
	}
	public String getLandmarkCity() {
		return landmarkCity;
	}
	public void setLandmarkCity(String landmarkCity) {
		this.landmarkCity = landmarkCity;
	}
}
