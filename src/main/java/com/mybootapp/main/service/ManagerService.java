package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Manager;
import com.mybootapp.main.model.Product;
import com.mybootapp.main.repository.ManagerRepository;


@Service
public class ManagerService {

	
	 @Autowired
	private ManagerRepository managerrepository;
	public Manager insert(Manager manager) {
		// TODO Auto-generated method stub
		return managerrepository.save(manager);
	}
	public Manager getById(int managerId) {
		// TODO Auto-generated method stub
		
		Optional<Manager> optional=managerrepository.findById(managerId);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get() ;
	}
	public Manager getmanager(int id) {
		// TODO Auto-generated method stub

		Optional<Manager> optional=managerrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
		return optional.get();
	}
	public void deletemanager(Manager manager) {
		// TODO Auto-generated method stub
	managerrepository.delete(manager);
		
	}
	public List<Manager> getAllmanagers() {
		// TODO Auto-generated method stub
		return managerrepository.findAll();
	}

}
