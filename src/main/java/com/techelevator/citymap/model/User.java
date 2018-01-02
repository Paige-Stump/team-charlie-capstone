package com.techelevator.citymap.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// this is a simple JavaBean that holds a userName and a password

public class User {
	private String userName;
	
	@Size(min=10, message="Password too short")
	@Pattern.List({
		@Pattern(regexp=".*[A-Z].*", message="Password needs a capital letter"),
		@Pattern(regexp=".*[a-z].*", message="Password needs a lowercase letter")
	})
	private String password;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
