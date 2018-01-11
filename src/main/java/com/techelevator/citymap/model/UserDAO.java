package com.techelevator.citymap.model;

public interface UserDAO {

	public boolean saveUser(String userName, String password, String firstName, String lastName);

	//public boolean validatePassword(String userName, String password);

	public void updatePassword(String userName, String password);
	
	public User getUser(String userName);

	public boolean validateUserName(String userName);

}
