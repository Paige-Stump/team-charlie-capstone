	package com.techelevator.citymap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.techelevator.citymap.model.UserDAO;
import com.techelevator.citymap.security.Security;
import com.techelevator.citymap.model.Constants;
import com.techelevator.citymap.model.ItineraryDAO;
import com.techelevator.citymap.model.Landmark;
import com.techelevator.citymap.model.User;

@Controller
@SessionAttributes(Constants.NAME)
public class AuthenticationController {

	private UserDAO userDAO;
	private ItineraryDAO itineraryDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO, ItineraryDAO itineraryDAO) {
		this.userDAO = userDAO;
		this.itineraryDAO = itineraryDAO;
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(ModelMap model, 
						@RequestParam String userName, 
						@RequestParam String password,
						@RequestParam(required=false) String destination,
						HttpSession session) {
		
		String jspPage = "";
		User user = userDAO.getUser(userName);
		if(Security.IsUserValid(user, password)) {
			session.setAttribute(Constants.NAME, user);
			model.put(Constants.NAME, user);	
			if(isValidRedirect(destination)) {
				jspPage = "redirect:"+destination;
			} else {
				jspPage = "redirect:/users/userDash"; 
			}
		} else {
			model.put("error", "Invalid password");
			jspPage = "login";
		}
		return jspPage;
	}

	private boolean isValidRedirect(String destination) {
		return destination != null && destination.startsWith("http://localhost");
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(Map<String, Object> model, HttpSession session) {
		model.remove(Constants.NAME);
		session.removeAttribute(Constants.NAME);
		return "redirect:/";
	}
	
	@RequestMapping(path="/users/userDash", method=RequestMethod.GET) 
	public String displayDash(ModelMap model, HttpSession session) {
		
		User user = (User)model.get(Constants.NAME);
		session.getAttribute(Constants.NAME);
		model.put("username", user.getUserName());
		model.put("itineraries", itineraryDAO.getAllItineraries(user.getUserName()));
	
		return "userDash";
	}
	
	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm() {
		return "newUser";
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User newUser, ModelMap model) { 
		String jspPage = "redirect:/login";
		if(!userDAO.saveUser(newUser.getUserName(), newUser.getPassword(), newUser.getFirstName(), newUser.getLastName())){
			model.put("error", "User Name already exists. Please try another one.");
			jspPage = "newUser";
		}
		return jspPage;
	}

	@RequestMapping(path="/adminCreateLandmark", method=RequestMethod.GET)
	public String displayAdminCreateLandmark() {
		return "adminCreateLandmark";
	}
	
	@RequestMapping(path="/adminCreateLandmark", method=RequestMethod.POST)
	public String adminCreateNewLandmark(ModelMap model, @RequestParam String landmarkTitle, @RequestParam String landmarkDescription, @RequestParam String landmarkLocation, @RequestParam String landmarkCityState, @RequestParam String landmarkHref) {
		itineraryDAO.adminCreateNewLandmark(landmarkTitle, landmarkDescription, landmarkLocation, landmarkCityState, landmarkHref);
		return "redirect:/users/userDash";
	}
}
