package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mybootapp.main.model.InwardRegister;
import com.mybootapp.main.repository.InwardRegisterRepository;

@Service
public class InwardRegisterService {
    
	@Autowired
	private InwardRegisterRepository inwardregisterrepository;
	public InwardRegister insert(InwardRegister inwardregister) {
		// TODO Auto-generated method stub
		return inwardregisterrepository.save(inwardregister);
	}
	public boolean checkQuantity(int productId, int quantitypurchased) {
		InwardRegister inwardRegister = inwardregisterrepository
				.checkQuantity(productId,quantitypurchased);
		if(inwardRegister == null)
			return false;
		return true;
		
	}
	public List<InwardRegister> getAllinwardregisters() {
		// TODO Auto-generated method stub
		
		return inwardregisterrepository.findAll();
	}
	public InwardRegister getinwardregister(int id) {
		// TODO Auto-generated method stub
		Optional<InwardRegister> optional=inwardregisterrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
       return optional.get();

	}
	public void deleteinwardregister(InwardRegister inwardregister) {
		// TODO Auto-generated method stub
		
		inwardregisterrepository.delete(inwardregister);
	}
	public List<InwardRegister> getAll() {
		// TODO Auto-generated method stub
		return inwardregisterrepository.findAll();
	}
	
	
	

}
