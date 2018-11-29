package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

public class Analytics extends ElementsBottomBarNav {

    //view objects
    private Spinner period;
    private Spinner typeData;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //initializing views
        period = (Spinner) findViewById(R.id.spinnerPeriod);
        typeData = (Spinner) findViewById(R.id.spinnerTypeData);
        image = (ImageView) findViewById(R.id.imageDataGraphic);

        //Create adapter to period spinner
        ArrayAdapter<CharSequence> periodAdapter = ArrayAdapter.createFromResource
                (this,R.array.frequency,android.R.layout.simple_spinner_item);
        //initialize spinner
        period.setAdapter(periodAdapter);

        //Create adapter to typeData spinner
        ArrayAdapter<CharSequence> typeDataAdapter = ArrayAdapter.createFromResource
                (this,R.array.typeData,android.R.layout.simple_spinner_item);
        //initialize spinner
        typeData.setAdapter(typeDataAdapter);

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_analytics;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_analytics;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioRowChart:
                if (checked)
                    image.setImageResource(R.drawable.rowchart);
                    break;
            case R.id.radioLineChart:
                if (checked)
                    image.setImageResource(R.drawable.linechart);
                    break;
            case R.id.radioPieChart:
                if (checked)
                    image.setImageResource(R.drawable.piechart);
                    break;
        }
    }
}
