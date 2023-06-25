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

import com.mybootapp.main.model.Supplier;

import com.mybootapp.main.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierservice;
	
	@PostMapping("/add")
	public  Supplier postSupplier(@RequestBody Supplier supplier)
	{
		return supplierservice.insert(supplier);
	}
	@GetMapping("/all")
	public List<Supplier> getAllsuppliers()
	{
		List<Supplier> list=supplierservice.getAllsuppliers();
		return list;
	} 
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getsupplierid(@PathVariable("id") int id)
	{
		
		Supplier supplier=supplierservice.getsupplier(id);
		if(supplier==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(supplier);
		
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatesupplier(@PathVariable("id") int id,@RequestBody Supplier newsupplier)
	{
	  Supplier oldsupplier=supplierservice.getsupplier(id);
	  if(oldsupplier==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newsupplier.setId(oldsupplier.getId());
	  newsupplier=supplierservice.insert(newsupplier);
	  return ResponseEntity.status(HttpStatus.OK).body(newsupplier);
	  }
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletesupplier(@PathVariable("id") int id)
	{
		Supplier supplier=supplierservice.getsupplier(id);
		if(supplier==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		supplierservice.deletesupplier(supplier);
		return ResponseEntity.status(HttpStatus.OK).body("supplier deleted");
	}

}
