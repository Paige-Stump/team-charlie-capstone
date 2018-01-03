package com.techelevator.citymap.model;

import java.time.LocalDateTime;

public class Landmark {
	private int landmarkId; 
	private String landmarkName;
	private String landmarkDescription;
	private String landmarkLocation;
	private String landmarkLink;
	
	public int getLandmarkId() {
		return landmarkId;
	}
	public void setLandmarkId(int landmarkId) {
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

}