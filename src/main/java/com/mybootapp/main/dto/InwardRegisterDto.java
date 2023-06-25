package com.mybootapp.main.dto;

import org.springframework.stereotype.Component;

@Component
public class InwardRegisterDto {
	private String productTitle;
	private double productQuantity;
	private double productPrice;
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double d) {
		this.productPrice = d;
	}
	private String supplierName;
	private String supplierCity;
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public double getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(double d) {
		this.productQuantity = d;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierCity() {
		return supplierCity;
	}
	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}
	
}
