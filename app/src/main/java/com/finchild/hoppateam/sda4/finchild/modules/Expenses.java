package com.finchild.hoppateam.sda4.finchild.modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Expenses {

    private String expensesId;
    private String store;
    private SimpleDateFormat date;
    private ArrayList<Item> items= new ArrayList <>();

    public Expenses() {
    }

    public Expenses(String expensesId, String store, SimpleDateFormat date, ArrayList <Item> items) {
        this.expensesId = expensesId;
        this.store = store;
        this.date = date;
        this.items = items;
    }

    public String getExpensesId() {
        return expensesId;
    }

    public String getStore() {
        return store;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public ArrayList <Item> getItems() {
        return items;
    }

    public void setExpensesId(String expensesId) {
        this.expensesId = expensesId;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    public void setItems(ArrayList <Item> items) {
        this.items = items;
    }
}
