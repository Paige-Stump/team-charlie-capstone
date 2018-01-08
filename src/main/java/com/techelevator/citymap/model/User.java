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
	private String firstName;
	private String lastName;
	private String salt;
	private boolean admin;

	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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
