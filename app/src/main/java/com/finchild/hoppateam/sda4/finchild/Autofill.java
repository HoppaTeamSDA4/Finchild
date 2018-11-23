package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Autofill extends AppCompatActivity {

    //view objects
    private ImageView btnBack;

    private Spinner frequencyAutofill;
    private Spinner daysAutofill;
    private Spinner weekdaysAutofill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autofill);

        //initializing views
        btnBack = (ImageView) findViewById(R.id.btnBack);

        frequencyAutofill= (Spinner) findViewById(R.id.spinnerFrequencyAutofill);
        daysAutofill= (Spinner) findViewById(R.id.spinnerDayAutofill);
        weekdaysAutofill= (Spinner) findViewById(R.id.spinnerWeekdayAutofill);

        //Create adapter to frequency spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.frequency,android.R.layout.simple_spinner_item);
        //initialize spinner
        frequencyAutofill.setAdapter(adapter);

        //Create adapter to days spinner
        ArrayAdapter<CharSequence> adapterDays = ArrayAdapter.createFromResource
                (this,R.array.days,android.R.layout.simple_spinner_item);
        //initialize spinner
        daysAutofill.setAdapter(adapterDays);

        //Create adapter to weedays spinner
        ArrayAdapter<CharSequence> adapterWeekday = ArrayAdapter.createFromResource
                (this,R.array.weekdays,android.R.layout.simple_spinner_item);
        //initialize spinner
        weekdaysAutofill.setAdapter(adapterWeekday);
    }

    public void onClick(View view) {
        //if btnBack is pressed
        if (view == btnBack){
            // Create an Intent to start the Control activity
            Intent backIntent = new Intent(this, Control.class);
            // Start the activity.
            startActivity(backIntent);
        }
    }
}
