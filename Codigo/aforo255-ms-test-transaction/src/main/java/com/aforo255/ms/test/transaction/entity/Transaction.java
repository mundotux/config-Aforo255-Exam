package com.aforo255.ms.test.transaction.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="transaction")
public class Transaction {
	
	private String id;
	private Integer idInvoice;
	private double amount;
	private String creationDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(Integer idInvoice) {
		this.idInvoice = idInvoice;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
