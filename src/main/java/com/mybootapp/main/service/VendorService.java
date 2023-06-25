package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Product;
import com.mybootapp.main.model.Vendor;
import com.mybootapp.main.repository.VendorRepository;

@Service
public class VendorService {
	
	@Autowired
	private VendorRepository vendorrepository;

	public Vendor insert(Vendor vendor) {
		
		
		return vendorrepository.save(vendor);
	}

	public Vendor getById(int vendorId) {
		// TODO Auto-generated method stub
		
		Optional<Vendor> optional=vendorrepository.findById(vendorId);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}

	public Vendor getvendor(int id) {
		// TODO Auto-generated method stub
		Optional<Vendor> optional=vendorrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}

	public void deletevendor(Vendor vendor) {
		// TODO Auto-generated method stub
		vendorrepository.delete(vendor);
	}

	public List<Vendor> getAllvendors() {
		// TODO Auto-generated method stub
		return vendorrepository.findAll();
	}

}
