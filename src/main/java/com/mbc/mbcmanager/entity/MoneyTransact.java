package com.mbc.mbcmanager.entity;

import java.util.Date;

public class MoneyTransact {

	private String transactionType;
	private String transactionId;
	private Date transactionDate;
	private double amount;

	public MoneyTransact(String transactionType, double amount) {
		this.transactionType = transactionType;
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



}
