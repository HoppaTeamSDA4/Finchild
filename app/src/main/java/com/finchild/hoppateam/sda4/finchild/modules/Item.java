package com.finchild.hoppateam.sda4.finchild.modules;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String category;
    private double quantity;
    private String name;
    private double price;

    public Item() {

    }

    protected Item(Parcel in) {
        category = in.readString();
        quantity = in.readDouble();
        name = in.readString();
        price =in.readDouble();
    }

    public Item(String category, String name, double quantity, double price) {
        this.category = category;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(quantity);
        dest.writeDouble(price);
        dest.writeString(category);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
