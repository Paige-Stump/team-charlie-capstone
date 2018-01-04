package com.techelevator.citymap.model;

import java.util.List;

public interface MapMarkerDAO {
	
	public List<MapMarker> getAllWaypoints(String itineraryId);
	public String getStartingPoint(String itineraryId);

}
