package com.finchild.hoppateam.sda4.finchild;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Child> children;

    @Override
    public void onCreate() {
        super.onCreate();

        children = new ArrayList<>();

        children.add(new Child("Rambo", 340, true));
        children.add(new Child("Marcus", 960, false));
    }
}
