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

	/*@Override
	public List<Itinerary> getItineraryByName(String userName, String itineraryName) {
		List<Itinerary> itineraries = new ArrayList<>();
		List<Landmark> landmarks = new ArrayList<>();
		String sqlGetLandmarksFromItinerary = "SELECT * FROM landmark "
				+ "JOIN itinerary ON itinerary.landmark_id = landmark.landmark_id "
				+ "WHERE itinerary.user_name = ? AND WHERE itinerary.itinerary_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLandmarksFromItinerary, userName, itineraryName);
		while (results.next()) {
			landmarks.add(mapRowToLandmark(results));
		}
		String sqlGetItineraryByUserName = "SELECT * FROM itinerary "
				+ "WHERE user_name = ? AND WHERE itinerary.itinerary_name = ?";
		SqlRowSet itineraryResults = jdbcTemplate.queryForRowSet(sqlGetItineraryByUserName, userName, itineraryName);
		while (itineraryResults.next()) {
			itineraries.add(mapRowToItinerary(itineraryResults, landmarks));
		}
		return itineraries;
	}*/

	@Override
	public List<Itinerary> getAllItineraries(String userName) {
		List<Itinerary> itineraries = new ArrayList<>();
		List<String> itineraryNames = getItineraryName(userName);
		for (String itinerary : itineraryNames) {
			List<Landmark> landmarks = getAllLandmarksForItinerary(userName, itinerary);
			String sqlGetItineraryBasedOnName = "SELECT * FROM itinerary " + 
										"WHERE itinerary_name = ? AND WHERE itinerary.user_name = ?";
			SqlRowSet itineraryResults = jdbcTemplate.queryForRowSet(sqlGetItineraryBasedOnName, itinerary, userName);
			while (itineraryResults.next()) {
				itineraries.add(mapRowToItinerary(itineraryResults, landmarks));
			}
		}
		return itineraries;
	}

	private List<String> getItineraryName(String userName) {
		List<String> itineraryNames = new ArrayList<>();
		String sqlGetItineraryName = "SELECT itinerary.itinerary_name FROM itinerary "
				+ "JOIN app_user ON app_user.user_name = itinerary.user_name " + "WHERE app_user.user_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetItineraryName, userName);
		while (results.next()) {
			itineraryNames.add(results.getString("user_name"));
		}
		return itineraryNames;
	}

	private Itinerary mapRowToItinerary(SqlRowSet results, List<Landmark> landmarks) {
		Itinerary itinerary = new Itinerary();
		itinerary.setItineraryName(results.getString("itinerary_name"));
		itinerary.setStartingPoint(results.getString("starting_point"));
		itinerary.setLandmarks(landmarks);
		return itinerary;
	}

	private List<Landmark> getAllLandmarksForItinerary(String userName, String itinerary) {
		List<Landmark> allLandmarks = new ArrayList<>();
		String sqlGetLandmarksFromItinerary = "SELECT * FROM landmark "
				+ "JOIN itinerary ON itinerary.landmark_id = landmark.landmark_id "
				+ "WHERE itinerary.user_name = ? AND WHERE itinerary.itinerary_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLandmarksFromItinerary, userName, itinerary);
		while (results.next()) {
			allLandmarks.add(mapRowToLandmark(results));
		}
		return allLandmarks;
	}

	//can't remember why we need this method
	@Override
	public Landmark getLandmarkById(int landmarkId) {
		Landmark myLandmark = new Landmark();
		String sqlSearchForLandmark = "SELECT * " + "FROM landmark " + "WHERE landmark_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForLandmark, landmarkId);
		if (results.next()) {
			myLandmark = mapRowToLandmark(results);
		}
		return myLandmark;
	}

	@Override
	public List<Landmark> getFeaturedLandmarks() {
		List<Landmark> landmarks = new ArrayList<>();
		String sqlGetFeaturedLandmarks = "SELECT * " + "FROM landmark " + "ORDER BY random()" + "LIMIT 3";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFeaturedLandmarks);
		while(results.next()){
			landmarks.add(mapRowToLandmark(results));
		}
		return landmarks;
	}

	// redundant - just using general mapRowToLandmark - changed in getFeaturedLandmarks
	/*private List<Landmark> mapRowSetToFeaturedLandmarks(SqlRowSet results) {
		List<Landmark> myFeaturedLandmarks = new ArrayList<Landmark>();
		while (results.next()) {
			myFeaturedLandmarks.add(mapRowToLandmark(results));
		}
		return myFeaturedLandmarks;
	}*/

	private Landmark mapRowToLandmark(SqlRowSet results) {
		Landmark myLandmark = new Landmark();
		myLandmark.setLandmarkName(results.getString("landmark_title"));
		myLandmark.setLandmarkDescription(results.getString("landmark_description"));
		myLandmark.setLandmarkLocation(results.getString("landmark_location"));
		myLandmark.setLandmarkLink(results.getString("wikipedia"));
		myLandmark.setLandmarkCity(results.getString("landmark_city"));
		return myLandmark;
	}

	@Override
	public List<Landmark> getAllLandmarks() {
		List<Landmark> allLandmarks = new ArrayList<>();
		String sqlGetAllLandmarks = "SELECT * " + "FROM landmark";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllLandmarks);
		while (results.next()) {
			allLandmarks.add(mapRowToLandmark(results));
		}
		return allLandmarks;
	}
}
