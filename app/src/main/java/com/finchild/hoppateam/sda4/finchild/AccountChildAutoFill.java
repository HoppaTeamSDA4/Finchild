package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.finchild.hoppateam.sda4.finchild.session.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountChildAutoFill extends ElementsBottomBarNav {

    //view objects
    private ImageView btnBack;

    private Spinner frequencyAutofill;
    private Spinner daysAutofill;
    private Spinner weekdaysAutofill;
    private EditText amountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        //initializing views
        btnBack = (ImageView) findViewById(R.id.btnBack);

        frequencyAutofill = (Spinner) findViewById(R.id.fillFrequency);
        daysAutofill = (Spinner) findViewById(R.id.spinnerDayAutofill);
        weekdaysAutofill = (Spinner) findViewById(R.id.spinnerWeekdayAutofill);

        //Create adapter to frequency spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.frequency, android.R.layout.simple_spinner_item);
        //initialize spinner
        frequencyAutofill.setAdapter(adapter);

        //Create adapter to days spinner
        ArrayAdapter<CharSequence> adapterDays = ArrayAdapter.createFromResource
                (this, R.array.days, android.R.layout.simple_spinner_item);
        //initialize spinner
        daysAutofill.setAdapter(adapterDays);
        daysAutofill.setEnabled(false);

        //Create adapter to weedays spinner
        ArrayAdapter<CharSequence> adapterWeekday = ArrayAdapter.createFromResource
                (this, R.array.weekdays, android.R.layout.simple_spinner_item);
        //initialize spinner
        weekdaysAutofill.setAdapter(adapterWeekday);
        weekdaysAutofill.setEnabled(false);

        amountView = (EditText) findViewById(R.id.amountAutofill);
        amountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        amountView.setText(s);
                        amountView.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    amountView.setText(s);
                    amountView.setSelection(2);
                }

                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        amountView.setText(s.subSequence(0, 1));
                        amountView.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_account_child_auto_fill;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_fill;
    }

    public void autoFillByType(View view) {
        String fillType = (String) frequencyAutofill.getSelectedItem();
        switch (fillType) {
            case "Once":
                fillForOneTime();
        }
    }

    private void fillForOneTime() {
        Session session=new Session(AccountChildAutoFill.this);
        String childAcc=session.getChildAccNo();
        String parentAcc=session.getParentAcc();
        Double amount = Double.parseDouble(amountView.getText().toString());
        DatabaseReference childAccRef= FirebaseDatabase.getInstance().getReference().child("child").child(parentAcc);
        childAccRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double fillAmount=Double.parseDouble(amountView.getText().toString());
                for(DataSnapshot childAccSnapshot:dataSnapshot.getChildren()){
                    if(childAccSnapshot.child("accountNo").getValue().toString().equals(childAcc)){
                        double currentAmount=Double.parseDouble(childAccSnapshot.child("balance").getValue().toString());
                        double totalAmount=fillAmount+currentAmount;
                        childAccRef.child(childAccSnapshot.getKey()).child("balance").setValue(totalAmount);
                        break;
                    }
                }
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        
    }


}
