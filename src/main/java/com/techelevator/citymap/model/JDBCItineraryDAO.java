package com.techelevator.citymap.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCItineraryDAO implements ItineraryDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCItineraryDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Itinerary getItineraryByName(String userName, String itineraryName, String startingPoint) {
		Itinerary itinerary = new Itinerary();
		List<Landmark> landmarks = getAllLandmarksForItinerary(userName, itineraryName);
		itinerary.setItineraryName(itineraryName);
		itinerary.setStartingPoint(startingPoint);
		itinerary.setLandmarks(landmarks);
		return itinerary;
	}

	@Override
	public List<Itinerary> getAllItineraries(String userName) {
		List<Itinerary> itineraries = new ArrayList<>();
		List<String> itineraryNames = getItineraryName(userName); //this works
		for (String itinerary : itineraryNames) { 
			Itinerary thisItinerary = new Itinerary();
			List<Landmark> landmarks = getAllLandmarksForItinerary(userName, itinerary);
			String sqlGetItineraryBasedOnName = "SELECT * FROM itinerary " + 
										"WHERE itinerary.itinerary_name = ? AND itinerary.user_name = ?";
			SqlRowSet itineraryLandmarkResults = jdbcTemplate.queryForRowSet(sqlGetItineraryBasedOnName, itinerary, userName);
			while (itineraryLandmarkResults.next()) {
				thisItinerary = mapRowToItinerary(itineraryLandmarkResults, landmarks);
			} 
			itineraries.add(thisItinerary);
		}
		return itineraries;
	}

	private List<String> getItineraryName(String userName) {
		List<String> itineraryNames = new ArrayList<>();
		String sqlGetItineraryName = "SELECT itinerary_name FROM itinerary WHERE itinerary.user_name = ? GROUP BY itinerary.itinerary_name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetItineraryName, userName);
		while (results.next()) {
			itineraryNames.add(results.getString("itinerary_name"));
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
				+ "WHERE itinerary.user_name = ? AND itinerary.itinerary_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLandmarksFromItinerary, userName, itinerary);
		while (results.next()) {
			allLandmarks.add(mapRowToLandmark(results));
		}
		return allLandmarks;
	}

	//can't remember why we need this method
	@Override
	public Landmark getLandmarkById(String landmarkId) {
		int landmarkInt = Integer.parseInt(landmarkId);
		Landmark myLandmark = new Landmark();
		String sqlSearchForLandmark = "SELECT * " + "FROM landmark " + "WHERE landmark_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForLandmark, landmarkInt);
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
	
	@Override
	public List<Landmark> getLandmarksNotInItinerary(String userName, String itineraryName) {
		List<Landmark> landmarks = new ArrayList<>();
		
		String sqlGetLandmarksNotInItinerary = "SELECT * FROM landmark WHERE landmark.landmark_id NOT IN " 
				+ "(SELECT landmark.landmark_id FROM landmark " + 
				"JOIN itinerary ON itinerary.landmark_id = landmark.landmark_id WHERE "  
				+ "user_name = ? AND itinerary_name = ?) ";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLandmarksNotInItinerary, userName, itineraryName);
		while(results.next()) {
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
		myLandmark.setLandmarkId(Integer.toString(results.getInt("landmark_id")));
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
	
	@Override
	public String getWaypointArray(List<Landmark> landmarks) {
		String wayPointBuilder = "[";
		List<String> nameAndCityBuilder = new ArrayList<>();
		for(Landmark landmark: landmarks) {
			nameAndCityBuilder.add(landmark.getNameAndCity());
			
		}
		
		for(String nameAndCityConcat : nameAndCityBuilder) {
			wayPointBuilder += "{location: \"" + nameAndCityConcat + "\", stopover: true}, ";
		}
		wayPointBuilder += "]";
		return wayPointBuilder;
	}
	
	
	/*public List<String> getItineraryNamesForDashboard(String userName) {
		List<String> itineraryNames = new ArrayList<>();
		String sqlGetItineraryName = "SELECT itinerary_name FROM itinerary WHERE itinerary.user_name = ? GROUP BY itinerary.itinerary_name";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetItineraryName, userName);
		while (results.next()) {
			itineraryNames.add(results.getString("itinerary_name"));
		}
		return itineraryNames;
	} */

	
	public void createNewItinerary(String itineraryName, String startingPoint, String userName, List<Landmark> landmarks) {
		for (Landmark landmark : landmarks) {
			String ourString = landmark.getLandmarkId();
			
			int ourInt = Integer.parseInt(landmark.getLandmarkId());
			String sqlCreateNewItinerary = "INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) " +
					"VALUES (?, ?, ?, ?)";
			jdbcTemplate.update(sqlCreateNewItinerary, userName, itineraryName, startingPoint, ourInt);
		}
	}
	
	public void addLandmarkToItinerary(String itineraryName, String startingPoint, String userName, Landmark landmark) {
		String sqlAddLandmarkToItinerary = "INSERT INTO itinerary (user_name, itinerary_name, starting_point, landmark_id) " +
					"VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddLandmarkToItinerary, userName, itineraryName, startingPoint, Integer.parseInt(landmark.getLandmarkId()));
	}
	
	public void removeLandmarkFromItinerary(String itineraryName, String userName, String landmarkId) {
		String sqlRemoveLandmarkFromItinerary = "DELETE FROM itinerary WHERE itinerary.itinerary_name = ? AND itinerary.user_name = ? AND landmark_id = ?";
		jdbcTemplate.update(sqlRemoveLandmarkFromItinerary, itineraryName, userName, Integer.parseInt(landmarkId));
	}
	
	public void deleteItinerary(String itineraryName, String userName) {
		String sqlDeleteItinerary = "DELETE FROM itinerary WHERE itinerary.itinerary_name = ? AND itinerary.user_name = ?";
		jdbcTemplate.update(sqlDeleteItinerary, itineraryName, userName);
	}

	@Override
	public List<Landmark> searchForLandmarks(String search) {
		List<Landmark> searchedLandmarks = new ArrayList<>();
		String sqlSearchForLandmarks = "SELECT * FROM landmark WHERE landmark_description ILIKE ?" ;
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForLandmarks, "%" + search + "%");
		while (results.next()) {
			searchedLandmarks.add(mapRowToLandmark(results));
		}
		return searchedLandmarks;
	}
	
	@Override
	public void updateItineraryStartingPoint(String changedStartingPoint, String itineraryName, String oldStartingPoint, String userName){
		String sqlChangeStartingPoint = "UPDATE itinerary SET starting_point = ? WHERE itinerary_name = ? " +
										"AND starting_point = ? AND user_name = ?";
		jdbcTemplate.update(sqlChangeStartingPoint, changedStartingPoint, itineraryName, oldStartingPoint, userName);
	}

	@Override
	public void adminCreateNewLandmark(String landmarkTitle, String landmarkDescription, String landmarkLocation, String landmarkCityState,
			String landmarkHref) {
		
		String sqlCreateNewLandmark = "INSERT INTO landmark (landmark_title, landmark_description, landmark_location, landmark_city, wikipedia) " +
				"VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlCreateNewLandmark, landmarkTitle, landmarkDescription, landmarkLocation, landmarkCityState, landmarkHref);
		
	}
}
