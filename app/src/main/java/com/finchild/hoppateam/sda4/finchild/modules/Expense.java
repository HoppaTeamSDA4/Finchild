package com.finchild.hoppateam.sda4.finchild.modules;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.finchild.hoppateam.sda4.finchild.modules.Item;

public class Expense extends ExpandableGroup<Item> {
    private String expenseId;
    private String store;
    private String date;
    private double totalAmount;
    private ArrayList<Item> items= new ArrayList <>();



    public Expense(String expenseId, String store, String date, double totalAmount,List<Item> items) {
        super(store,items);
        this.expenseId = expenseId;
        this.store = store;
        this.date = date;
        this.totalAmount = totalAmount;

    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getStore() {
        return store;
    }

    public String getDate() {
        return date;
    }


    public void setExpenseId(String expensesId) {
        this.expenseId = expenseId;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
