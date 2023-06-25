package com.mybootapp.main.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mybootapp.main.model.Manager;

import com.mybootapp.main.model.User;
import com.mybootapp.main.service.ManagerService;
import com.mybootapp.main.service.UserService;


@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerservice;
	@Autowired
	private PasswordEncoder passwordencoder;
	@Autowired
	private UserService userservice;
	@PostMapping("/add")
	
	
	public Manager managerposting(@RequestBody Manager manager)
	{
		
		User user=manager.getUser();
		user.setPassword(passwordencoder.encode(user.getPassword()));
		user=userservice.insert(user);
		manager.setUser(user);
		return managerservice.insert(manager);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatemanager(@PathVariable("id") int id,@RequestBody Manager newmanager)
	{
	  Manager oldmanager=managerservice.getmanager(id);
	  if(oldmanager==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newmanager.setId(oldmanager.getId());
	  newmanager=managerservice.insert(newmanager);
	  return ResponseEntity.status(HttpStatus.OK).body(newmanager);
	  }
	@GetMapping("/all")
	public List<Manager> getAllmanagers()
	{
		List<Manager> list=managerservice.getAllmanagers();
		return list;
	} 
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getmanagerid(@PathVariable("id") int id)
	{
		
		Manager manager=managerservice.getmanager(id);
		if(manager==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(manager);
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletemanager(@PathVariable("id") int id)
	{
		Manager manager=managerservice.getmanager(id);
		if(manager==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		managerservice.deletemanager(manager);
		return ResponseEntity.status(HttpStatus.OK).body("manager deleted");
	}
	

}
