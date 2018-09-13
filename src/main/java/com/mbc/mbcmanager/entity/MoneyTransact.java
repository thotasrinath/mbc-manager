package com.mbc.mbcmanager.entity;

import java.util.Date;

public class MoneyTransact {

	private String transactionId;
	private String transactionType;
	private String vendorName;
	private Date transactionDate;
	private double amount;

	public MoneyTransact() {

	}

	public MoneyTransact(String transactionId, String transactionType, Date transactionDate, double amount) {
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}
	
	public MoneyTransact(String transactionId, String transactionType, Date transactionDate, double amount,String vendorName) {
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.vendorName = vendorName;
		this.amount = amount;
	}
	
	public MoneyTransact(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


}
