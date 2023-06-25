package com.mybootapp.main.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;


import com.mybootapp.main.model.Product;
import com.mybootapp.main.model.Vendor;
import com.mybootapp.main.service.ProductService;
import com.mybootapp.main.service.VendorService;

@RestController
public class Productcontroller {
	
	@Autowired//Dependency injection
	private ProductService productservice;
	@Autowired
	private VendorService vendorservice;
	
	@PostMapping("/product/add/{vendorId}")
	public ResponseEntity<?> postring(@PathVariable("vendorId") int vendorId,@RequestBody Product product)
	{
		Vendor vendor=vendorservice.getById(vendorId);
		if(vendor==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		product.setVendor(vendor);
		product=productservice.insert(product);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
    
	@GetMapping("/product/all")
	public List<Product> getAllproducts()
	{
		List<Product> list=productservice.getAllproducts();
		return list;
	} 
	@GetMapping("/product/one/{id}")
	public ResponseEntity<?> getproductid(@PathVariable("id") int id)
	{
		
		Product product=productservice.getproduct(id);
		if(product==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(product);
		
	}
	@PutMapping("/product/update/{id}")
	public ResponseEntity<?> updateproduct(@PathVariable("id") int id,@RequestBody Product newproduct)
	{
	  Product oldproduct=productservice.getproduct(id);
	  if(oldproduct==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newproduct.setId(oldproduct.getId());
	  newproduct=productservice.insert(newproduct);
	  return ResponseEntity.status(HttpStatus.OK).body(newproduct);
	  }
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<String> deleteproduct(@PathVariable("id") int id)
	{
		Product product=productservice.getproduct(id);
		if(product==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		productservice.deleteproduct(product);
		return ResponseEntity.status(HttpStatus.OK).body("product deleted");
	}
	
	
	}
