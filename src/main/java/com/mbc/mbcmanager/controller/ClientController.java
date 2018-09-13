package com.mbc.mbcmanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbc.mbcmanager.entity.MbcClient;
import com.mbc.mbcmanager.entity.MoneyTransact;

@RestController
public class ClientController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@CrossOrigin
	@RequestMapping("/clients")
	public List<MbcClient> getAllClients() {

		Query query = new Query();
		query.fields().include("clientName");

		return mongoTemplate.find(query, MbcClient.class);

	}

	@CrossOrigin
	@RequestMapping("/client/{id}")
	public MbcClient getClient(@PathVariable String id) {

		return mongoTemplate.findById(id, MbcClient.class);

	}

	@CrossOrigin
	@RequestMapping("/clientsave")
	public void saveClient() {

		MbcClient mbcClient = new MbcClient();
		mbcClient.setClientName("Srinath");

		MoneyTransact one = new MoneyTransact("Cheque 123444", 9288);
		one.setTransactionDate(new Date());
		one.setTransactionId("1");
		MoneyTransact two = new MoneyTransact("Cheque 123dvf4", 82772);
		two.setTransactionDate(new Date());
		two.setTransactionId("1");

		List<MoneyTransact> income = new ArrayList<MoneyTransact>();
		income.add(one);
		
		List<MoneyTransact> expense = new ArrayList<MoneyTransact>();
		expense.add(two);

		
		mbcClient.setIncome(income);
		mbcClient.setPayments(expense);
		mbcClient.setTotalExpenses(100);
		mongoTemplate.save(mbcClient);

	}

}
