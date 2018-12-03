package com.finchild.hoppateam.sda4.finchild.jobScheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.annotation.NonNull;
import android.util.Log;

import com.finchild.hoppateam.sda4.finchild.AccountChildAutoFill;
import com.finchild.hoppateam.sda4.finchild.AccountChildPurchases;
import com.finchild.hoppateam.sda4.finchild.session.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DailyFillService extends JobService {
    private static final String Tag = "DailyFillService";
    private boolean jobCancelled = false;
    Session session;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(Tag, "Job Started");
        doJob(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(Tag, "Job cancelled before completion");
        jobCancelled = true;
        return true;
    }


    private void doJob(JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                session = new Session(getApplicationContext());
                final String parentAcc = session.getParentAcc();
                System.out.println(parentAcc);
                final String childAcc = session.getChildAccNo();
                System.out.println(childAcc);
                final DatabaseReference autoFillRef = FirebaseDatabase.getInstance().getReference().child("autofill").child(childAcc).child("dailyFill");
                autoFillRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Double childAccFill = Double.parseDouble(dataSnapshot.child("fillAmount").getValue().toString());
                        final DatabaseReference childAccRef = FirebaseDatabase.getInstance().getReference().child("child").child(parentAcc);
                        childAccRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                    if (childSnapshot.child("accountNo").getValue().equals(childAcc)) {
                                        String childAccKey = childSnapshot.getKey();
                                        Double childAccBalance = Double.parseDouble(childSnapshot.child("balance").getValue().toString());
                                        Double total = childAccBalance + childAccFill;
                                        childAccRef.child(childAccKey).child("balance").setValue(total);
                                        System.out.println("autofill successful");
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                Log.d(Tag, "Job finished");
                jobFinished(params, false);
            }
        }).start();
    }


}
