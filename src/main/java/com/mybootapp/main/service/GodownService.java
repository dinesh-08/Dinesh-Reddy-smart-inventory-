package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mybootapp.main.model.Godown;

import com.mybootapp.main.repository.GodownRepository;

@Service
public class GodownService {
     
	
	@Autowired
	private GodownRepository godownrepository;
	public Godown insert(Godown godown) {
		// TODO Auto-generated method stub
		return  godownrepository.save(godown);
	}
	public Godown getById(int godownId) {
		// TODO Auto-generated method stub
	
		Optional<Godown> optional=godownrepository.findById(godownId);
		if(!optional.isPresent())
		{
			return null;
		}
       return optional.get();
	}
	public List<Godown> getAllgodowns() {
		// TODO Auto-generated method stub
		return godownrepository.findAll();
	}
	public Godown getgodown(int id) {
		Optional<Godown> optional=godownrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}
	public void deletegodown(Godown godown) {
		// TODO Auto-generated method stub
		godownrepository.delete(godown);
		
	}
	public List<Godown> getAll() {
		// TODO Auto-generated method stub
		return  godownrepository.findAll();
	}

}
