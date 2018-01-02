package com.techelevator;

import org.postgresql.util.Base64;

import com.techelevator.model.User;

public class Security {

	public static boolean IsUserValid(User user, String password) {
		
		PasswordHasher hasher = new PasswordHasher();		
		String hash = hasher.computeHash(password, Base64.decode(user.getSalt()));
		
		return hash.compareTo(user.getPassword()) == 0;
	}
}
