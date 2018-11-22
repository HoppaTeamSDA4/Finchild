package com.finchild.hoppateam.sda4.finchild.modules;

public class parentAccount {

    private String parentAccountId;
    private String parentAccountNumber;
    private String parentCardNumber;
    private String name;
    private boolean status;

    public parentAccount() {
    }

    public parentAccount(String parentAccountId, String parentAccountNumber, String parentCardNumber, String name, boolean status) {
        this.parentAccountId = parentAccountId;
        this.parentAccountNumber = parentAccountNumber;
        this.parentCardNumber = parentCardNumber;
        this.name = name;
        this.status = status;
    }

    public String getParentAccountId() {
        return parentAccountId;
    }

    public String getParentAccountNumber() {
        return parentAccountNumber;
    }

    public String getParentCardNumber() {
        return parentCardNumber;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setParentAccountId(String parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public void setParentAccountNumber(String parentAccountNumber) {
        this.parentAccountNumber = parentAccountNumber;
    }

    public void setParentCardNumber(String parentCardNumber) {
        this.parentCardNumber = parentCardNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
