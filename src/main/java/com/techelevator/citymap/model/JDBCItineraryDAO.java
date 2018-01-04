package com.techelevator.citymap.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JDBCItineraryDAO implements ItineraryDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCItineraryDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Itinerary> getAllItineraries(String userName) {
		List<Itinerary> itineraries = new ArrayList<>();
		List<Landmark> landmarks = new ArrayList<>();
		String sqlGetLandmarksFromItinerary = "SELECT * FROM landmarks "+
									"JOIN itinerary ON itinerary.landmark_id = landmark.landmark_id " +
									"WHERE itinerary.user_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLandmarksFromItinerary, userName);
		while (results.next()) {
			landmarks.add(mapRowToLandmark(results));
		}
		String sqlGetItineraryByUserName = "SELECT * FROM itinerary " +
									"WHERE user_name = ?";
		SqlRowSet itineraryResults = jdbcTemplate.queryForRowSet(sqlGetItineraryByUserName, userName);
		while(itineraryResults.next()){
			itineraries.add(mapRowToItinerary(itineraryResults, landmarks));
		}
		return itineraries;
	}
	
	public Landmark mapRowToLandmark(SqlRowSet results) {
		Landmark myLandmark = new Landmark();myLandmark.setLandmarkName(results.getString("landmark_title"));
		myLandmark.setLandmarkDescription(results.getString("landmark_description"));
		myLandmark.setLandmarkLocation(results.getString("landmark_location"));
		myLandmark.setLandmarkLink(results.getString("wikipedia"));
		return myLandmark;
	}
	
	private Itinerary mapRowToItinerary (SqlRowSet results, List<Landmark> landmarks){
		Itinerary itinerary = new Itinerary();
		itinerary.setItineraryName(results.getString("itinerary_name"));
		itinerary.setStartingPoint(results.getString("starting_point"));
		itinerary.setLandmarks(landmarks);
		return itinerary;
	}

}
