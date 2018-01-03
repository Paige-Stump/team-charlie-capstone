package com.techelevator.citymap.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.citymap.model.LandmarkDAO;

// import com.techelevator.citymap.model.MessageDAO;

@Controller
public class HomeController {
	
private LandmarkDAO landmarkDAO;

@Autowired
public HomeController(LandmarkDAO landmarkDAO) {
		this.landmarkDAO = landmarkDAO;
	}
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String showHomePage(ModelMap model) {
	model.put("landmarks", landmarkDAO.getFeaturedLandmarks());
	return "home";
	}
}
