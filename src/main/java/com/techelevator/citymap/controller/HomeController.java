package com.techelevator.citymap.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.citymap.model.ItineraryDAO;


// import com.techelevator.citymap.model.MessageDAO;

@Controller
public class HomeController {
	
private ItineraryDAO itineraryDAO;

@Autowired
public HomeController(ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String showHomePage(ModelMap model) {
	model.put("landmarks", itineraryDAO.getFeaturedLandmarks());
	return "home";
	}
	
	@RequestMapping(path="/landmarks", method=RequestMethod.GET)
	public String showAllLandmarks(ModelMap model) {
		model.put("landmarks", itineraryDAO.getAllLandmarks());
		return "landmarks";
	}
}
