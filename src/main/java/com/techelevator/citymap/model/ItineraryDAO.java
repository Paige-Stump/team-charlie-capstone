package com.techelevator.citymap.model;

import java.util.List;

public interface ItineraryDAO {

	//public List<Itinerary> getItineraryByName(String userName, String itineraryName);
	public List<Itinerary> getAllItineraries(String userName);
	public Landmark getLandmarkById(int landmarkId);
	public List<Landmark> getFeaturedLandmarks();
	public List<Landmark> getAllLandmarks();
	public String getWaypointArray(List<Landmark> landmarks);
	public void createNewItinerary(String itineraryName, String startingPoint, String userName, List<Landmark> landmarks);
	public void addLandmarkToItinerary(String itineraryName, String startingPoint, String userName, Landmark landmark);
	public void removeLandmarkFromItinerary(String itineraryName, String userName, int landmarkId);
	public void deleteItinerary(String itineraryName, String userName);
	//public List<String> getItineraryNamesForDashboard(String username);
}
