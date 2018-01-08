package com.techelevator.citymap.model;

import java.util.List;

public interface ItineraryDAO {

	public Itinerary getItineraryByName(String userName, String itineraryName, String startingPoint);
	public List<Itinerary> getAllItineraries(String userName);
	public Landmark getLandmarkById(String landmarkId);
	//public List<Landmark> getLandmarksByItinerary(String itineraryName, String userName);
	public List<Landmark> getFeaturedLandmarks();
	public List<Landmark> getAllLandmarks();
	public String getWaypointArray(List<Landmark> landmarks);
	public void createNewItinerary(String itineraryName, String startingPoint, String userName, List<Landmark> landmarks);
	public void addLandmarkToItinerary(String itineraryName, String startingPoint, String userName, Landmark landmark);
	public void removeLandmarkFromItinerary(String itineraryName, String userName, int landmarkId);
	public void deleteItinerary(String itineraryName, String userName);
	//public List<String> getItineraryNamesForDashboard(String username);
}
