package com.techelevator.citymap.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCLandmarkDAO implements LandmarkDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCLandmarkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
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
		String sqlGetFeaturedLandmarks = "SELECT * " + "FROM landmark " + "ORDER BY random()" + "LIMIT 3";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFeaturedLandmarks);
		return mapRowSetToFeaturedLandmarks(results);
	}

	private List<Landmark> mapRowSetToFeaturedLandmarks(SqlRowSet results) {
		List<Landmark> myFeaturedLandmarks = new ArrayList<Landmark>();
		while(results.next()) {
			myFeaturedLandmarks.add(mapRowToLandmark(results));
		}
		return myFeaturedLandmarks;
	}

	private Landmark mapRowToLandmark(SqlRowSet results) {
		Landmark myLandmark = new Landmark();myLandmark.setLandmarkName(results.getString("landmark_title"));
		myLandmark.setLandmarkDescription(results.getString("landmark_description"));
		myLandmark.setLandmarkLocation(results.getString("landmark_location"));
		myLandmark.setLandmarkLink(results.getString("wikipedia"));
		return myLandmark;
	}

}
