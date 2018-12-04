package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.finchild.hoppateam.sda4.finchild.session.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChildSpendLimit extends AppCompatActivity {

    //view objects
    private ImageView btnBack;
    private Button confirm;
    private EditText amountSpendLimits;
    private Spinner frequency;
    private String limitType;
    private double limitAmount;
    //private TextView limitMessage;
    //private Session session;
    private CheckBox dailycheck;
    private CheckBox weeklycheck;
    private CheckBox monthlycheck;
    private Button resetSpendingLimits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_spend_limit);

        //initializing views
        btnBack = (ImageView) findViewById(R.id.btnBack);
        confirm = (Button) findViewById(R.id.confirm);
        frequency = (Spinner) findViewById(R.id.spinnerFrequency);
        amountSpendLimits = (EditText) findViewById(R.id.amountSpendLimit);
        //limitMessage=(TextView) findViewById(R.id.statusLimitMessage);
        dailycheck = (CheckBox) findViewById(R.id.dailyCheckBox);
        weeklycheck = (CheckBox) findViewById(R.id.weeklyCheckBox);
        monthlycheck = (CheckBox) findViewById(R.id.monthlyCheckBox);
        resetSpendingLimits = (Button) findViewById(R.id.resetLimits);

        //Create adapter to frequency spinner
        final ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.limit, android.R.layout.simple_spinner_item);

        //initialize spinner
        frequency.setAdapter(adapter);

        //initialize Session
        final Session session = new Session(ChildSpendLimit.this);

        //initialize DatabaseReference
        final DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference().child("child").child(session.getParentAcc());

        //Geting the spinner value
        frequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
                limitType = frequency.getItemAtPosition(position).toString();
                accountRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot accountShot : dataSnapshot.getChildren()) {
                            if (accountShot.child("name").getValue().toString().equals(session.getChildName())) {
                                amountSpendLimits.setText(accountShot.child(limitType.toLowerCase() + "LimitAmount").getValue().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                limitType = null;
            }
        });

        accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot accountShot : dataSnapshot.getChildren()) {
                    if (accountShot.child("name").getValue().toString().equals(session.getChildName())) {
                       /* String daily = "Daily: " + accountShot.child("dailyLimitAmount").getValue().toString();
                        String weekly = "Weekly: " + accountShot.child("weeklyLimitAmount").getValue().toString();
                        String monthly = "Monthly: " + accountShot.child("monthlyLimitAmount").getValue().toString();*/

                   /*     //Check if there is no spending limit
                        if(!Boolean.valueOf(accountShot.child("dailyLimit").getValue().toString())&&
                           !Boolean.valueOf(accountShot.child("weeklyLimit").getValue().toString())&&
                           !Boolean.valueOf(accountShot.child("monthlyLimit").getValue().toString()))
                            limitMessage.setText("There is no spending limits.");
                        // check if there are spending limits
                        else limitMessage.setText("The spending limits are:\n"+
                                displayLimit(accountShot.child("dailyLimit").getValue().toString(),daily)+
                                displayLimit(accountShot.child("weeklyLimit").getValue().toString(),weekly)+
                                displayLimit(accountShot.child("monthlyLimit").getValue().toString(),monthly));*/
                        if (accountShot.child("dailyLimit").getValue().toString().equals("true")) {
                            dailycheck.setVisibility(View.VISIBLE);
                            dailycheck.setText("Daily: " + accountShot.child("dailyLimitAmount").getValue().toString() + " kr");
                        }
                        if (Boolean.valueOf(accountShot.child("weeklyLimit").getValue().toString())) {
                            weeklycheck.setVisibility(View.VISIBLE);
                            weeklycheck.setText("Weekly: " + accountShot.child("weeklyLimitAmount").getValue().toString() + " kr");
                        }
                        if (Boolean.valueOf(accountShot.child("monthlyLimit").getValue().toString())) {
                            monthlycheck.setVisibility(View.VISIBLE);
                            monthlycheck.setText("Monthly: " + accountShot.child("monthlyLimitAmount").getValue().toString() + " kr");
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        resetSpendingLimits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot accountShot : dataSnapshot.getChildren()) {
                            if (accountShot.child("name").getValue().toString().equals(session.getChildName())) {
                                String childAccounKey = accountShot.getKey();
                                if (dailycheck.isChecked()) {
                                    accountRef.child(childAccounKey).child("dailyLimitAmount").setValue(0);
                                    accountRef.child(childAccounKey).child("dailyLimit").setValue(false);
                                    dailycheck.setChecked(false);
                                    dailycheck.setVisibility(View.GONE);
                                }
                                if (weeklycheck.isChecked()) {
                                    accountRef.child(childAccounKey).child("weeklyLimitAmount").setValue(0);
                                    accountRef.child(childAccounKey).child("weeklyLimit").setValue(false);
                                    weeklycheck.setChecked(false);
                                    weeklycheck.setVisibility(View.GONE);
                                }
                                if (monthlycheck.isChecked()) {
                                    accountRef.child(childAccounKey).child("monthlyLimitAmount").setValue(0);
                                    accountRef.child(childAccounKey).child("monthlyLimit").setValue(false);
                                    monthlycheck.setChecked(false);
                                    monthlycheck.setVisibility(View.GONE);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        //Setting the limit in database
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limitAmount = Double.valueOf(amountSpendLimits.getText().toString());

                accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot accountShot : dataSnapshot.getChildren()) {
                            if (accountShot.child("name").getValue().toString().equals(session.getChildName())) {
                                String childAccounKey = accountShot.getKey();
                                if (limitAmount == 0) {
                                    Toast.makeText(ChildSpendLimit.this, "Please enter the amount", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                if (TextUtils.isEmpty(limitType)) {
                                    Toast.makeText(ChildSpendLimit.this, "Please enter the type of the limit", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                String limitField = selectSwitchLimit(limitType);


                                accountRef.child(childAccounKey).child(limitField + "Amount").setValue(limitAmount);
                                accountRef.child(childAccounKey).child(limitField).setValue(true);
                                if (limitType.equals("Daily")) {
                                    dailycheck.setChecked(false);
                                    dailycheck.setVisibility(View.VISIBLE);
                                    dailycheck.setText(limitType + ": " + limitAmount + " kr");
                                } else if (limitType.equals("Weekly")) {
                                    weeklycheck.setChecked(false);
                                    weeklycheck.setVisibility(View.VISIBLE);
                                    weeklycheck.setText(limitType + ": " + limitAmount + " kr");
                                } else if (limitType.equals("Monthly")) {
                                    monthlycheck.setChecked(false);
                                    monthlycheck.setVisibility(View.VISIBLE);
                                    monthlycheck.setText(limitType + ": " + limitAmount + " kr");
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    public void onClick(View view) {
        //if btnBack is pressed
        if (view == btnBack) {
            // Create an Intent to start the control activity
            Intent backIntent = new Intent(this, Control.class);
            // Start the activity.
            startActivity(backIntent);
        }

    }

    public String selectSwitchLimit(String limitType) {

        String limitField = null;
        switch (limitType) {
            case "Daily":
                limitField = "dailyLimit";
                break;
            case "Weekly":
                limitField = "weeklyLimit";
                break;
            case "Monthly":
                limitField = "monthlyLimit";
                break;
            default:
                break;
        }

        return limitField;
    }

    public String displayLimit(String status, String result) {
        if (!Boolean.valueOf(status)) return "";
        else return result + " kr\n";
    }

}
