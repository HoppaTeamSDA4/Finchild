package com.finchild.hoppateam.sda4.finchild;

import java.util.ArrayList;
import java.util.List;

public class Purchase_Details {
      String purchase_date;
      String store;
        double amount;


    Purchase_Details(String purchase_date, String store, double amount) {
        this.purchase_date = purchase_date;
        this.amount = amount;
        this.store = store;

    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public double getAmount() {
        return amount;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public String getStore() {
        return store;
    }



}


