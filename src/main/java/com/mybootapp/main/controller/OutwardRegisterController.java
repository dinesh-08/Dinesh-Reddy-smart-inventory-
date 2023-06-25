package com.mybootapp.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.mybootapp.main.dto.OutwardRegisterDto;
import com.mybootapp.main.model.Customer;
import com.mybootapp.main.model.CustomerProduct;
import com.mybootapp.main.model.Godown;

import com.mybootapp.main.model.OutwardRegister;

import com.mybootapp.main.model.Product;
import com.mybootapp.main.service.CustomerProductService;
import com.mybootapp.main.service.CustomerService;
import com.mybootapp.main.service.GodownService;
import com.mybootapp.main.service.OutwardRegisterService;

import com.mybootapp.main.service.ProductService;
import com.springboot.main.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/outwardregister")
public class OutwardRegisterController 
{
	 @Autowired
	  private ProductService productservice;
	 @Autowired
	  private OutwardRegisterService outwardregisterservice;
	 @Autowired
	 private CustomerService customerservice;
	 @Autowired
	 private GodownService godownservice;
	 private CustomerProductService customerproductservice;
	 
	 @PostMapping("/add/{productId}/{godownId}/{customerId}")
	 public ResponseEntity<?> outwardPosting(@RequestBody OutwardRegister outwardregister,
			                    @PathVariable("productId") int productId,
			                    @PathVariable("godownId") int godownId,
			                    @PathVariable("customerId") int customerId
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
		 Customer customer=customerservice.getById(customerId);
		 if(customer==null)
		 {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		 }
		 outwardregister.setGodown(godown);
		 outwardregister.setProduct(product);
		 outwardregister.setCustomer(customer);
		 
		 
		 outwardregister=outwardregisterservice.insert(outwardregister);
		 return ResponseEntity.status(HttpStatus.OK).body(outwardregister);
	}
	 
	 @PutMapping("/update/{productId}/{godownId}/{customerId}/{id}")
		public ResponseEntity<?> updateoutwardregister(@PathVariable("id") int id,
				@PathVariable("productId") int productId,
				@PathVariable("godownId") int godownId,
				@PathVariable("customerId") int customerId,
				
	 			@RequestBody OutwardRegister newoutwardregister)
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
			 Customer customer=customerservice.getById(customerId);
			 if(customer==null)
			 {
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
			 }
			
		  OutwardRegister oldoutwardregister=outwardregisterservice.getoutwardregister(id);
		  if(oldoutwardregister==null)
		  {
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
			  
		  }
		  newoutwardregister.setGodown(godown);
			 newoutwardregister.setProduct(product);
			 newoutwardregister.setCustomer(customer);
		  newoutwardregister.setId(oldoutwardregister.getId());
		  
		  newoutwardregister=outwardregisterservice.insert(newoutwardregister);
		  return ResponseEntity.status(HttpStatus.OK).body(newoutwardregister);
		 }
	 @GetMapping("/all")
		public List<OutwardRegister> getAlloutwardregisters()
		{
			List<OutwardRegister> list=outwardregisterservice.getAlloutwardregisters();
			return list;
		} 
		@GetMapping("/one/{id}")
		public ResponseEntity<?> getoutwardregisterid(@PathVariable("id") int id)
		{
			
			OutwardRegister outwardregister=outwardregisterservice.getoutwardregister(id);
			if(outwardregister==null)
			{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
			}
			return ResponseEntity.status(HttpStatus.OK).body(outwardregister);
			
		}
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteoutwardregister(@PathVariable("id") int id)
		{
			OutwardRegister outwardregister=outwardregisterservice.getoutwardregister(id);
			if(outwardregister==null)
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
			}
			outwardregisterservice.deleteoutwardregister(outwardregister);
			return ResponseEntity.status(HttpStatus.OK).body("outwardregister deleted");
		}
		@GetMapping("/report")
		public List<OutwardRegisterDto> outwardReport() {
			
			List<OutwardRegister> list = outwardregisterservice.getAll();
			List<OutwardRegisterDto> listDto = new ArrayList<>();
			/* convert the response into UI format */
			 list.stream().forEach(entry->{
				 OutwardRegisterDto dto = new OutwardRegisterDto(); 
				 dto.setProductTitle(entry.getProduct().getTitle());
				 
				 dto.setProductQuantity(entry.getProduct().getQuantity());
				 dto.setGodownLocation(entry.getGodown().getLocation());
				 dto.setGodownManager(entry.getGodown().getManager().getName());
				 dto.setReceiptno(entry.getReceiptno());
				 listDto.add(dto); //100X 200X
			 });
			 
			return listDto; 
		}
	///Report APi2	
		@GetMapping("/report/customer/{customerId}")
	    public ResponseEntity<?> outwardReportByCustomer(@PathVariable int customerId) throws ResourceNotFoundException {
	        List<CustomerProduct> list;
	        list =  (List<CustomerProduct>) customerproductservice.getByCustomerId(customerId);

	        HashMap<String, Integer> map = new HashMap<>();
	        list.stream().forEach(entry -> {
	            if (map.containsKey(entry.getProduct().getTitle())) {
	                map.put(entry.getProduct().getTitle(), map.get(entry.getProduct().getTitle()) + entry.getQuantitypurchased());
	            }
	            else {
	                map.put(entry.getProduct().getTitle(), entry.getQuantitypurchased());                
	            }
	        });

	        return ResponseEntity.status(HttpStatus.OK).body(map);
	    }
		
		
		
		
}

