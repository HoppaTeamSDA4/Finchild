package com.finchild.hoppateam.sda4.finchild;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    List<Purchase_Details> purchases = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //purchases = new ArrayList<Purchase_Details>();
         initialiseData();


        //Create recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // create an adapter and supply the data to be displayed
        adapter = new RVAdapter(this, purchases);
        //Connect the adapter with RecyclerView
        mRecyclerView.setAdapter(adapter);
        //Give the recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Method of Initiating Data in the list, to be called for the RecyclerView
         public void initialiseData(){

        purchases.add(new Purchase_Details("1/1/2018", "ICA", 30.5));
        purchases.add(new Purchase_Details("1/2/2018", "MAXI", 20.5));

        }
    }

