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

import com.mybootapp.main.model.OutwardRegister;
import com.mybootapp.main.model.Returns;

import com.mybootapp.main.service.OutwardRegisterService;
import com.mybootapp.main.service.ReturnService;


@RestController
@RequestMapping("/returns")
public class ReturnController {
	@Autowired
	private ReturnService returnservice;
	@Autowired
	private OutwardRegisterService outwardregisterservice;
	@PostMapping("/add/{outwardregisterId}")
	public  ResponseEntity<?> postreturn(@RequestBody Returns returns,@PathVariable("outwardregisterId") int outwardregisterId)
	{OutwardRegister outwardregister=outwardregisterservice.getById(outwardregisterId);
	if(outwardregister==null)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
	}
	returns.setOutwardRegister(outwardregister);
	returns=returnservice.insert(returns);
	return ResponseEntity.status(HttpStatus.OK).body(returns);
		
		
	}
	@GetMapping("/all")
	public List<Returns> getAllreturns()
	{
		List<Returns> list=returnservice.getAllreturns();
		return list;
	} 
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getreturnid(@PathVariable("id") int id)
	{
		
		Returns returns=returnservice.getreturn(id);
		if(returns==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(returns);
		
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatereturn(@PathVariable("id") int id,@RequestBody Returns newreturn)
	{
	  Returns oldreturn=returnservice.getreturn(id);
	  if(oldreturn==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newreturn.setId(oldreturn.getId());
	  newreturn=returnservice.insert(newreturn);
	  return ResponseEntity.status(HttpStatus.OK).body(newreturn);
	  }
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletereturn(@PathVariable("id") int id)
	{
		Returns returns=returnservice.getreturn(id);
		if(returns==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		returnservice.deletereturn(returns);
		return ResponseEntity.status(HttpStatus.OK).body("return deleted");
	}
}
