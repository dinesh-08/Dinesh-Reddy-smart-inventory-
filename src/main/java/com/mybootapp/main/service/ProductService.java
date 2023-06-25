package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Product;
import com.mybootapp.main.repository.Productrepository;



@Service
public class ProductService {
	
	
	
	@Autowired
	private Productrepository productrepository;

	public Product insert(Product product) {
		// TODO Auto-generated method stub
		
		
		return productrepository.save(product);
	}

	public List<Product> getAllproducts() {
		// TODO Auto-generated method stub
		
		
		return productrepository.findAll();
	}

	public Product getproduct(int id) {
		// TODO Auto-generated method stub
		
		Optional<Product> optional=productrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}

	public void deleteproduct(Product product) {
		// TODO Auto-generated method stub
		productrepository.delete(product);
		
	}

	public Product getById(int  productId) {
		// TODO Auto-generated method stub
		
		Optional<Product> optional=productrepository.findById(productId);
				if(!optional.isPresent())
				{
					return null;
				}
		return optional.get();
	}

	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productrepository.findAll();
	}

}
