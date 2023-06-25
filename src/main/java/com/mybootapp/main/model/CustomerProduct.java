package com.mybootapp.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomerProduct {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@ManyToOne
private Customer customer;
@ManyToOne
private Product product;
private int quantitypurchased;
private LocalDate Dateofpurchase;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Customer getCustomer() {
	return customer;
}
public  void setCustomer(Customer customer) {
	this.customer = customer;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public int getQuantitypurchased() {
	return quantitypurchased;
}
public void setQuantitypurchased(int quantitypurchased) {
	this.quantitypurchased = quantitypurchased;
}
public LocalDate getDateofpurchase() {
	return Dateofpurchase;
}
public void setDateofpurchase(LocalDate dateofpurchase) {
	Dateofpurchase = dateofpurchase;
}
@Override
public String toString() {
	return "CustomerProduct [id=" + id + ", customer=" + customer + ", product=" + product + ", quantitypurchased="
			+ quantitypurchased + ", Dateofpurchase=" + Dateofpurchase + "]";
}





}
