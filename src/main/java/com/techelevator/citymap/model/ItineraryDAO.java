package com.techelevator.citymap.model;

import java.util.List;

public interface ItineraryDAO {

	public List<Itinerary> getAllItineraries(String userName);
}
