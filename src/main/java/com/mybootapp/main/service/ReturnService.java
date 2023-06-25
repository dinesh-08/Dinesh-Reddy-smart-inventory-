package com.mybootapp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybootapp.main.model.Product;
import com.mybootapp.main.model.Returns;
import com.mybootapp.main.repository.ReturnRepository;


@Service
public class ReturnService {

	@Autowired
	private ReturnRepository returnrepository;
	public Returns insert(Returns returns) {
		// TODO Auto-generated method stub
		return returnrepository.save(returns);
	}

	public List<Returns> getAllreturns() {
		// TODO Auto-generated method stub
		return returnrepository.findAll();
	}

	public Returns getreturn(int id) {
		// TODO Auto-generated method stub
		Optional<Returns> optional=returnrepository.findById(id);
		if(!optional.isPresent())
		{
			return null;
		}
return optional.get();
	}

	public void deletereturn(Returns returns) {
		// TODO Auto-generated method stub
		returnrepository.delete(returns);
	}

}
