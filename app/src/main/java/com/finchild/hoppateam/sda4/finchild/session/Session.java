package com.finchild.hoppateam.sda4.finchild.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private static SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setParentAcc(String parentAcc) {
        prefs.edit().putString("parentAcc", parentAcc).commit();
    }

    public String getParentAcc() {
        String parentAcc = prefs.getString("parentAcc","");
        return parentAcc;
    }

    public void setChildName(String childName){
        prefs.edit().putString("childName", childName).commit();
    }

    public String getChildName(){
        String childName = prefs.getString("childName","");
        return childName;
    }
    public void setChildDailyLimit(String dailyLimit) {
        prefs.edit().putString("dailyLimit", dailyLimit).commit();
    }

    public String getChildDailyLimit() {
        String dailyLimit = prefs.getString("dailyLimit","");
        return dailyLimit;
    }
}