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


import com.mybootapp.main.model.Vendor;
import com.mybootapp.main.service.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
@Autowired
private VendorService vendorservice;

@PostMapping("/add")
public Vendor addvendor(@RequestBody Vendor vendor)
{
	vendor=vendorservice.insert(vendor);
	return vendor;
}

@GetMapping("/all")
public List<Vendor> getAllvendors()
{
	List<Vendor> list=vendorservice.getAllvendors();
	return list;
} 
@GetMapping("/one/{id}")
public ResponseEntity<?> getvendorid(@PathVariable("id") int id)
{
	
	Vendor vendor=vendorservice.getvendor(id);
	if(vendor==null)
	{
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
	}
	return ResponseEntity.status(HttpStatus.OK).body(vendor);
	
}
@PutMapping("/update/{id}")
public ResponseEntity<?> updatevendor(@PathVariable("id") int id,@RequestBody Vendor newvendor)
{
  Vendor oldvendor=vendorservice.getvendor(id);
  if(oldvendor==null)
  {
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
	  
  }
  newvendor.setId(oldvendor.getId());
  newvendor=vendorservice.insert(newvendor);
  return ResponseEntity.status(HttpStatus.OK).body(newvendor);
  }
@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deletevendor(@PathVariable("id") int id)
{
	Vendor vendor=vendorservice.getvendor(id);
	if(vendor==null)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
	}
	vendorservice.deletevendor(vendor);
	return ResponseEntity.status(HttpStatus.OK).body("vendor deleted");
}

}
