package com.techelevator.citymap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.citymap.model.Itinerary;
import com.techelevator.citymap.model.ItineraryDAO;
import com.techelevator.citymap.model.Landmark;


@Controller
public class MapController {

	private ItineraryDAO itineraryDAO;

	@Autowired
	public MapController(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}

	@RequestMapping(path = "/map", method = RequestMethod.GET)
	public String showMap(ModelMap model) {
		String start = "Hyde Park, Cincinnati, OH";
		String end = "21C Hotel, Cincinnati, OH";
		String[] waypoints = {"Reds Stadium, Cincinnati, OH", "John Roebling Bridge, Cincinnati, OH"};
		model.put("start", start);
		model.put("end", end);
		model.put("waypts", waypoints);
		return "map";
	}
	
	@RequestMapping(path = "/mapOld", method = RequestMethod.GET)
	public String showOldMap(ModelMap model) {
		return "mapOld";
	}
	
	@RequestMapping(path = "/mapSelector", method = RequestMethod.GET)
	public String showMapSelector(ModelMap model, @RequestParam String itineraryStart, @RequestParam String itineraryName) {
		List<Landmark> ourLandmarks = new ArrayList<>();
		String username = "CINDY";
		model.put("username", username);
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		Itinerary itinerary = itineraryDAO.getItineraryByName(username, itineraryName, itineraryStart);
		//model.put("itinerary", itinerary);
		ourLandmarks = itinerary.getLandmarks();
		//get parameter name and then run sql statement to populate the data 
		
		model.put("waypts", itineraryDAO.getWaypointArray(ourLandmarks)); //this is a theory fingers crossed
		
		// for test: String waypoints = "[{location: \"Reds Stadium, Cincinnati, OH\", stopover: true}, {location: \"John Roebling Bridge, Cincinnati, OH\", stopover: true}, {location: \"HCDC, Cincinnati, OH\", stopover: true}]";
		model.put("start", itineraryStart);
		model.put("end", itineraryStart);
		
		return "mapSelector";
	}
}
