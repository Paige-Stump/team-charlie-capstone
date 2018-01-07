package com.techelevator.citymap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.citymap.model.Itinerary;
import com.techelevator.citymap.model.ItineraryDAO;
import com.techelevator.citymap.model.Landmark;
import com.techelevator.citymap.model.User;
import com.techelevator.citymap.model.UserDAO;

@Controller
public class ItineraryController {

	private UserDAO userDao;
	private ItineraryDAO itineraryDAO;

	@Autowired
	public ItineraryController(UserDAO userDAO, ItineraryDAO itineraryDAO) {
		this.userDao = userDAO;
		this.itineraryDAO = itineraryDAO;
	}
	
	@RequestMapping(path="/users/userDash", method=RequestMethod.GET) 
	public String displayDash(ModelMap model) {
		String username = "CINDY";
		model.put("username", username);
		model.put("itineraries", itineraryDAO.getAllItineraries(username));
	
		return "userDash";
	}
	
	@RequestMapping(path="/users/userDash", method=RequestMethod.POST)
	public String clickOnItinerary(ModelMap model){
		return"redirect:/itinerary";
	}
	
	@RequestMapping(path="/itinerary", method=RequestMethod.GET)
	public String displayItinerary(ModelMap model, @RequestParam String itineraryStart, @RequestParam String itineraryName){
		String username = "CINDY";
		model.put("username", username);
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		Itinerary itinerary = itineraryDAO.getItineraryByName(username, itineraryName, itineraryStart);
		model.put("itinerary", itinerary);
		return "itinerary";
	}
}
