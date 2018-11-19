package com.finchild.hoppateam.sda4.finchild.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.R;

public class ProfileSettingAdapter extends ArrayAdapter {
    private final Activity context;
    private  final String[] profile_menu;

    public ProfileSettingAdapter(Activity context,int resource,String [] profile_menu) {
        super(context, R.layout.childacc_listview_entry, profile_menu);
        this.context = context;
        this.profile_menu=profile_menu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.childacc_listview_entry, null, true);
        TextView profileItem = (TextView) rowView.findViewById(R.id.profileItem);
        profileItem.setText(profile_menu[position]);
        return rowView;
    }
}
