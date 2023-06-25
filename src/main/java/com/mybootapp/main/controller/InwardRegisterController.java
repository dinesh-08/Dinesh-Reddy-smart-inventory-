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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybootapp.main.dto.InwardRegisterDto;

import com.mybootapp.main.model.Godown;
import com.mybootapp.main.model.InwardRegister;
import com.mybootapp.main.model.Product;
import com.mybootapp.main.model.Supplier;

import com.mybootapp.main.service.GodownService;
import com.mybootapp.main.service.InwardRegisterService;
import com.mybootapp.main.service.ProductService;
import com.mybootapp.main.service.SupplierService;

@RestController
@RequestMapping("/inwardregister")
public class InwardRegisterController {
	
 @Autowired
  private ProductService productservice;
 @Autowired
  private InwardRegisterService inwardregisterservice;
 @Autowired
 private SupplierService supplierservice;
 @Autowired
 private GodownService godownservice;
 
 @PostMapping("/add/{productId}/{godownId}/{supplierId}")
 public ResponseEntity<?> InwardPosting(@RequestBody InwardRegister inwardregister,
		                    @PathVariable("productId") int productId,
		                    @PathVariable("godownId") int godownId,
		                    @PathVariable("supplierId") int supplierId
		                    )
 {
	 
	 Product product=productservice.getById(productId);
	 if(product==null)
	 {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
	 }
	 Godown godown=godownservice.getById(godownId);
	 if(godown==null)
	 {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
	 }
	 Supplier supplier=supplierservice.getById(supplierId);
	 if(supplier==null)
	 {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
	 }
	 inwardregister.setGodown(godown);
	 inwardregister.setProduct(product);
	 inwardregister.setSupplier(supplier);
	 
	 inwardregister=inwardregisterservice.insert(inwardregister);
	 return ResponseEntity.status(HttpStatus.OK).body(inwardregister);
}
 @GetMapping("/all")
	public List<InwardRegister> getAllinwardregisters()
	{
		List<InwardRegister> list=inwardregisterservice.getAllinwardregisters();
		return list;
	} 
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getinwardregisterid(@PathVariable("id") int id)
	{
		
		InwardRegister inwardregister=inwardregisterservice.getinwardregister(id);
		if(inwardregister==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(inwardregister);
		
	}

	@PutMapping("/update/{productId}/{godownId}/{supplierId}/{id}")
	public ResponseEntity<?> updateinwardregister(@PathVariable("id") int id,
			@PathVariable("productId") int productId,
			@PathVariable("godownId") int godownId,
			@PathVariable("supplierId") int supplierId,
			
 			@RequestBody InwardRegister newinwardregister)
	{
		 Product product=productservice.getById(productId);
		 if(product==null)
		 {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		 }
		 Godown godown=godownservice.getById(godownId);
		 if(godown==null)
		 {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		 }
		 Supplier supplier=supplierservice.getById(supplierId);
		 if(supplier==null)
		 {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		 }
		
	  InwardRegister oldinwardregister=inwardregisterservice.getinwardregister(id);
	  if(oldinwardregister==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newinwardregister.setGodown(godown);
		 newinwardregister.setProduct(product);
		 newinwardregister.setSupplier(supplier);
	  newinwardregister.setId(oldinwardregister.getId());
	  
	  newinwardregister=inwardregisterservice.insert(newinwardregister);
	  return ResponseEntity.status(HttpStatus.OK).body(newinwardregister);
	 }
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteinwardregister(@PathVariable("id") int id)
	{
		InwardRegister inwardregister=inwardregisterservice.getinwardregister(id);
		if(inwardregister==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		inwardregisterservice.deleteinwardregister(inwardregister);
		return ResponseEntity.status(HttpStatus.OK).body("inwardregister deleted");
	}
	
	//Report API 1
	@GetMapping("/report")
	public List<InwardRegisterDto> inwardReport() {
		
		List<InwardRegister> list = inwardregisterservice.getAll();
		List<InwardRegisterDto> listDto = new ArrayList<>();
		/* convert the response into UI format */
		 list.stream().forEach(entry->{
			 InwardRegisterDto dto = new InwardRegisterDto(); 
			 dto.setProductTitle(entry.getProduct().getTitle());
			 dto.setProductPrice(entry.getProduct().getPrice());
			 dto.setProductQuantity(entry.getProduct().getQuantity());
			 dto.setSupplierName(entry.getSupplier().getName());
			 dto.setSupplierCity(entry.getSupplier().getCity());
			 listDto.add(dto); //100X 200X
		 });
		 
		return listDto; 
	}
	
}
