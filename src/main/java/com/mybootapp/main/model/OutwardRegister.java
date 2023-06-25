package com.mybootapp.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OutwardRegister {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Product product;
	@ManyToOne
	private Godown godown;
	@ManyToOne
	private Customer customer;
	
	private int quantity;
	private String invoice;
	private String purpose;
	private String receiptno;
	private double billvalue;
	private String deliveredto;
	private LocalDate dateofdelivery;
	public String getDeliveredto() {
		return deliveredto;
	}
	public void setDeliveredto(String deliveredto) {
		this.deliveredto = deliveredto;
	}
	public LocalDate getDateofdelivery() {
		return dateofdelivery;
	}
	public void setDateofdelivery(LocalDate dateofdelivery) {
		this.dateofdelivery = dateofdelivery;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Godown getGodown() {
		return godown;
	}
	public void setGodown(Godown godown) {
		this.godown = godown;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getReceiptno() {
		return receiptno;
	}
	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	public double getBillvalue() {
		return billvalue;
	}
	public void setBillvalue(double billvalue) {
		this.billvalue = billvalue;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
