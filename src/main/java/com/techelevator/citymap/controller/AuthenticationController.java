package com.techelevator.citymap.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.citymap.model.UserDAO;
import com.techelevator.citymap.security.Security;
import com.techelevator.citymap.model.Constants;
import com.techelevator.citymap.model.User;

@Controller
@SessionAttributes("currentUser")
public class AuthenticationController {

	private UserDAO userDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO) {
		this.userDAO = userDAO;
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
			model.put("currentUser", user.getFirstName());
			if(isValidRedirect(destination)) {
				jspPage = "redirect:"+destination;
			} else {
				jspPage = "redirect:/users/"+userName;
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
}
