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

import com.mybootapp.main.model.Employee;
import com.mybootapp.main.model.Manager;

import com.mybootapp.main.model.User;
import com.mybootapp.main.service.EmployeeService;
import com.mybootapp.main.service.ManagerService;
import com.mybootapp.main.service.UserService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

   @Autowired
	private PasswordEncoder encoder; 
	
	@Autowired
	private UserService userservice; 
	
	@Autowired
	private EmployeeService employeeService; 
	
	@Autowired
	private ManagerService managerService; 
	
	@PostMapping("/add/{managerId}")
	public ResponseEntity<?> addEmployee(@PathVariable("managerId") int managerId, 
			@RequestBody Employee employee) {
		/* validate managerId and fetch manager obj from DB */
		Manager manager  = managerService.getById(managerId);
		if(manager == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Manager ID invalid");
		
	
		employee.setManager(manager);
		
		
		User user = employee.getUser();
		
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		/* Set the role: EMPLOYEE */
		user.setRole("EMPLOYEE");
		user  = userservice.insert(user);
		employee.setUser(user);
		employee =  employeeService.insert(employee);
		return ResponseEntity.status(HttpStatus.OK)
				.body(employee);
	}
	@GetMapping("/all")
	public List<Employee> getAllemployees()
	{
		List<Employee> list=employeeService.getAllemployees();
		return list;
	} 
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getemployeeid(@PathVariable("id") int id)
	{
		
		Employee employee=employeeService.getemployee(id);
		if(employee==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(employee);
		
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateemployee(@PathVariable("id") int id,@RequestBody Employee newemployee)
	{
	  Employee oldemployee=employeeService.getemployee(id);
	  if(oldemployee==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newemployee.setId(oldemployee.getId());
	  newemployee=employeeService.insert(newemployee);
	  return ResponseEntity.status(HttpStatus.OK).body(newemployee);
	  }
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteemployee(@PathVariable("id") int id)
	{
		Employee employee=employeeService.getemployee(id);
		if(employee==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		employeeService.deleteemployee(employee);
		return ResponseEntity.status(HttpStatus.OK).body("employee deleted");
	}
}
