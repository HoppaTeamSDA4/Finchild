package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class ChildSpendLimit extends AppCompatActivity {

    //view objects
    private ImageView btnBack;

    private Spinner frequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_spend_limit);

        //initializing views
        btnBack = (ImageView) findViewById(R.id.btnBack);

        frequency= (Spinner) findViewById(R.id.spinnerFrequency);

        //Create adapter to frequency spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.frequency,android.R.layout.simple_spinner_item);
        //initialize spinner
        frequency.setAdapter(adapter);

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
