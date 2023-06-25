
package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mybootapp.main.model.Supplier;
import com.mybootapp.main.model.Vendor;
import com.mybootapp.main.repository.SupplierRepository;

@Service
public class SupplierService {
     
	
	@Autowired
	private SupplierRepository supplierrepository;
	public Supplier insert(Supplier supplier) {
		// TODO Auto-generated method stub
		return  supplierrepository.save(supplier);
	}
	public Supplier getById(int supplierId) {
		// TODO Auto-generated method stub
		Optional<Supplier> optional=supplierrepository.findById(supplierId);
		if(!optional.isPresent())
		{
			return null;
		}
return optional.get();
		
	}
	public List<Supplier> getAllsuppliers() {
		// TODO Auto-generated method stub
		return supplierrepository.findAll();
	}
	public Supplier getsupplier(int id) {
		// TODO Auto-generated method stub
		Optional<Supplier> optional=supplierrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}
	public void deletesupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		supplierrepository.delete(supplier);
		
	}

}
