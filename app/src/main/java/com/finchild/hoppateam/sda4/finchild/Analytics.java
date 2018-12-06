package com.finchild.hoppateam.sda4.finchild;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class Analytics extends ElementsBottomBarNav {

    //view objects
    private Spinner period;
    private Spinner typeData;
    private ImageView image;
    private ImageView btnBack;

    RelativeLayout prova;

    RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);


        //initializing views
        period = (Spinner) findViewById(R.id.spinnerPeriod);
        typeData = (Spinner) findViewById(R.id.spinnerTypeData);
        image = (ImageView) findViewById(R.id.imageView);

        btnBack = (ImageView) findViewById(R.id.ivBack);
        btnBack.setImageResource(R.drawable.back_button);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAccount();
            }
        });

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
                    setWidthAndHeight(getApplicationContext(), findViewById(R.id.includeAn), 500, 325);
                        image.setImageResource(R.drawable.rowchart);
                        showButton();
                    break;
            case R.id.radioLineChart:
                if (checked)

                    setWidthAndHeight(getApplicationContext(), findViewById(R.id.includeAn), 500, 325);
                    image.setImageResource(R.drawable.linechart);
                    showButton();
                    break;
            case R.id.radioPieChart:
                if (checked)
                    setWidthAndHeight(getApplicationContext(), findViewById(R.id.includeAn), 400, 325);
                    image.setImageResource(R.drawable.piechart);
                    showButton();
                    break;
        }
    }


    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_analytics);
        findViewById(R.id.topHomeFrag).getLayoutParams().width = 400;
    }



*/


    public void setWidthAndHeight(Context context, RelativeLayout view, int width, int height) {
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, context.getResources().getDisplayMetrics());//used to convert you width integer value same as dp
        height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, context.getResources().getDisplayMetrics());
        //note : if your layout is LinearLayout then use LinearLayout.LayoutParam
        view.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
    }

    private void goAccount() {
        // Create an Intent to start the AccountChildPurchases activity
        Intent intent = new Intent(this, AccountChildPurchases.class);
        // Start the activity.
        startActivity(intent);
    }

    private void showButton(){
        btnBack = (ImageView) findViewById(R.id.ivBack);
        btnBack.setImageResource(R.drawable.back_button);
        btnBack.setColorFilter(R.color.colorPrimaryDark);
    }
}
