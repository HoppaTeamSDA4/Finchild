package com.finchild.hoppateam.sda4.finchild.modules;

public class ChildAccount {

    private String accountNo;
    private String cardNumber;
    private String name;
    private String personalNumber;
    private String mobileNumber;
    private double balance;
    private boolean status;
    private double dailyLimitAmount;

    public boolean isExceedLimitNotify() {
        return exceedLimitNotify;
    }

    public void setExceedLimitNotify(boolean exceedLimitNotify) {
        this.exceedLimitNotify = exceedLimitNotify;
    }

    private boolean exceedLimitNotify;
    public boolean isDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(boolean dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    private boolean dailyLimit;


    public ChildAccount() {
    }

    public ChildAccount(String accountNo, String cardNumber, String name, String personalNumber, String mobileNumber,double balance, boolean status,double dailyLimitAmount,boolean dailyLimit
    ,boolean exceedLimitNotify) {

        this.accountNo = accountNo;
        this.cardNumber = cardNumber;
        this.name = name;
        this.personalNumber = personalNumber;
        this.mobileNumber = mobileNumber;
        this.balance=balance;
        this.status = status;
        this.dailyLimit =dailyLimit;
        this.dailyLimitAmount=dailyLimitAmount;
        this.exceedLimitNotify=exceedLimitNotify;
    }
    public double getDailyLimitAmount() { return dailyLimitAmount; }

    public void setDailyLimitAmount(double dailyLimitAmount) { this.dailyLimitAmount = dailyLimitAmount; }

    public String getAccountNo() {
        return accountNo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }




}
