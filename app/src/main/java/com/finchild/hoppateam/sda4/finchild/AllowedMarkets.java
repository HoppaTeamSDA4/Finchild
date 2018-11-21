package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class AllowedMarkets extends AppCompatActivity {

    //view objects
    private ImageView btnBack;
    private Spinner markets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allowed_markets);

        //initializing views
        btnBack = (ImageView) findViewById(R.id.btnBack);
        markets= (Spinner) findViewById(R.id.spinnerMarket);

        //Create adapter to market spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.markets,android.R.layout.simple_spinner_item);
        //initialize spinner
        markets.setAdapter(adapter);
    }

    public void onClick(View view) {
        //if btnBack is pressed
        if(view == btnBack) {
            // Create an Intent to start the Control activity
            Intent backIntent = new Intent(this, Control.class);
            // Start the activity.
            startActivity(backIntent);
        }
    }
}
