package com.mybootapp.main.controller;

import java.time.LocalDate;
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
import com.mybootapp.main.model.CustomerProduct;
import com.mybootapp.main.model.Product;
import com.mybootapp.main.service.CustomerProductService;
import com.mybootapp.main.service.CustomerService;
import com.mybootapp.main.service.InwardRegisterService;
import com.mybootapp.main.service.ProductService;

@RestController
@RequestMapping("/customer-product")
public class CustomerProductController {

	@Autowired
	private CustomerService customerService; 
	@Autowired
	private ProductService productService; 
	@Autowired
	private CustomerProductService customerproductservice;
	@Autowired
	private InwardRegisterService inwardregisterservice;
	
	@PostMapping("/purchase/{customerId}/{productId}")
	public ResponseEntity<?> purchaseApi(@RequestBody CustomerProduct customerProduct,
							@PathVariable("customerId") int customerId, 
							@PathVariable("productId") int productId) {
		
		/* Step 1: validate Ids and fetch objects  */
		Customer customer  = customerService.getById(customerId);
		if(customer == null )
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid customer ID given");
		
		Product product = productService.getById(productId);
		if(product == null )
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid product ID given");
		
		/* Step 2: set customer and product to customerProduct*/
		customerProduct.setCustomer(customer);
		customerProduct.setProduct(product);
		customerProduct.setDateofpurchase(LocalDate.now());
		
		/* Step 3: check if that product is available in proper quantity in InwardRegister*/
		boolean status = inwardregisterservice.checkQuantity(productId,customerProduct.getQuantitypurchased());
		if(status == false)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Product out of stock");
		
		/* Step 4: Save customerProduct object in DB */
		customerProduct = customerproductservice.insert(customerProduct);
		return ResponseEntity.status(HttpStatus.OK)
				.body(customerProduct);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatecustomerproduct(@PathVariable("id") int id,@RequestBody CustomerProduct newcustomerproduct)
	{
	  CustomerProduct oldcustomerproduct=customerproductservice.getcustomerproduct(id);
	  if(oldcustomerproduct==null)
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		  
	  }
	  newcustomerproduct.setId(oldcustomerproduct.getId());
	  newcustomerproduct=customerproductservice.insert(newcustomerproduct);
	  return ResponseEntity.status(HttpStatus.OK).body(newcustomerproduct);
	  }
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteproduct(@PathVariable("id") int id)
	{
		CustomerProduct customerproduct=customerproductservice.getcustomerproduct(id);
		if(customerproduct==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid");
		}
		customerproductservice.deletecustomerproduct(customerproduct);
		return ResponseEntity.status(HttpStatus.OK).body("product deleted");
	}
	
	@GetMapping("/all")
	public List<CustomerProduct> getAllCustomerproducts()
	{
		List<CustomerProduct> list=customerproductservice.getAllCustomerproducts();
		return list;
	} 
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getcustomerproductid(@PathVariable("id") int id)
	{
		
		CustomerProduct  customerproduct=customerproductservice.getcustomerproduct(id);
		if(customerproduct==null)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(customerproduct);
	}
	
}
