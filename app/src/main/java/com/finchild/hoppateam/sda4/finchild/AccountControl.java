package com.finchild.hoppateam.sda4.finchild;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.finchild.hoppateam.sda4.finchild.session.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountControl extends AppCompatActivity {

    //view objects
    private ImageView backBtn;
    private Switch switchDisable;
    private Button confirmDeleteButton;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);
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

        switchDisable = (Switch) findViewById(R.id.switchDisableAccount);
        confirmDeleteButton = (Button) findViewById(R.id.confirmDeleteAccount);
        final Session session = new Session(AccountControl.this);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference().child("child").child(session.getParentAcc());
        accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot accountShot : dataSnapshot.getChildren()) {
                    if (dataSnapshot.hasChildren()) {
                        if (accountShot.child("name").getValue().toString().equals(session.getChildName())) {
                            String status = accountShot.child("status").getValue().toString().trim();
                            switchDisable.setChecked(Boolean.valueOf(status));
                        }
                    } else {
                        Toast.makeText(AccountControl.this, "There is no Childto control", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        switchDisable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {

                accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot accountShot : dataSnapshot.getChildren()) {
                            if (dataSnapshot.hasChildren()) {
                                if (accountShot.child("name").getValue().toString().equals(session.getChildName())) {
                                    String childAccounKey = accountShot.getKey();
                                    accountRef.child(childAccounKey).child("status").setValue(switchDisable.isChecked());
                                    if (!switchDisable.isChecked())
                                        Toast.makeText(AccountControl.this, "The account is disabled", Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(AccountControl.this, "The account is enabled", Toast.LENGTH_SHORT).show();
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
        confirmDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AccountControl.this);
                builder.setTitle("Delete Confirmation !");
                builder.setMessage("You are about to delete the account. Do you really want to proceed ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        accountRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot accountShot : dataSnapshot.getChildren()) {
                                    if (accountShot.child("name").getValue().toString().equals(session.getChildName())) {
                                        String childAccounKey = accountShot.getKey();
                                        accountRef.child(childAccounKey).removeValue();
                                        finish();
                                        Intent intent = new Intent(AccountControl.this,HomeActivity.class);
                                        // Start the activity.
                                        startActivity(intent);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });

    }

    private void goAccount() {
        // Create an Intent to start the Control activity
        Intent intent = new Intent(this, Control.class);
        // Start the activity.
        startActivity(intent);
    }

}
