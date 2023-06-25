package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Customer;
import com.mybootapp.main.model.Supplier;
import com.mybootapp.main.repository.CustomerRepository;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerRepository customerrepository;
	public Customer insert(Customer customer) {
		// TODO Auto-generated method stub
		return customerrepository.save(customer);
	}
	public Customer getById(int customerId) {
		Optional<Customer> optional=customerrepository.findById(customerId);
		if(!optional.isPresent())
		{
			return null;
		}
       return optional.get();
		
	}
	public List<Customer> getAllcustomers() {
		// TODO Auto-generated method stub
		return customerrepository.findAll();
	}
	public Customer getcustomer(int id) {
		// TODO Auto-generated method stub
		Optional<Customer> optional=customerrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}
	public void deletecustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerrepository.delete(customer);
		
	}

}
