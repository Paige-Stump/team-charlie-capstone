package com.techelevator.citymap.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCMapMarkerDAO implements MapMarkerDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCMapMarkerDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<MapMarker> getAllWaypoints(String itineraryId) {
		String sqlGetAllWaypoints = "SELECT landmark_location " 
									+ "FROM landmark " 
									+ "WHERE landmark_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllWaypoints, itineraryId);
		if (results.next()){
			//FIX THIS
		}
		return null;
	}

	@Override
	public String getStartingPoint(String itineraryId) {
		String sqlGetStartPoint = "SELECT "; //SELECT spot 1
		return null;
	
	}
	
	

}
