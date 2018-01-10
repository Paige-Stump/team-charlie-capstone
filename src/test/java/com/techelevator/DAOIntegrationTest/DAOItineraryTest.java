package com.techelevator.DAOIntegrationTest;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.citymap.model.Itinerary;
import com.techelevator.citymap.model.JDBCItineraryDAO;
import com.techelevator.citymap.model.JDBCUserDAO;
import com.techelevator.citymap.model.Landmark;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DAOItineraryTest extends DAOIntegrationTest {

    private JDBCItineraryDAO itineraryDAO;
    private JDBCUserDAO userDAO;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        itineraryDAO = new JDBCItineraryDAO(dataSource);
    }

    /*@Test
    public void does_it_get_all_itineraries() {
    	ArrayList<Itinerary> ourItineraries = (ArrayList<Itinerary>) itineraryDAO.getAllItineraries("CINDY");
    	assertFalse(ourItineraries.isEmpty());
    	assertEquals (2, ourItineraries.size());
    	
    	Itinerary itinerary1 = ourItineraries.get(0);
    	assertEquals (itinerary1.getItineraryName(), "");
    	assertEquals(itinerary1.getStartingPoint(), "");
    	
    	Itinerary itinerary2 = ourItineraries.get(1);
    	assertEquals (itinerary2.getItineraryName(), "");
    	assertEquals(itinerary2.getStartingPoint(), "");
    	
    }*/
    
    @Test
    public void does_it_get_landmarks_by_id() {
    	Landmark greatAmerican = itineraryDAO.getLandmarkById("1");
    	assertEquals("ChIJLwacWEOxQYgRcu3UJQuLYwc", greatAmerican.getLandmarkLocation());
    }
    
    @Test
    public void does_it_get_featured_landmarks() {
    	List<Landmark> featuredLandmarks = itineraryDAO.getFeaturedLandmarks();
    	List<String> landmarkNames = new ArrayList<String>();
    	for (Landmark landmark : featuredLandmarks) {
    		landmarkNames.add(landmark.getLandmarkName());
    		System.out.println(landmark.getLandmarkName());
    	}
    	
    	assertEquals(featuredLandmarks.size(), 3);
    	assertEquals(landmarkNames.size(), 3);
    	
    }
    
    

}
