package com.techelevator.citymap.model;

import java.util.List;

public interface ItineraryDAO {

	//public List<Itinerary> getItineraryByName(String userName, String itineraryName);
	public List<Itinerary> getAllItineraries(String userName);
	public Landmark getLandmarkById(int landmarkId);
	public List<Landmark> getFeaturedLandmarks();
	public List<Landmark> getAllLandmarks();
	public String getWaypointArray(List<Landmark> landmarks);
}
