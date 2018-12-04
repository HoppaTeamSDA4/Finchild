package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class AllowedMarkets extends AppCompatActivity {

    //view objects
    private ImageView backBtn;
    private ImageView btnSettings;
    private Spinner markets;
    private Button btnShowMap;
    private Button btnShowList;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allowed_markets);

        //initializing views
        markets = (Spinner) findViewById(R.id.spinnerMarket);
        btnShowMap = (Button) findViewById(R.id.btnShowMap);
        btnShowList = (Button) findViewById(R.id.btnShowList);
        image = (ImageView) findViewById(R.id.imageView1);

        backBtn = (ImageView) findViewById(R.id.ivBack);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAccount();
            }
        });

        // to set the back button instead of the logout
        backBtn.setImageResource(R.drawable.back_button);
        btnSettings = (ImageView) findViewById(R.id.ivSettings);
        btnSettings.setImageResource(0);

        //Create adapter to market spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.markets, android.R.layout.simple_spinner_item);
        //initialize spinner
        markets.setAdapter(adapter);
    }

    public void onClick(View view) {
        //if btnShowList is pressed
        if (view == btnShowList) {
            //Show a logo image
            image.setImageResource(R.drawable.list);
        }
        //if btnShowMap is pressed
        if (view == btnShowMap) {
            //Show a map
            image.setImageResource(R.drawable.mapmarkets);
        }
    }

    private void goAccount() {
        // Create an Intent to start the Control activity
        Intent intent = new Intent(this, Control.class);
        // Start the activity.
        startActivity(intent);
    }
}
