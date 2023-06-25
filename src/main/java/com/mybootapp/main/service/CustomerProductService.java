package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Customer;
import com.mybootapp.main.model.CustomerProduct;
import com.mybootapp.main.model.Product;
import com.mybootapp.main.model.Vendor;
import com.mybootapp.main.repository.CustomerProductRepository;

@Service
public class CustomerProductService {

	
	@Autowired
	private CustomerProductRepository customerproductrepository;
//	public CustomerProduct insert(Product newproduct) {
//		// TODO Auto-generated method stub
//		return customerproductrepository.save(newproduct);
//	}
//	public List<CustomerProduct> getByCustomerId(int customerId) {
//		// TODO Auto-generated method stub
//		return customerproductrepository.findAll();
//	}
	public CustomerProduct insert(CustomerProduct customerProduct) {
		 
		return customerproductrepository.save(customerProduct);
	}
	public List<CustomerProduct> getByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return customerproductrepository.findAll();
	}
	public CustomerProduct getcustomerproduct(int id) {
		// TODO Auto-generated method stub
		Optional<CustomerProduct> optional=customerproductrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}
	public void deletecustomerproduct(CustomerProduct customerproduct) {
		// TODO Auto-generated method stub
		customerproductrepository.delete(customerproduct);
	}
	public List<CustomerProduct> getAllCustomerproducts() {
		// TODO Auto-generated method stub
		return customerproductrepository.findAll();
	}


	
	
	

	
}
