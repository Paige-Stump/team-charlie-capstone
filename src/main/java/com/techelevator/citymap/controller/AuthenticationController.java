package com.techelevator.citymap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.citymap.model.UserDAO;
import com.techelevator.citymap.security.Security;

import com.techelevator.citymap.model.Constants;
import com.techelevator.citymap.model.Itinerary;
import com.techelevator.citymap.model.ItineraryDAO;
import com.techelevator.citymap.model.Landmark;
import com.techelevator.citymap.model.User;

@Controller
@SessionAttributes("currentUser")
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
	public String login(Map<String, Object> model, 
						@RequestParam String userName, 
						@RequestParam String password,
						@RequestParam(required=false) String destination,
						HttpSession session) {
		
		String jspPage = "";
		User user = userDAO.getUser(userName);
		
		if(Security.IsUserValid(user, password)) {
			/*session.invalidate();*/
			session.setAttribute(Constants.NAME, user);
			model.put("currentUser", user);
			if(isValidRedirect(destination)) {
				jspPage = "redirect:"+destination;
			} else {
				jspPage = "redirect:/users/userDash"; //+userName;
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
		model.remove("currentUser");
		session.removeAttribute("currentUser");
		return "redirect:/";
	}
	
	
	@RequestMapping(path="/users/userDash", method=RequestMethod.GET) 
	public String displayDash(ModelMap model) {
		User user = (User)model.get("currentUser");
		model.put("username", user.getUserName());
		model.put("itineraries", itineraryDAO.getAllItineraries(user.getUserName()));
	
		return "userDash";
	}
	
	@RequestMapping(path="/users/userDash", method=RequestMethod.POST)
	public String clickOnItinerary(ModelMap model){
		return"redirect:/itinerary";
	}
	
	@RequestMapping(path="/itinerary", method=RequestMethod.GET)
	public String displayItinerary(ModelMap model, @RequestParam String itineraryStart, @RequestParam String itineraryName){
		User user = (User)model.get("currentUser");
		model.put("username", user.getUserName());
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		Itinerary itinerary = itineraryDAO.getItineraryByName(user.getUserName(), itineraryName, itineraryStart);
		model.put("itinerary", itinerary);
		return "itinerary";
	}
	
	@RequestMapping(path="/itinerary", method=RequestMethod.POST)
	public String deleteFromItinerary(ModelMap model, @RequestParam String itineraryName, @RequestParam (required=false) String landmarkId) {
		User user = (User)model.get("currentUser");
		model.put("userName", user.getUserName());
		model.put("itineraryName", itineraryName);
		model.put("landmarkId", landmarkId);
		System.out.println(itineraryName);
		System.out.println(landmarkId);
		System.out.println(user.getUserName());
		itineraryDAO.removeLandmarkFromItinerary(itineraryName, user.getUserName(), landmarkId);
		return "redirect:/users/userDash";
	}
	
	@RequestMapping(path="/landmarks", method=RequestMethod.GET)
	public String showAllLandmarks(ModelMap model) {
		model.put("landmarks", itineraryDAO.getAllLandmarks());
		return "landmarks";
	}
	
	@RequestMapping(path="/landmarks", method=RequestMethod.POST)
	public String createItinerary(ModelMap model, @RequestParam String startingPoint, @RequestParam String itineraryName, @RequestParam (required=false) String checked1) {
		
		User user = (User)model.get("currentUser");
		if(checked1 != null) {
			
			Landmark landmark = new Landmark();
			List<Landmark> landmarks = new ArrayList<>();
			landmark = itineraryDAO.getLandmarkById(checked1);
			landmarks.add(landmark);
			
			itineraryDAO.createNewItinerary(itineraryName, startingPoint, user.getUserName(), landmarks);
			System.out.println(landmark.getLandmarkName());
			System.out.println(checked1);
		}
		

		model.put("username", user.getUserName());
		model.put("itineraries", itineraryDAO.getAllItineraries(user.getUserName()));
		return "redirect:/users/userDash";
	}
}
