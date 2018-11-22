package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.finchild.hoppateam.sda4.finchild.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity  {
    private FirebaseAuth firebaseAuth;

    //view objects
    private Button btnControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        btnControl = (Button) findViewById(R.id.btnControl);

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                //startActivity(new Intent(this, Tab2.class));
                return true;
            case R.id.setting:
                startActivity(new Intent(this, Settings.class));
                return true;
            case R.id.logout:
                //logging out the user
                firebaseAuth.signOut();
                //closing activity
                finish();
                //starting login activity
                startActivity(new Intent(this, LoginActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        //if btnSettings is pressed
        if (view == btnControl){
            // Create an Intent to start the control activity
            Intent controlIntent = new Intent(this, Control.class);
            // Start the activity.
            startActivity(controlIntent);

        }
    }
}
