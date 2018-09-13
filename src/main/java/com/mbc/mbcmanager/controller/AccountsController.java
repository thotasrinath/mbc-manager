package com.mbc.mbcmanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbc.mbcmanager.entity.MbcClient;
import com.mbc.mbcmanager.entity.MoneyTransact;
import com.mbc.mbcmanager.vo.IncomeVo;

@RestController
@RequestMapping("/account")
public class AccountsController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@CrossOrigin
	@RequestMapping(path = "/addincome/{clientId}", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public void addIncome(@RequestBody IncomeVo incomeVo, @PathVariable String clientId) {

		MbcClient mbcClient = mongoTemplate.findById(clientId, MbcClient.class);

		List<MoneyTransact> incomeTransacts = mbcClient.getIncome();
		if (incomeTransacts == null) {

			incomeTransacts = new ArrayList<MoneyTransact>();
			mbcClient.setIncome(incomeTransacts);
		}
		if (StringUtils.isEmpty(incomeVo.getTransactionId())) {

			MoneyTransact newTransact = new MoneyTransact(UUID.randomUUID().toString(), incomeVo.getTransactionType(),
					incomeVo.getTransactionDate(), incomeVo.getAmount());
			incomeTransacts.add(newTransact);

		} else {

			incomeTransacts.stream().filter(mt -> mt.getTransactionId().equals(incomeVo.getTransactionId()))
					.forEach(mt -> {

						mt.setAmount(incomeVo.getAmount());
						mt.setTransactionType(incomeVo.getTransactionType());
						mt.setTransactionDate(incomeVo.getTransactionDate());

					});

		}
		Optional<MoneyTransact> totalIncome = incomeTransacts.stream()
				.reduce((a1, a2) -> new MoneyTransact(a2.getAmount() + a1.getAmount()));

		totalIncome.ifPresent(m -> mbcClient.setTotalIncome(m.getAmount()));

		mongoTemplate.save(mbcClient);

	}

	@CrossOrigin
	@RequestMapping(path = "/delincome/{clientId}", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public void delIncome(@RequestBody IncomeVo incomeVo, @PathVariable String clientId) {

		MbcClient mbcClient = mongoTemplate.findById(clientId, MbcClient.class);

		List<MoneyTransact> incomeTransacts = mbcClient.getIncome();

		incomeTransacts.removeIf(m -> m.getTransactionId().equals(incomeVo.getTransactionId()));

		Optional<MoneyTransact> totalIncome = incomeTransacts.stream()
				.reduce((a1, a2) -> new MoneyTransact(a2.getAmount() + a1.getAmount()));

		totalIncome.ifPresent(m -> mbcClient.setTotalIncome(m.getAmount()));

		mongoTemplate.save(mbcClient);
	}

}
