package com.it353.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.it353.registration.entity.Users;
import com.it353.registration.exception.UserAlreadyExistException;
import com.it353.registration.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
    @Autowired
    private UserRepository userRepo;


	@Override
    public void saveUser(Users user) throws UserAlreadyExistException {
        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
              + user.getEmail());
        }

BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
String encodedPassword = passwordEncoder.encode(user.getPassword());
user.setPassword(encodedPassword);

        userRepo.save(user);
    }

    private boolean emailExists(String email) {
        return userRepo.findByEmail(email) != null;
    }
}



