package com.techelevator.citymap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.citymap.model.LandmarkDAO;
import com.techelevator.citymap.model.MapMarkerDAO;

@Controller
public class MapController {

	private MapMarkerDAO mapMarkerDAO;

	@Autowired
	public MapController(MapMarkerDAO mapMarkerDAO) {
		this.mapMarkerDAO = mapMarkerDAO;
	}

	@RequestMapping(path = "/map", method = RequestMethod.GET)
	public String showMap(ModelMap model) {
		return "map";
	}
	
	@RequestMapping(path = "/mapOld", method = RequestMethod.GET)
	public String showOldMap(ModelMap model) {
		return "mapOld";
	}
	
	@RequestMapping(path = "/mapSelector", method = RequestMethod.GET)
	public String showMapSelector(ModelMap model) {
		return "mapSelector";
	}

}
