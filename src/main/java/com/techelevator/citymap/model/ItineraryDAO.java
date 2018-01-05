package com.techelevator.citymap.model;

import java.util.List;

public interface ItineraryDAO {

	public List<Itinerary> getItineraryById(String userName, String itineraryName);
	public List<Itinerary> getAllItineraries(String userName);
}
