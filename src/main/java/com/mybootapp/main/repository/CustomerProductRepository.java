package com.mybootapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybootapp.main.model.CustomerProduct;
import com.mybootapp.main.model.Product;

public interface CustomerProductRepository  extends JpaRepository<CustomerProduct,Integer>{



	

}
