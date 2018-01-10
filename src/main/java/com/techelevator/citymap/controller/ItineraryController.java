package com.techelevator.citymap.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import com.techelevator.citymap.model.UserDAO;

@Controller
@SessionAttributes(Constants.NAME)
public class ItineraryController {

	private ItineraryDAO itineraryDAO;
	int searchCount = 0;

	@Autowired
	public ItineraryController(UserDAO userDAO, ItineraryDAO itineraryDAO) {
		this.itineraryDAO = itineraryDAO;
	}
	
	@RequestMapping(path="/itinerary", method=RequestMethod.GET)
	public String displayItinerary(ModelMap model, @RequestParam String itineraryStart, @RequestParam String itineraryName, HttpSession session){
		User user = (User)model.get(Constants.NAME);
		model.put("username", user.getUserName());
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		Itinerary itinerary = itineraryDAO.getItineraryByName(user.getUserName(), itineraryName, itineraryStart);
		model.put("itinerary", itinerary);
		return "itinerary";
	}
	
	@RequestMapping(path="/itinerary", method=RequestMethod.POST)
	public String deleteLandmarkFromItinerary(ModelMap model, @RequestParam String itineraryName, @RequestParam String landmarkId, HttpSession session) {
		User user = (User)model.get(Constants.NAME);
		model.put("username", user.getUserName());
		model.put("itineraryName", itineraryName);
		model.put("landmarkId", landmarkId);
		itineraryDAO.removeLandmarkFromItinerary(itineraryName, user.getUserName(), landmarkId);
		return "redirect:/users/userDash";
	}
	
	@RequestMapping(path="/itineraryDelete", method=RequestMethod.GET)
	public String confirmItineraryDelete(ModelMap model, @RequestParam(required=false) String itineraryStart, @RequestParam String itineraryDeleteButton, HttpSession session) {
		User user = (User)model.get(Constants.NAME);
		model.put("userName", user.getUserName());
		model.put("itineraryName", itineraryDeleteButton);
		model.put("itineraryStart", itineraryStart);
		return "itineraryDelete";
	}
	
	@RequestMapping(path="/itineraryDelete", method=RequestMethod.POST)
	public String deleteItineraryForUser(ModelMap model, @RequestParam (required=false) String itineraryStart, @RequestParam (required=false) String itineraryName, HttpSession session) {
		User user = (User)model.get(Constants.NAME);
		model.put("userName", user.getUserName());
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		itineraryDAO.deleteItinerary(itineraryName, user.getUserName());
		return "redirect:/users/userDash";
	}
	
	@RequestMapping(path= "/addLandmarks", method = RequestMethod.POST)
	public String addLandmarksToExistingItinerary(ModelMap model, @RequestParam String itineraryName, @RequestParam String itineraryStart, @RequestParam (required = false) String changeStartingPoint, HttpServletRequest request, HttpSession session) {
		User user = (User)model.get(Constants.NAME);
		String username = user.getUserName();
		model.put("username", username);
		model.put("itineraryName", itineraryName);
		int landmarksSelected = 0;
		List<String> landmarkIdsList = new ArrayList<>();
		if(request.getParameterValues("landmarkId") != null){
			String[] landmarkIds = request.getParameterValues("landmarkId");
			for(String id: landmarkIds){
				landmarkIdsList.add(id);
			}
			landmarksSelected =1;
		}
		if(landmarksSelected == 0 && changeStartingPoint.length() > 0){
			itineraryDAO.updateItineraryStartingPoint(changeStartingPoint, itineraryName, itineraryStart, username);
			return "redirect:/users/userDash";
		}
		if(landmarksSelected == 1) {
			List<Landmark> landmarks = new ArrayList<>();
			for(int i = 0; i < landmarkIdsList.size(); i++) {
				Landmark landmark = new Landmark();
				landmark = itineraryDAO.getLandmarkById(landmarkIdsList.get(i));
				landmarks.add(landmark);
			}
			if(changeStartingPoint.length() > 0){
				model.put("itineraryStart", changeStartingPoint);
				for(Landmark landmark: landmarks){
					itineraryDAO.updateItineraryStartingPoint(changeStartingPoint, itineraryName, itineraryStart, username);
					itineraryDAO.addLandmarkToItinerary(itineraryName, changeStartingPoint, username, landmark);
				}
			}
			else{
				model.put("itineraryStart", itineraryStart);
				for(Landmark landmark: landmarks){		
					itineraryDAO.addLandmarkToItinerary(itineraryName, itineraryStart, username, landmark);
				}
			}
		}
		return "redirect:/users/userDash";
	}
	
	@RequestMapping(path="/landmarks", method=RequestMethod.POST)
	public String createItinerary(ModelMap model, @RequestParam (required=false) String startingPoint, @RequestParam (required=false) String itineraryName, 
			HttpServletRequest request, HttpSession session) {
		
		User user = (User)model.get(Constants.NAME);
		String username = user.getUserName();
		model.put("username", username);
		List<Landmark> landmarks = new ArrayList<>();
		String [] landmarkIds = request.getParameterValues("landmarkId");
			if("landmarkId" != null && landmarkIds.length > 0) {
			for(int i = 0; i < landmarkIds.length; i++) {
				Landmark landmark = new Landmark();
				landmark = itineraryDAO.getLandmarkById(landmarkIds[i]);
				landmarks.add(landmark);
			}
		}
		model.put("itineraryName", itineraryName);
		model.put("startingPoint", startingPoint);
		itineraryDAO.createNewItinerary(itineraryName, startingPoint, user.getUserName(), landmarks);
		return "redirect:/users/userDash";
	}
	
	@RequestMapping(path="/searchLandmarks", method=RequestMethod.GET)
	public String showSearchForLandmarks(ModelMap model, HttpSession session) {
		model.put("landmarks", itineraryDAO.getAllLandmarks());
		return "searchLandmarks";
	}
	
	@RequestMapping(path="/searchLandmarks", method=RequestMethod.POST)
	public String showMatchingLandmarks(ModelMap model, @RequestParam String searchForLandmark, HttpSession session) {
		User user = (User)model.get(Constants.NAME);
		String username = user.getUserName();
		model.put("username", username);
		List<Landmark> landmarks = new ArrayList<>();
		landmarks = itineraryDAO.searchForLandmarks(searchForLandmark);
		model.put("landmarks", landmarks);
		return "searchLandmarks";
	}
	
	@RequestMapping(path="/landmarks", method=RequestMethod.GET)
	public String showAllLandmarks(ModelMap model, HttpSession session) {
		model.put("landmarks", itineraryDAO.getAllLandmarks());
		return "landmarks";
	}
	
	@RequestMapping(path= "/addLandmarks", method = RequestMethod.GET)
	public String showAddLandmarks(ModelMap model, @RequestParam String itineraryName, @RequestParam String itineraryStart, HttpSession session) {
		User user = (User)model.get(Constants.NAME);
		String username = user.getUserName();
		List<Landmark> ourLandmarks = new ArrayList<>();
		ourLandmarks = itineraryDAO.getLandmarksNotInItinerary(username, itineraryName);
		model.put("username", username);
		model.put("itineraryName", itineraryName);
		model.put("itineraryStart", itineraryStart);
		model.put("landmarks",  ourLandmarks);
		return "addLandmarks";
	}
}
