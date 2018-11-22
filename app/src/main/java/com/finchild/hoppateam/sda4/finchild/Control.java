package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Control extends AppCompatActivity {

    //view objects
    private Button btnSpendLimit;
    private Button btnAutofill;
    private Button btnAccountControl;
    private Button btnAllowedMarkets;
    private Button btnNotifications;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        //initializing views
        btnSpendLimit = (Button) findViewById(R.id.btnSpendLimit);
        btnAutofill = (Button) findViewById(R.id.btnAutofill);
        btnAccountControl = (Button) findViewById(R.id.btnAccountControl);
        btnAllowedMarkets = (Button) findViewById(R.id.btnAllowedMarkets);
        btnNotifications = (Button) findViewById(R.id.btnNotifications);
        btnBack = (ImageView) findViewById(R.id.btnBack);
    }

    public void onClick(View view) {
        //if btnSpendLimit is pressed
        if (view == btnSpendLimit){
            // Create an Intent to start the ChildSpendLimit activity
            Intent spendLimitIntent = new Intent(this, ChildSpendLimit.class);
            // Start the activity.
            startActivity(spendLimitIntent);
        }
        //if btnAutofill is pressed
        if (view == btnAutofill){
            // Create an Intent to start the Autofill activity
            Intent autofillIntent = new Intent(this, Autofill.class);
            // Start the activity.
            startActivity(autofillIntent);
        }
        //if btnAccountControl is pressed
        if (view == btnAccountControl){
            // Create an Intent to start the AccountControl activity
            Intent accountControlIntent = new Intent(this, AccountControl.class);
            // Start the activity.
            startActivity(accountControlIntent);
        }
        //if btnAllowedMarkets is pressed
        if (view == btnAllowedMarkets){
            // Create an Intent to start the AllowedMarkets activity
            Intent allowedMarketsIntent = new Intent(this, AllowedMarkets.class);
            // Start the activity.
            startActivity(allowedMarketsIntent);
        }
        //if btnNotifications is pressed
        if (view == btnNotifications){
            // Create an Intent to start the Notifications activity
            Intent notificationsIntent = new Intent(this, Notifications.class);
            // Start the activity.
            startActivity(notificationsIntent);
        }
        //if btnBack is pressed
        if (view == btnBack){
            // Create an Intent to start the ProfileActivity activity
            Intent profileActivityIntent = new Intent(this, HomeActivity.class);
            // Start the activity.
            startActivity(profileActivityIntent);
        }
    }
}