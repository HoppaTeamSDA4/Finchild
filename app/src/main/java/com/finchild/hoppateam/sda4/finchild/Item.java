package com.finchild.hoppateam.sda4.finchild;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    final String item_name;
    int item_qty;
    double item_price;

    public int getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(int item_qty) {
        this.item_qty = item_qty;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }



    protected Item(String item_name,int item_qty, double item_price) {
        this.item_name = item_name;
        this.item_qty = item_qty;
        this.item_price = item_price;

    }


    protected Item(Parcel in) {
        item_name = in.readString();
        item_qty = in.readInt();
        item_price = in.readDouble();
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
        dest.writeString(item_name);
        dest.writeInt(item_qty);
        dest.writeDouble(item_price);
    }
}
