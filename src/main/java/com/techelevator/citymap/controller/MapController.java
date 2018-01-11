package com.techelevator.citymap.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.techelevator.citymap.model.Constants;
import com.techelevator.citymap.model.Itinerary;
import com.techelevator.citymap.model.ItineraryDAO;
import com.techelevator.citymap.model.Landmark;
import com.techelevator.citymap.model.User;

@Controller
@SessionAttributes(Constants.NAME)
public class MapController {

	private ItineraryDAO itineraryDAO;

	@Autowired
	public MapController(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}
	
	@RequestMapping(path = "/mapSelector", method = RequestMethod.GET)
	public String showMapSelector(ModelMap model, @RequestParam String itineraryStart, @RequestParam String itineraryName, HttpSession session) {
		if(session.getAttribute(Constants.NAME) == null){
			return "redirect:/login";
		}
		List<Landmark> ourLandmarks = new ArrayList<>();
		User user = (User)model.get(Constants.NAME);
		model.put("username", user.getUserName());
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		Itinerary itinerary = itineraryDAO.getItineraryByName(user.getUserName(), itineraryName, itineraryStart);
		ourLandmarks = itinerary.getLandmarks();
		model.put("waypts", itineraryDAO.getWaypointArray(ourLandmarks));  
		model.put("start", itineraryStart);
		model.put("end", itineraryStart);
		return "mapSelector";
	}
}
