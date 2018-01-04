package com.techelevator.citymap.model;

import java.util.List;

public class Itinerary {
	
	//private int itineraryId; 
	private String itineraryName;
	private String startingPoint;
	private List<Landmark> landmarks;
	
	public List<Landmark> getLandmarks() {
		return landmarks;
	}
	public void setLandmarks(List<Landmark> landmarks) {
		this.landmarks = landmarks;
	}
	/*public int getItineraryId() {
		return itineraryId;
	}*/
	public String getItineraryName() {
		return itineraryName;
	}
	public String getStartingPoint() {
		return startingPoint;
	}
	/*public void setItineraryId(int itineraryId) {
		this.itineraryId = itineraryId;
	}*/
	public void setItineraryName(String itineraryName) {
		this.itineraryName = itineraryName;
	}
	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}
}
