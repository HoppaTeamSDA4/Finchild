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

    public void setChildDailyLimitAmount(String dailyLimit) {
        prefs.edit().putString("dailyLimitAmount", dailyLimit).commit();
    }

    public String getChildDailyLimitAmount() {
        String dailyLimit = prefs.getString("dailyLimitAmount", "");
        return dailyLimit;
    }
    public void setChildWeeklyLimitAmount(String weeklyLimit) {
        prefs.edit().putString("weeklyLimitAmount", weeklyLimit).commit();
    }
    public String getChildWeeklyLimitAmount() {
        String weeklyLimit = prefs.getString("weeklyLimitAmount", "");
        return weeklyLimit;
    }

    public String getChildMonthlyLimitAmount() {
        String monthlyLimit = prefs.getString("monthlyLimitAmount", "");
        return monthlyLimit;
    }
    public void setChildMonthlyLimitAmount(String monthlyLimit) {
        prefs.edit().putString("monthlyLimitAmount", monthlyLimit).commit();
    }

    public void clear(){
        prefs.edit().clear().commit();

    }

    public void setChildWeeklyStat(String s) {
        prefs.edit().putString("weeklyLimitStat", "weeklyLimitStat").commit();
    }

    public void setChildDailyLimitStat(String s) {
        prefs.edit().putString("dailyLimitStat", "dailyLimitStat").commit();
    }

    public void setChildMonthlyyLimitStat(String s) {
        prefs.edit().putString("monthlyimitStat", "monthlyLimitStat").commit();
    }

    public String getChildWeeklyLimitStat() {
        String weeklyStat = prefs.getString("weeklyLimitStat", "");
        return weeklyStat;
    }

    public String getChildMonthlyLimitStat() {
        String monthlyStat = prefs.getString("monthlyLimitStat", "");
        return monthlyStat;
    }

    public String getChildDailyLimitStat() {
        String dailyStat = prefs.getString("dailyLimitStat", "");
        return dailyStat;
    }


}