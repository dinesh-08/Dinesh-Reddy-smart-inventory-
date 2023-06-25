package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Employee;
import com.mybootapp.main.model.Product;
import com.mybootapp.main.repository.EmployeeRepository;



@Service

public class EmployeeService {

	
	@Autowired
	private EmployeeRepository employeerepository;
	public Employee insert(Employee employee) {
		// TODO Auto-generated method stub
	
	 return employeerepository.save(employee);
	}
	public Employee getemployee(int id) {
		// TODO Auto-generated method stub
		Optional<Employee> optional=employeerepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}
	public void deleteemployee(Employee employee) {
		// TODO Auto-generated method stub
		employeerepository.delete(employee);
		
	}
	public List<Employee> getAllemployees() {
		// TODO Auto-generated method stub
		return employeerepository.findAll();
	}

}
