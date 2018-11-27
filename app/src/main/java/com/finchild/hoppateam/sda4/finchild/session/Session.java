package com.finchild.hoppateam.sda4.finchild.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

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
}