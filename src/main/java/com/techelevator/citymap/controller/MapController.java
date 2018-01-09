package com.techelevator.citymap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.citymap.model.Itinerary;
import com.techelevator.citymap.model.ItineraryDAO;
import com.techelevator.citymap.model.Landmark;
import com.techelevator.citymap.model.User;


@Controller
@SessionAttributes("currentUser")
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
	
	/*@RequestMapping(path = "/mapSelector", method = RequestMethod.GET)
	public String showMapSelector(ModelMap model, @RequestParam String itineraryStart, @RequestParam String itineraryName) {
		List<Landmark> ourLandmarks = new ArrayList<>();
		User user = new User();
		String username = user.getUserName();
		model.put("username", username);
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		Itinerary itinerary = itineraryDAO.getItineraryByName(username, itineraryName, itineraryStart);
		ourLandmarks = itinerary.getLandmarks();
		model.put("waypts", itineraryDAO.getWaypointArray(ourLandmarks));  
		model.put("start", itineraryStart);
		model.put("end", itineraryStart);
		System.out.println(username);
		System.out.println(itineraryName);
		System.out.println(itineraryStart);
		System.out.println(itinerary.getStartingPoint());
		System.out.println(itineraryDAO.getWaypointArray(ourLandmarks));
				
		
		return "mapSelector";
	}*/
	
	@RequestMapping(path= "/addLandmarks", method = RequestMethod.GET)
	public String showAddLandmarks(ModelMap model, @RequestParam String itineraryStart, @RequestParam String itineraryName) {
		List<Landmark> ourLandmarks = new ArrayList<>();
		User user = new User();
		String username = user.getUserName();
		model.put("username", username);
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		
		
		return "addLandmarks";
	}
}
