package com.techelevator.citymap.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.citymap.model.Constants;
import com.techelevator.citymap.model.ItineraryDAO;
import com.techelevator.citymap.model.UserDAO;

@Controller
@SessionAttributes(Constants.NAME)
public class UserController {

	private UserDAO userDAO;
	private ItineraryDAO itineraryDAO;

	@Autowired
	public UserController(UserDAO userDAO, ItineraryDAO itineraryDAO){
		this.userDAO = userDAO;
		this.itineraryDAO = itineraryDAO;
	}
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String showHomePage(ModelMap model) {
	model.put("landmarks", itineraryDAO.getFeaturedLandmarks());
	return "home";
	}
	
	
	@RequestMapping(path="/users/{userName}/changePassword", method=RequestMethod.GET)
	public String displayChangePasswordForm(ModelMap model, @PathVariable String userName, HttpSession session) {
		if(session.getAttribute(Constants.NAME) == null){
			return "redirect:/login";
		}
		model.put("userName", userName);
		return "changePassword";
	}
	
	@RequestMapping(path="/users/{userName}/changePassword", method=RequestMethod.POST)
	public String changePassword(@PathVariable String userName, @RequestParam String password) {
		userDAO.updatePassword(userName, password);
		return "userDash";
	}
}
