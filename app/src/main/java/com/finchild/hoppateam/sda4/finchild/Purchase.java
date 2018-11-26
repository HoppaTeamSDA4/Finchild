package com.finchild.hoppateam.sda4.finchild;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Purchase extends ExpandableGroup<Item> {

        String store;
        String purchase_date;
        double amount;

    public Purchase(String store,String purchase_date, double amount, List<Item> items) {
        super(store,items);
        this.amount= amount;
        this.purchase_date= purchase_date;
        this.store=store;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
