package com.finchild.hoppateam.sda4.finchild.modules;

import java.util.ArrayList;

public class User {

    private String userID;
    private String email;
    private String password;
    private String parentPersonalNumber;
    private ArrayList<ChildAccount> childAccounts= new ArrayList <>();

    public User(){

    }

    public User(String userID, String email, String password, String parentPersonalNumber, ArrayList <ChildAccount> childAccounts) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.parentPersonalNumber = parentPersonalNumber;
        this.childAccounts = childAccounts;
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getParentPersonalNumber() {
        return parentPersonalNumber;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setParentPersonalNumber(String parentPersonalNumber) {
        this.parentPersonalNumber = parentPersonalNumber;
    }

    public ArrayList <ChildAccount> getChildAccounts() {
        return childAccounts;
    }

    public void setChildAccounts(ArrayList <ChildAccount> childAccounts) {
        this.childAccounts = childAccounts;
    }
}
