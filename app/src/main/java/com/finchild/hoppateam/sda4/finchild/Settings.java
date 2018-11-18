package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.adapter.ProfileSettingAdapter;

public class Settings extends AppCompatActivity {
    private final String[] profile_menu={"Change Password","Add Card and Account",};
    private TextView item_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ListView lv = (ListView)findViewById(R.id.childAccList);
        Button btnBack = (Button)findViewById(R.id.btn_settings_back);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item_view= (TextView) view.findViewById(R.id.profileItem);
                switch(item_view.getText().toString()){
                    case "Add Card and Account":
                        startActivity(new Intent(Settings.this, AddParentAccount.class));
                }
            }
        });
        ProfileSettingAdapter adapter=new ProfileSettingAdapter(this,R.layout.childacc_listview_entry,profile_menu);
        lv.setAdapter(adapter);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}
