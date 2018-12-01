package com.mbc.mbcmanager.vo;

import com.mbc.mbcmanager.entity.MoneyTransact;

import java.text.DateFormat;

public class MoneyTransactVo {

    private String transactionId;
    private String transactionType;
    private String vendorName;
    private String transactionDate;
    private double amount;

    public MoneyTransactVo(MoneyTransact mt){

        this.transactionId = mt.getTransactionId();
        this.transactionType = mt.getTransactionType();
        this.vendorName = mt.getVendorName();
        this.amount = mt.getAmount();
        this.transactionDate = DateFormat.getDateInstance().format(mt.getTransactionDate());


    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
