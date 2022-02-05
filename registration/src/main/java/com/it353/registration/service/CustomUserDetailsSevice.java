package com.it353.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.it353.registration.entity.Users;
import com.it353.registration.model.CustomUserDetails;
import com.it353.registration.repository.UserRepository;

public class CustomUserDetailsSevice implements UserDetailsService {
	 
    @Autowired
    private UserRepository userRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Users user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
 
}

	


