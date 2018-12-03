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

    private ImageView backBtn;
    private Button confirm;
    private EditText amountSpendLimits;


    private ImageView btnSettings;


    private Spinner frequency;
    private String limitType;
    private String limitAmount;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_spend_limit);

        //initializing views
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

        //initializing views
        backBtn = (ImageView) findViewById(R.id.ivBack);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAccount();
            }
        });

        // to set the back button instead of the logout
        backBtn.setImageResource(R.drawable.back_button);


        confirm=(Button) findViewById(R.id.confirm);

        frequency= (Spinner) findViewById(R.id.spinnerFrequency);
        amountSpendLimits=(EditText) findViewById(R.id.amountSpendLimit);

        //Create adapter to frequency spinner
         final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.limit,android.R.layout.simple_spinner_item);
        //initialize spinner
        frequency.setAdapter(adapter);
        frequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
                limitType=frequency.getItemAtPosition(position).toString();
            }


            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                limitType=null;
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limitAmount=amountSpendLimits.getText().toString();
                final Session session = new Session(ChildSpendLimit.this);
                final DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference().child("child").child(session.getParentAcc());
                accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot accountShot : dataSnapshot.getChildren()) {
                            if (accountShot.child("name").getValue().toString().equals(session.getChildName())) {
                                String childAccounKey = accountShot.getKey();
                                if (TextUtils.isEmpty(limitAmount)) {
                                    Toast.makeText(ChildSpendLimit.this, "Please enter the amount", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                //accountRef.child(childAccounKey).child("limitAmount").setValue(limitAmount);
                                //accountRef.child(childAccounKey).child("limitType").setValue(limitType);
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

    private void goAccount() {
        // Create an Intent to start the Control activity
        Intent intent = new Intent(this, Control.class);
        // Start the activity.
        startActivity(intent);
    }

    public String selectSwitchLimit(String limitType){

        String limitField=null;
        switch(limitType){
            case "Daily":
                limitField="dailyLimit";
                break;
            case"weekly":
                limitField="weeklyLimit";
                break;
            case"Monthly":
                limitField="monthlyLimit";
                break;
            default:
                break;
        }

        return limitField;
    }
}
