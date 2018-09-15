package com.mbc.mbcmanager.entity;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mbc_client")
public class MbcClient {

	@Id
	private String clientId;
	private String clientName;
	private String siteDescription;
	private List<MoneyTransact> income;
	private List<MoneyTransact> payments;
	private double totalIncome;
	private double totalExpenses;
	private transient Map<String,DoubleSummaryStatistics> groupedPayments;
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSiteDescription() {
		return siteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}
	

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


	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Map<String, DoubleSummaryStatistics> getGroupedPayments() {
		return groupedPayments;
	}

	public void setGroupedPayments(Map<String, DoubleSummaryStatistics> groupedPayments) {
		this.groupedPayments = groupedPayments;
	}
}
