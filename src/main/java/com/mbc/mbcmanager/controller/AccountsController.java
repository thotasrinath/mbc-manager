package com.mbc.mbcmanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mbc.mbcmanager.vo.MoneyTransactVo;
import com.mbc.mbcmanager.vo.ResponseVo;
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
import com.mbc.mbcmanager.vo.ExpenseVo;
import com.mbc.mbcmanager.vo.IncomeVo;

@RestController
@RequestMapping("/account")
public class AccountsController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @CrossOrigin
    @RequestMapping(path = "/addincome/{clientId}", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseVo addIncome(@RequestBody IncomeVo incomeVo, @PathVariable String clientId) {

        ResponseVo responseVo = new ResponseVo();

        try {
            MbcClient mbcClient = mongoTemplate.findById(clientId, MbcClient.class);

            List<MoneyTransact> incomeTransacts = mbcClient.getIncome();
            if (incomeTransacts == null) {

                incomeTransacts = new ArrayList<MoneyTransact>();
                mbcClient.setIncome(incomeTransacts);
            }
            if (StringUtils.isEmpty(incomeVo.getTransactionId())) {

                MoneyTransact newTransact = new MoneyTransact(UUID.randomUUID().toString(), incomeVo.getReferenceNo(), incomeVo.getTransactionType(),
                        incomeVo.getTransactionDate(), incomeVo.getAmount());
                incomeTransacts.add(newTransact);

            } else {

                incomeTransacts.stream().filter(mt -> mt.getTransactionId().equals(incomeVo.getTransactionId()))
                        .forEach(mt -> {

                            mt.setAmount(incomeVo.getAmount());
                            mt.setTransactionType(incomeVo.getTransactionType());
                            mt.setTransactionDate(incomeVo.getTransactionDate());
                            mt.setReferenceNo(incomeVo.getReferenceNo());

                        });

            }
            Optional<MoneyTransact> totalIncome = incomeTransacts.stream()
                    .reduce((a1, a2) -> new MoneyTransact(a2.getAmount() + a1.getAmount()));

            totalIncome.ifPresent(m -> mbcClient.setTotalIncome(m.getAmount()));

            mongoTemplate.save(mbcClient);

            responseVo.setStatus("success");


        } catch (Exception e) {
            responseVo.setStatus("failed");
            responseVo.setErrorMessage(e.getMessage());

        }
        return responseVo;

    }

    @CrossOrigin
    @RequestMapping(path = "/delincome/{clientId}", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseVo delIncome(@RequestBody IncomeVo incomeVo, @PathVariable String clientId) {

        ResponseVo responseVo = new ResponseVo();

        try {
            MbcClient mbcClient = mongoTemplate.findById(clientId, MbcClient.class);

            List<MoneyTransact> incomeTransacts = mbcClient.getIncome();

            incomeTransacts.removeIf(m -> m.getTransactionId().equals(incomeVo.getTransactionId()));

            if (incomeTransacts.size() == 0) {
                mbcClient.setTotalIncome(0);
            } else {

                Optional<MoneyTransact> totalIncome = incomeTransacts.stream()
                        .reduce((a1, a2) -> new MoneyTransact(a2.getAmount() + a1.getAmount()));

                totalIncome.ifPresent(m -> mbcClient.setTotalIncome(m.getAmount()));
            }

            mongoTemplate.save(mbcClient);
            responseVo.setStatus("success");
        } catch (Exception e) {
            responseVo.setStatus("failed");
            responseVo.setErrorMessage(e.getMessage());
        }
        return responseVo;

    }

    @CrossOrigin
    @RequestMapping(path = "/addexpense/{clientId}", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseVo addExpense(@RequestBody ExpenseVo expenseVo, @PathVariable String clientId) {

        ResponseVo responseVo = new ResponseVo();

        try {
            MbcClient mbcClient = mongoTemplate.findById(clientId, MbcClient.class);

            List<MoneyTransact> expenseTransacts = mbcClient.getPayments();
            if (expenseTransacts == null) {

                expenseTransacts = new ArrayList<MoneyTransact>();
                mbcClient.setPayments(expenseTransacts);
            }
            if (StringUtils.isEmpty(expenseVo.getTransactionId())) {

                MoneyTransact newTransact = new MoneyTransact(UUID.randomUUID().toString(), expenseVo.getReferenceNo(), expenseVo.getTransactionType(),
                        expenseVo.getTransactionDate(), expenseVo.getAmount(), expenseVo.getVendorName());
                expenseTransacts.add(newTransact);

            } else {

                expenseTransacts.stream().filter(mt -> mt.getTransactionId().equals(expenseVo.getTransactionId()))
                        .forEach(mt -> {

                            mt.setAmount(expenseVo.getAmount());
                            mt.setTransactionType(expenseVo.getTransactionType());
                            mt.setTransactionDate(expenseVo.getTransactionDate());
                            mt.setVendorName(expenseVo.getVendorName());
                            mt.setReferenceNo(expenseVo.getReferenceNo());

                        });

            }
            Optional<MoneyTransact> totalIncome = expenseTransacts.stream()
                    .reduce((a1, a2) -> new MoneyTransact(a2.getAmount() + a1.getAmount()));

            totalIncome.ifPresent(m -> mbcClient.setTotalExpenses(m.getAmount()));

            mongoTemplate.save(mbcClient);
            responseVo.setStatus("success");

        } catch (Exception e) {
            responseVo.setStatus("failed");
            responseVo.setErrorMessage(e.getMessage());
        }
        return responseVo;

    }

    @CrossOrigin
    @RequestMapping(path = "/delexpense/{clientId}", method = RequestMethod.POST, consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseVo delExpense(@RequestBody ExpenseVo expenseVo, @PathVariable String clientId) {

        ResponseVo responseVo = new ResponseVo();

        try {
            MbcClient mbcClient = mongoTemplate.findById(clientId, MbcClient.class);

            List<MoneyTransact> expenseTransacts = mbcClient.getPayments();

            expenseTransacts.removeIf(m -> m.getTransactionId().equals(expenseVo.getTransactionId()));

            if (expenseTransacts.size() == 0) {
                mbcClient.setTotalExpenses(0);
            } else {

                Optional<MoneyTransact> totalIncome = expenseTransacts.stream()
                        .reduce((a1, a2) -> new MoneyTransact(a2.getAmount() + a1.getAmount()));

                totalIncome.ifPresent(m -> mbcClient.setTotalExpenses(m.getAmount()));
            }

            mongoTemplate.save(mbcClient);
            responseVo.setStatus("success");

        } catch (Exception e) {
            responseVo.setStatus("failed");
            responseVo.setErrorMessage(e.getMessage());
        }
        return responseVo;

    }

    @CrossOrigin
    @RequestMapping(path = "/expensesbyvendor/{clientId}/{vendorName}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseVo getExpensesByVendor(@PathVariable String clientId, @PathVariable String vendorName) {

        ResponseVo<List<MoneyTransactVo>> responseVo = new ResponseVo<>();

        try {
            MbcClient mbcClient = mongoTemplate.findById(clientId, MbcClient.class);

            List<MoneyTransact> expenseTransacts = mbcClient.getPayments().stream()
                    .filter(m -> m.getVendorName().equals(vendorName)).collect(Collectors.toList());

            List<MoneyTransactVo> moneyTransactVos = new ArrayList<>();

            expenseTransacts.forEach(m -> {
                MoneyTransactVo moneyTransactVo = new MoneyTransactVo(m);
                moneyTransactVos.add(moneyTransactVo);
            });

            responseVo.setStatus("success");
            responseVo.setResponseObject(moneyTransactVos);

        } catch (Exception e) {
            responseVo.setStatus("failed");
            responseVo.setErrorMessage(e.getMessage());
        }
        return responseVo;


    }

}
