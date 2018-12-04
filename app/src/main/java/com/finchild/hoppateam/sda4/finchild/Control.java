package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Control extends ElementsBottomBarNav {

    //view objects
    private Button btnSpendLimit;
    private Button btnAutofill;
    private Button btnAccountControl;
    private Button btnAllowedMarkets;
    private Button btnNotifications;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        //initializing views
        btnSpendLimit = (Button) findViewById(R.id.btnSpendLimit);
        btnAutofill = (Button) findViewById(R.id.btnAutofill);
        btnAccountControl = (Button) findViewById(R.id.btnAccountControl);
        btnAllowedMarkets = (Button) findViewById(R.id.btnAllowedMarkets);
        btnNotifications = (Button) findViewById(R.id.btnNotifications);

        backBtn = (ImageView) findViewById(R.id.ivBack);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAccount();
            }
        });

        // to set the back button instead of the logout
        backBtn.setImageResource(R.drawable.back_button);

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_control;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_control;
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
            // Create an Intent to start the AccountChildAutoFill activity
            Intent autofillIntent = new Intent(this, AccountChildAutoFill.class);
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
    }

    private void goAccount() {
        // Create an Intent to start the AccountChildPurchases activity
        Intent intent = new Intent(this, AccountChildPurchases.class);
        // Start the activity.
        startActivity(intent);
    }
}
