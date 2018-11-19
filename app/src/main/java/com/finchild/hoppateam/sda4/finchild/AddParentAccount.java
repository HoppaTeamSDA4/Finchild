package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddParentAccount extends AppCompatActivity {
    private EditText cardNoView;
    private EditText accountNoView;
    private Button accountBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parent_account);
        cardNoView=(EditText)findViewById(R.id.cardNo);
        accountNoView=(EditText)findViewById(R.id.accountNo);
        accountBtn=(Button)findViewById(R.id.accountBtn);

        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNo=accountNoView.getText().toString().trim();
                String accountNo=cardNoView.getText().toString().trim();
                if(TextUtils.isEmpty(cardNo)){
                    Toast.makeText(AddParentAccount.this,"Please enter CardNo",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(accountNo)){
                    Toast.makeText(AddParentAccount.this,"Please enter AccountNo",Toast.LENGTH_LONG).show();
                    return;
                }
                String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference accountRf= database.getReference().child("account");
                DatabaseReference userRf=accountRf.child(userId);
                DatabaseReference accountNoRf=userRf.child(accountNo);
                accountNoRf.child("cardNo").setValue(cardNo);
                accountNoRf.child("status").setValue(true);
                finish();

            }
        });
    }

}
