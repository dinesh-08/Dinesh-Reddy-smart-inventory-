package com.mybootapp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.model.Customer;

import com.mybootapp.main.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	
	@PostMapping("/add")
	public Customer customerposting(@RequestBody Customer customer)
	{
		return customerservice.insert(customer);
	}
	@GetMapping("/all")
	public List<Customer> getAllcustomers()
	{
		List<Customer> list=customerservice.getAllcustomers();
		return list;
	} 
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getcustomerid(@PathVariable("id") int id)
	{
		
		Customer customer=customerservice.getcustomer(id);
		if(customer==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(customer);
		
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatecustomer(@PathVariable("id") int id,@RequestBody Customer newcustomer)
	{
	  Customer oldcustomer=customerservice.getcustomer(id);
	  if(oldcustomer==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newcustomer.setId(oldcustomer.getId());
	  newcustomer=customerservice.insert(newcustomer);
	  return ResponseEntity.status(HttpStatus.OK).body(newcustomer);
	  }
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletecustomer(@PathVariable("id") int id)
	{
		Customer customer=customerservice.getcustomer(id);
		if(customer==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		customerservice.deletecustomer(customer);
		return ResponseEntity.status(HttpStatus.OK).body("customer deleted");
	}

}
