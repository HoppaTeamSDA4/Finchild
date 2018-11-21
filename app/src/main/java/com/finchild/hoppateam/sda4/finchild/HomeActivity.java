package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.finchild.hoppateam.sda4.finchild.adapter.ChildAdapter;
import com.finchild.hoppateam.sda4.finchild.login.LoginActivity;
import com.finchild.hoppateam.sda4.finchild.modules.ChildDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private ImageView backBtn;
    private ImageView btnSettings;
    private Button btnAddChild;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    List<ChildDetails> children = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        backBtn=(ImageView) findViewById(R.id.ivBack);
        btnSettings = (ImageView) findViewById(R.id.ivSettings);
        btnAddChild= (Button) findViewById(R.id.btnAddChild);
        backBtn.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnAddChild.setOnClickListener(this);
        //purchases = new ArrayList<Purchase_Details>();
        initialiseData();


        //Create recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.childrenList);
        // create an adapter and supply the data to be displayed
        adapter = new ChildAdapter(this, children);
        //Connect the adapter with RecyclerView
        mRecyclerView.setAdapter(adapter);
        //Give the recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
    }

    public void onClick(View view) {
        //if btnSettings is pressed

        if(view==btnAddChild){
             Intent settingsIntent = new Intent(this, AddChildAcount.class);
             // Start the activity.                                                  
             startActivity(settingsIntent);                                          
                                                                                     
        }

        if(view==btnSettings) {
            startActivity(new Intent(this, Settings.class));
        }

        if(view==backBtn){
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    // Method of Initiating Data in the list, to be called for the RecyclerView
    public void initialiseData(){

        children.add(new ChildDetails("Gloria", "2500 Kr", 0));
        children.add(new ChildDetails("Adam", "1000 Kr", 1));
        children.add(new ChildDetails("Gloria", "2500 Kr", 0));
        children.add(new ChildDetails("Adam", "1000 Kr", 1));
        children.add(new ChildDetails("Gloria", "2500 Kr", 0));
        children.add(new ChildDetails("Adam", "1000 Kr", 1));
        children.add(new ChildDetails("Gloria", "2500 Kr", 0));
        children.add(new ChildDetails("Adam", "1000 Kr", 1));
        children.add(new ChildDetails("Gloria", "2500 Kr", 0));
        children.add(new ChildDetails("Adam", "1000 Kr", 1));

    }
}
