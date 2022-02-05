package com.it353.registration.service;

import com.it353.registration.entity.Users;
import com.it353.registration.exception.UserAlreadyExistException;

public interface UserService {
	
	
		  public void saveUser (Users user) throws UserAlreadyExistException;
		 // public boolean isUserAlreadyPresent(User user);
		  
		


}
