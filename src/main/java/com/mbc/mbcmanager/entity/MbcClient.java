package com.mbc.mbcmanager.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mbc_client")
public class MbcClient {

	@Id
	private String id;
	private String clientName;
	private List<MoneyTransact> income;
	private List<MoneyTransact> payments;
	private double totalIncome;
	private double totalExpenses;
	

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public List<MoneyTransact> getIncome() {
		return income;
	}

	public void setIncome(List<MoneyTransact> income) {
		this.income = income;
	}

	public List<MoneyTransact> getPayments() {
		return payments;
	}

	public void setPayments(List<MoneyTransact> payments) {
		this.payments = payments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
