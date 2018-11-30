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
    private boolean exceedDailyNotify;
    private boolean dailyLimit;

    private double weeklyLimitAmount;
    private boolean exceedWeeklyNotify;

    public ChildAccount() {
    }

    public ChildAccount(String accountNo, String cardNumber, String name, String personalNumber,
                        String mobileNumber,double balance, boolean status,
                        double dailyLimitAmount,boolean dailyLimit,boolean exceedDailyNotify,
                        double weeklyLimitAmount,boolean weeklyLimit,boolean exceedWeeklyNotify,
                        double monthlyLimitAmount,boolean monthlyLimit,boolean exceedMonthlyNotify
    ) {

        this.accountNo = accountNo;
        this.cardNumber = cardNumber;
        this.name = name;
        this.personalNumber = personalNumber;
        this.mobileNumber = mobileNumber;
        this.balance=balance;
        this.status = status;
        this.dailyLimit =dailyLimit;
        this.dailyLimitAmount=dailyLimitAmount;
        this.exceedDailyNotify=exceedDailyNotify;

        this.weeklyLimit =weeklyLimit;
        this.weeklyLimitAmount=weeklyLimitAmount;
        this.exceedWeeklyNotify=exceedWeeklyNotify;

        this.monthlyLimit =monthlyLimit;
        this.monthlyLimitAmount=monthlyLimitAmount;
        this.exceedMonthlyNotify=exceedMonthlyNotify;
    }


    public double getWeeklyLimitAmount() {
        return weeklyLimitAmount;
    }

    public void setWeeklyLimitAmount(double weeklyLimitAmount) {
        this.weeklyLimitAmount = weeklyLimitAmount;
    }

    public boolean isExceedWeeklyNotify() {
        return exceedWeeklyNotify;
    }

    public void setExceedWeeklyNotify(boolean exceedWeeklyNotify) {
        this.exceedWeeklyNotify = exceedWeeklyNotify;
    }

    public boolean isWeeklyLimit() {
        return weeklyLimit;
    }

    public void setWeeklyLimit(boolean weeklyLimit) {
        this.weeklyLimit = weeklyLimit;
    }

    public double getMonthlyLimitAmount() {
        return monthlyLimitAmount;
    }

    public void setMonthlyLimitAmount(double monthlyLimitAmount) {
        this.monthlyLimitAmount = monthlyLimitAmount;
    }

    public boolean isExceedMonthlyNotify() {
        return exceedMonthlyNotify;
    }

    public void setExceedMonthlyNotify(boolean exceedMonthlyNotify) {
        this.exceedMonthlyNotify = exceedMonthlyNotify;
    }

    public boolean isMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(boolean monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    private boolean weeklyLimit;

    private double monthlyLimitAmount;
    private boolean exceedMonthlyNotify;
    private boolean monthlyLimit;


    public boolean isExceedDailyNotify() { return exceedDailyNotify;}

    public void setExceedDailyNotify(boolean exceedDailyNotify) {  this.exceedDailyNotify = exceedDailyNotify; }

    public boolean isDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(boolean dailyLimit) {
        this.dailyLimit = dailyLimit;
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
