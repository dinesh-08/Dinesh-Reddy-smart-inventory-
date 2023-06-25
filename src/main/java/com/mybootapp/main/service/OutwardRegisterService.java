package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.InwardRegister;
import com.mybootapp.main.model.OutwardRegister;
import com.mybootapp.main.model.Vendor;
import com.mybootapp.main.repository.OutwardRegisterRepository;

@Service
public class OutwardRegisterService {

	@Autowired
	private OutwardRegisterRepository outwardregisterrepository;
	public OutwardRegister insert(OutwardRegister outwardregister) {
		// TODO Auto-generated method stub
		return outwardregisterrepository.save(outwardregister);
	}
	public OutwardRegister getoutwardregister(int id) {
		// TODO Auto-generated method stub
		Optional<OutwardRegister> optional=outwardregisterrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
       return optional.get();
	}
	public List<OutwardRegister> getAlloutwardregisters() {
		// TODO Auto-generated method stub
		return outwardregisterrepository.findAll();
	}
	public void deleteoutwardregister(OutwardRegister outwardregister) {
	    outwardregisterrepository.delete(outwardregister);
		
	}
	public List<OutwardRegister> getAll() {
		// TODO Auto-generated method stub
		return outwardregisterrepository.findAll();
	}
	public OutwardRegister getById(int outwardregisterId) {
		// TODO Auto-generated method stub
		Optional<OutwardRegister> optional=outwardregisterrepository.findById(outwardregisterId);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}

}
