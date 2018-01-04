package com.techelevator.citymap.model;

import java.util.List;

public interface LandmarkDAO {
	
	public Landmark getLandmarkById(int landmarkId);
	public List<Landmark> getFeaturedLandmarks();
	public List<Landmark> getAllLandmarks();

}
