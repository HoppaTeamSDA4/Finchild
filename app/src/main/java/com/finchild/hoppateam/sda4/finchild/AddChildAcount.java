package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.finchild.hoppateam.sda4.finchild.modules.ChildAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddChildAcount extends AppCompatActivity {

    private Button confirmBtn;
    private ImageView backBtn;
    private EditText childNameView;
    private EditText childCardNoView;
    private EditText childAccNoView;
    private EditText childPersonalNoView;
    private EditText childMobileView;
    private String parentAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_acount);
        confirmBtn = (Button) findViewById(R.id.addChildBtn);
        backBtn=(ImageView) findViewById(R.id.ivBack);
        // to set the back button instead of the logout
        backBtn.setImageResource(R.drawable.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChildAccount();
                finish();
            }
        });

    }

    private void addChildAccount() {
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println(userId);
        final DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference().child("account").child(userId);

        accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot accshot : dataSnapshot.getChildren()) {
                    parentAcc = accshot.getKey();
                }
                DatabaseReference childAccRef = FirebaseDatabase.getInstance().getReference().child("child");
                childNameView = (EditText) findViewById(R.id.childName);
                childCardNoView = (EditText) findViewById(R.id.childCardNo);
                childAccNoView = (EditText) findViewById(R.id.childAccNo);
                childPersonalNoView = (EditText) findViewById(R.id.childPersonalNo);
                childMobileView = (EditText) findViewById(R.id.childMobile);
                String childName = childNameView.getText().toString().trim();
                String childCardNo = childCardNoView.getText().toString().trim();
                String childAccNo = childAccNoView.getText().toString().trim();
                String childPersonalNo = childPersonalNoView.getText().toString().trim();
                String childMobile = childMobileView.getText().toString().trim();
                if (TextUtils.isEmpty(childName)) {
                    Toast.makeText(AddChildAcount.this, "Please enter child's name", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(childCardNo)) {
                    Toast.makeText(AddChildAcount.this, "Please enter child card number", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(childAccNo)) {
                    Toast.makeText(AddChildAcount.this, "Please enter child account number", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(childPersonalNo)) {
                    Toast.makeText(AddChildAcount.this, "Please enter child'personal number", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(childMobile)) {
                    Toast.makeText(AddChildAcount.this, "Please enter child's mobile number", Toast.LENGTH_LONG).show();
                    return;
                }

                ChildAccount newChildAcc= new ChildAccount(childAccNo,childCardNo,childName,childPersonalNo,childMobile,0.00,true);
                childAccRef.child(parentAcc).push().setValue(newChildAcc);


                //childAccRef.child(parentAcc).child(childAccNo).child("card number").setValue(childCardNo);
               // childAccRef.child(parentAcc).child(childAccNo).child("mobile number").setValue(childMobile);
                //childAccRef.child(parentAcc).child(childAccNo).child("name").setValue(childName);
                //childAccRef.child(parentAcc).child(childAccNo).child("personal number").setValue(childPersonalNo);
                //childAccRef.child(parentAcc).child(childAccNo).child("status").setValue(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.print("error");
            }
        });


    }


}
