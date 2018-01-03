package com.techelevator.citymap.model;

public interface UserDAO {

	public boolean saveUser(String userName, String password, String firstName, String lastName);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

}
