package com.techelevator.citymap.model;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItineraryDAO {

	public Itinerary getItineraryByName(String userName, String itineraryName, String startingPoint);
	public List<Itinerary> getAllItineraries(String userName);
	public Landmark getLandmarkById(String landmarkId);
	//public List<Landmark> getLandmarksByItinerary(String itineraryName, String userName);
	public List<Landmark> getFeaturedLandmarks();
	public List<Landmark> getAllLandmarks();
	public List<Landmark> searchForLandmarks(String search);
	public String getWaypointArray(List<Landmark> landmarks);
	public void createNewItinerary(String itineraryName, String startingPoint, String userName, List<Landmark> landmarks);
	public void addLandmarkToItinerary(String itineraryName, String startingPoint, String userName, Landmark landmark);
	public void removeLandmarkFromItinerary(String itineraryName, String userName, String landmarkId);
	public void deleteItinerary(String itineraryName, String userName);
	//public List<String> getItineraryNamesForDashboard(String username);
	public List<Landmark> getLandmarksNotInItinerary(String userName, String itineraryName);
	public void updateItineraryStartingPoint(String changedStartingPoint, String itineraryName, String oldStartingPoint,
			String userName);
	public void adminCreateNewLandmark(String landmarkTitle, String landmarkDescription, String landmarkLocation,  String landmarkCityState, String landmarkHref);
}
