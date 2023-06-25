package com.mybootapp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.model.User;
import com.mybootapp.main.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private PasswordEncoder passwordencoder; 
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/add")
	public User add(@RequestBody User user) {
		/*encode the password, set the role, save it in DB */
		user.setPassword(passwordencoder.encode(user.getPassword()));
		user.setRole("ADMIN");
		return userservice.insert(user);
		
	}
}