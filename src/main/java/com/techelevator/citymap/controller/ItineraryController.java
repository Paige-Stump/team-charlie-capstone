package com.techelevator.citymap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.citymap.model.Itinerary;
import com.techelevator.citymap.model.ItineraryDAO;
import com.techelevator.citymap.model.UserDAO;

@Controller
@SessionAttributes("currentUser")
public class ItineraryController {

	private UserDAO userDao;
	private ItineraryDAO itineraryDAO;

	@Autowired
	public ItineraryController(UserDAO userDAO, ItineraryDAO itineraryDAO) {
		this.userDao = userDAO;
		this.itineraryDAO = itineraryDAO;
	}
	
	/*@RequestMapping(path="/users/userDash", method=RequestMethod.GET) 
	public String displayDash(ModelMap model) {
		String username = "PAIGE";
		model.put("username", username);
		model.put("itineraries", itineraryDAO.getAllItineraries(username));
	
		return "userDash";
	} */
	
	
}
