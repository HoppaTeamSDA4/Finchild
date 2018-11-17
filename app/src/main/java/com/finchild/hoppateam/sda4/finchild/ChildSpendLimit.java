package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChildSpendLimit extends AppCompatActivity {

    //view objects
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_spend_limit);

        //initializing views
        btnBack = (ImageView) findViewById(R.id.btnBack);
    }

    public void onClick(View view) {
        //if btnBack is pressed
        if (view == btnBack){
            // Create an Intent to start the control activity
            Intent backIntent = new Intent(this, Control.class);
            // Start the activity.
            startActivity(backIntent);
        }

    }
}
