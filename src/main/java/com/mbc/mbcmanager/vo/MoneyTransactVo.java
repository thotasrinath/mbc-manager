package com.mbc.mbcmanager.vo;

import com.mbc.mbcmanager.entity.MoneyTransact;

import java.text.DateFormat;
import java.util.Date;

public class MoneyTransactVo {

    private String transactionId;
    private String transactionType;
    private String referenceNo;
    private String vendorName;
    private Date transactionDate;
    private double amount;

    public MoneyTransactVo(MoneyTransact mt){

        this.transactionId = mt.getTransactionId();
        this.transactionType = mt.getTransactionType();
        this.vendorName = mt.getVendorName();
        this.amount = mt.getAmount();
        this.transactionDate = mt.getTransactionDate();
        this.referenceNo = mt.getReferenceNo();


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


    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }
}
