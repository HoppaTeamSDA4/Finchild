package com.finchild.hoppateam.sda4.finchild.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private static SharedPreferences prefs;

    public Session(Context ctx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public void setParentAcc(String parentAcc) {
        prefs.edit().putString("parentAcc", parentAcc).commit();
    }

    public String getParentAcc() {
        String parentAcc = prefs.getString("parentAcc","");
        return parentAcc;
    }

    public void setChildAccNo(String childAccNo){
        prefs.edit().putString("childAccNo", childAccNo).commit();
    }

    public String getChildAccNo(){
        String childAccNo = prefs.getString("childAccNo","");
        return childAccNo;
    }

    public String getChildAccBalance(){
        String childAccBalance = prefs.getString("childAccBalance","");
        return childAccBalance;
    }

    public void setChildAccBalance(String childAccBalance){
        prefs.edit().putString("childAccBalance", childAccBalance).commit();
    }

    public void setChildName(String childName){
        prefs.edit().putString("childName", childName).commit();
    }

    public String getChildName(){
        String childName = prefs.getString("childName","");
        return childName;
    }

    public void clear(){
        prefs.edit().clear().commit();

    }
}