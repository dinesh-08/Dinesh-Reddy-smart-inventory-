package com.mybootapp.main.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice; 
	
	@GetMapping("/login")
	public UserDetails login(Principal principal) { 
		String username = principal.getName();
		UserDetails user = userservice.loadUserByUsername(username);
		return user; 
	}
}