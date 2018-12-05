package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class AddParentAccount extends AppCompatActivity {
    private EditText cardNoView;
    private EditText accountNoView;
    private EditText fullnameView;
    private TextView showNameView;
    private TextView showCardNoView;
    private TextView showAccNoView;
    private Button confirmBtn;
    private Button deleteBtn;
    private ImageView backBtn;
    private ImageView btnSettings;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent_account);
        cardNoView = (EditText) findViewById(R.id.cardNo);
        accountNoView = (EditText) findViewById(R.id.accountNo);
        fullnameView = (EditText) findViewById(R.id.fullname);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);
        showNameView = (TextView) findViewById(R.id.showName);
        showCardNoView = (TextView) findViewById(R.id.showCardNo);
        showAccNoView = (TextView) findViewById(R.id.showAccNo);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        backBtn = (ImageView) findViewById(R.id.ivBack);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // to set the back button instead of the logout
        backBtn.setImageResource(R.drawable.back_button);
        btnSettings = (ImageView) findViewById(R.id.ivSettings);
        btnSettings.setImageResource(0);
        checkAccount();


    }

    private void checkAccount() {

        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference().child("account").child(userId);
        accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    fullnameView.setVisibility((View.INVISIBLE));
                    cardNoView.setVisibility(View.INVISIBLE);
                    accountNoView.setVisibility(View.INVISIBLE);
                    confirmBtn.setVisibility(View.INVISIBLE);
                    for(DataSnapshot accountShot:dataSnapshot.getChildren()){
                        showNameView.setText("Name: "+accountShot.child("name").getValue());
                        showCardNoView.setText("Card Number: "+accountShot.child("cardNo").getValue());
                        showAccNoView.setText("Account Number: "+accountShot.getKey());
                    }
                    deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseDatabase.getInstance().getReference().child("account").child(userId).removeValue();
                            finish();
                        }
                    });
                } else {
                    showNameView.setVisibility(View.INVISIBLE);
                    showCardNoView.setVisibility(View.INVISIBLE);
                    showAccNoView.setVisibility(View.INVISIBLE);
                    deleteBtn.setVisibility(View.INVISIBLE);
                    confirmBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String fullname = fullnameView.getText().toString().trim();
                            String cardNo = cardNoView.getText().toString().trim();
                            String accountNo = accountNoView.getText().toString().trim();

                            if (TextUtils.isEmpty(fullname)) {
                                Toast.makeText(AddParentAccount.this, "Please enter fullname", Toast.LENGTH_LONG).show();
                                return;
                            }

                            if (TextUtils.isEmpty(cardNo)) {
                                Toast.makeText(AddParentAccount.this, "Please enter CardNo", Toast.LENGTH_LONG).show();
                                return;
                            }

                            if (TextUtils.isEmpty(accountNo)) {
                                Toast.makeText(AddParentAccount.this, "Please enter AccountNo", Toast.LENGTH_LONG).show();
                                return;
                            }
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference accountRf = database.getReference().child("account");
                            DatabaseReference userRf = accountRf.child(userId);
                            DatabaseReference accountNoRf = userRf.child(accountNo);
                            accountNoRf.child("name").setValue(fullname);
                            accountNoRf.child("cardNo").setValue(cardNo);
                            accountNoRf.child("status").setValue(true);
                            finish();

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}