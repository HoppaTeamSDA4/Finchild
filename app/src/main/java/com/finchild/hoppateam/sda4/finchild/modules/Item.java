package com.finchild.hoppateam.sda4.finchild.modules;

public class Item {

    private String category;
    private String name;
    private float price;

    public Item(){

    }

    public Item(String category, String name, float price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
