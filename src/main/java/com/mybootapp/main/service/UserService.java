package com.mybootapp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.User;
import com.mybootapp.main.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userrepository;
	public User insert(User user) {
		
		
		return userrepository.save(user);
	}
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		// TODO Auto-generated method stub
		User user  = userrepository.getUserByUsername(username);
		
		return (UserDetails) user;
	}

}
