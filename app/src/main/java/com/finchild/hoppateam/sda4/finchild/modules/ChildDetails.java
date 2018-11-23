package com.finchild.hoppateam.sda4.finchild.modules;

public class ChildDetails {

    private String name;
    private String balance;
    private int idPicture;

    public ChildDetails(String name, String balance, int idPicture) {
        this.name = name;
        this.balance = balance;
        this.idPicture = idPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(int idPicture) {
        this.idPicture = idPicture;
    }
}
