package com.finchild.hoppateam.sda4.finchild;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.finchild.hoppateam.sda4.finchild.jobScheduler.DailyFillService;
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
    private final String Tag="AccountChildAutoFill";
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        session = new Session(getApplicationContext());
        //initializing views
        btnBack = (ImageView) findViewById(R.id.ivBack);
        btnBack.setImageResource(R.drawable.back_button);

        frequencyAutofill = (Spinner) findViewById(R.id.fillFrequency);
        daysAutofill = (Spinner) findViewById(R.id.spinnerDayAutofill);
        weekdaysAutofill = (Spinner) findViewById(R.id.spinnerWeekdayAutofill);

        //Create adapter to frequency spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.frequency, android.R.layout.simple_spinner_item);
        //initialize spinner
        frequencyAutofill.setAdapter(adapter);

        frequencyAutofill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "Once":
                        daysAutofill.setEnabled(false);
                        weekdaysAutofill.setEnabled(false);
                        break;
                    case "Daily":
                        daysAutofill.setEnabled(false);
                        weekdaysAutofill.setEnabled(false);
                        break;
                    case "Weekly":
                        daysAutofill.setEnabled(false);
                        weekdaysAutofill.setEnabled(true);
                        break;
                    case "Monthly":
                        daysAutofill.setEnabled(true);
                        weekdaysAutofill.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        double fillAmount = Double.parseDouble(amountView.getText().toString());
        String fillType = (String) frequencyAutofill.getSelectedItem();
        switch (fillType) {
            case "Once":
                fillForOneTime();
                break;
            case "Daily":
                dailyAutoFill(fillAmount);
                break;
            case "Weekly":
                weeklyAutoFill(fillAmount);
                break;
            case "Monthly":
                monthlyAutoFill(fillAmount);
                break;

        }
    }

    private void fillForOneTime() {

        String childAcc = session.getChildAccNo();
        String parentAcc = session.getParentAcc();
        Double amount = Double.parseDouble(amountView.getText().toString());
        DatabaseReference childAccRef = FirebaseDatabase.getInstance().getReference().child("child").child(parentAcc);
        childAccRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double fillAmount = Double.parseDouble(amountView.getText().toString());
                for (DataSnapshot childAccSnapshot : dataSnapshot.getChildren()) {
                    if (childAccSnapshot.child("accountNo").getValue().toString().equals(childAcc)) {
                        double currentAmount = Double.parseDouble(childAccSnapshot.child("balance").getValue().toString());
                        double totalAmount = fillAmount + currentAmount;
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

    private void dailyAutoFill(double fillAmount) {

        runScheduledTask("dailyFill", fillAmount);
    }

    private void weeklyAutoFill(double fillAmount) {
        runScheduledTask("weeklyFill", fillAmount);
    }

    private void monthlyAutoFill(double fillAmount) {

        runScheduledTask("monthlyFill", fillAmount);
    }

    private void runScheduledTask(String fillType, double fillAmount) {
        DatabaseReference autofillRef = FirebaseDatabase.getInstance().getReference().child("autofill").child(session.getChildAccNo());
        System.out.println(session.getChildAccNo());
        if (fillType.equals("dailyFill")) {
            autofillRef.child(fillType).child("fillAmount").setValue(fillAmount);
            ComponentName componentName = new ComponentName(this, DailyFillService.class.getName());
            JobInfo info = new JobInfo.Builder(1, componentName)
                    .setRequiresCharging(false)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setPersisted(true).setPeriodic(15 * 60 * 1000)
                    .build();


            JobScheduler mJobScheduler = (JobScheduler)
                    getSystemService(Context.JOB_SCHEDULER_SERVICE);
            int resultCode=mJobScheduler.schedule(info);
            if(resultCode==JobScheduler.RESULT_SUCCESS){
                Log.d(Tag,"Job scheduled");
            }else{
                Log.d(Tag,"Job scheduling failed");

            }

        }

        if (fillType.equals("weeklyFill")) {
            String operationDay = (String) weekdaysAutofill.getSelectedItem();
            autofillRef.child(fillType).child("fillAmount").setValue(fillAmount);
            autofillRef.child(fillType).child("weeklyFillDay").setValue(operationDay);
        }
        if (fillType.equals("monthlyFill")) {
            String operationDay = (String) daysAutofill.getSelectedItem();
            autofillRef.child(fillType).child("fillAmount").setValue(fillAmount);
            autofillRef.child(fillType).child("monthlyFillDay").setValue(operationDay);
        }





       /* ScheduledExecutorService dailyScheduler=
                Executors.newSingleThreadScheduledExecutor();

        dailyScheduler.scheduleAtFixedRate
                (new Runnable() {
                    public void run() {

                    }
                }, initialDelay, peroid, TimeUnit.MINUTES);*/

    }

    private void runScheduledTask() {

        JobScheduler mJobScheduler = (JobScheduler)
                getSystemService(Context.JOB_SCHEDULER_SERVICE);
        mJobScheduler.cancel(1);
    }


}
