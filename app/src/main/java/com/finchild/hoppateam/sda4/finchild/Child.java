package com.finchild.hoppateam.sda4.finchild;

public class Child {

    private String name;
    private Integer balance;
    private boolean picture;

    public Child(String name, Integer balance, boolean picture) {
        this.name = name;
        this.balance = balance;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public boolean isPicture() {
        return picture;
    }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }
}
