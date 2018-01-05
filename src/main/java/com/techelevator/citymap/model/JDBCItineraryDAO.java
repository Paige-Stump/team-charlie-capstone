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
	public List<Itinerary> getItineraryById(String userName, String itineraryName) {
		List<Itinerary> itineraries = new ArrayList<>();
		List<Landmark> landmarks = new ArrayList<>();
		String sqlGetLandmarksFromItinerary = "SELECT * FROM landmarks "+
									"JOIN itinerary ON itinerary.landmark_id = landmark.landmark_id " +
									"WHERE itinerary.user_name = ? AND WHERE itinerary.itinerary_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLandmarksFromItinerary, userName, itineraryName);
		while (results.next()) {
			landmarks.add(mapRowToLandmark(results));
		}
		String sqlGetItineraryByUserName = "SELECT * FROM itinerary " +
									"WHERE user_name = ? AND WHERE itinerary.itinerary_name = ?";
		SqlRowSet itineraryResults = jdbcTemplate.queryForRowSet(sqlGetItineraryByUserName, userName, itineraryName);
		while(itineraryResults.next()){
			itineraries.add(mapRowToItinerary(itineraryResults, landmarks));
		}
		return itineraries;
	}
	
	@Override
	public List<Itinerary> getAllItineraries(String userName){
		List<Itinerary> itineraries = new ArrayList<>();
		List<String> itineraryNames = getItineraryName(userName);
		List<Landmark> landmarks = new ArrayList<>();
		for(String itinerary:itineraryNames){
			// need to get a list of landmarks to make an itinerary object
			String sqlGetItineraryBasedOnUserName = "SELECT * FROM itinerary " +
										"WHERE itinerary_name = ?";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetItineraryBasedOnUserName, itinerary);
			while(results.next()){
				//itineraries.add(results.mapRowToItinerary(results, ))
			}
		}
		return itineraries;
	}
	
	private List<String> getItineraryName(String userName){
		List<String> itineraryNames = new ArrayList<>();
		String sqlGetItineraryName = "SELECT itinerary.itinerary_name FROM itinerary " +
								"JOIN app_user ON app_user.user_name = itinerary.user_name " +
								"WHERE app_user.user_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetItineraryName, userName);
		while(results.next()){
			itineraryNames.add(results.getString("user_name"));
		}
		return itineraryNames;
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
	
	private List<Landmark> getAllLandmarksForItinerary() {
		List<Landmark> allLandmarks = new ArrayList<>();
		String sqlGetAllLandmarks = "SELECT * " + "FROM landmark";
		// need sql to get all landmark for a particular itinerary
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllLandmarks);
		while(results.next()) {
			allLandmarks.add(mapRowToLandmark(results));
		}
		return allLandmarks;
	}

}
