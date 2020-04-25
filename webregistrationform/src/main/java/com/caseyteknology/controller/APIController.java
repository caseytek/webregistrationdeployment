package com.caseyteknology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caseyteknology.model.User;
import com.caseyteknology.model.UserDAO;

@RestController
@CrossOrigin   
public class APIController {
	
	@Autowired
    private UserDAO userDao;

	@PostMapping("/newUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
    	userDao.createNewUser(user);
    }


}
