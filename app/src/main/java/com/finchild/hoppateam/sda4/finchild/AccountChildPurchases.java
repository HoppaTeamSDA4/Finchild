package com.finchild.hoppateam.sda4.finchild;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.adapter.PurchaseAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AccountChildPurchases extends AppCompatActivity {

    private ImageView backBtn;
    private TextView tvChildPurchase, tvBalancePurchase;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    List<PurchaseDetails> purchases = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_child_purchases);
        tvChildPurchase = (TextView) findViewById(R.id.tvChildPurchase);
        tvBalancePurchase = (TextView) findViewById(R.id.tvBalancePurchase);
        backBtn=(ImageView) findViewById(R.id.ivBack);
        // to set the back button instead of the logout
        backBtn.setImageResource(R.drawable.back_button);

        //pass here name from the home activity the name and the balance of the child
        tvChildPurchase.setText("Gloria");
        tvBalancePurchase.setText("1200 kr");



        //purchases = new ArrayList<Purchase_Details>();
        initialiseData();


        //Create recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // create an adapter and supply the data to be displayed
        adapter = new PurchaseAdapter( this, purchases);
        //Connect the adapter with RecyclerView
        mRecyclerView.setAdapter(adapter);
        //Give the recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Method of Initiating Data in the list, to be called for the RecyclerView
    public void initialiseData() {

        purchases.add(new PurchaseDetails("1/1/2018", "ICA", 30.5));
        purchases.add(new PurchaseDetails("1/2/2018", "MAXI", 20.5));
        purchases.add(new PurchaseDetails("1/1/2018", "ICA", 30.5));
        purchases.add(new PurchaseDetails("1/2/2018", "MAXI", 20.5));
        purchases.add(new PurchaseDetails("1/1/2018", "ICA", 30.5));
        purchases.add(new PurchaseDetails("1/2/2018", "MAXI", 20.5));
        purchases.add(new PurchaseDetails("1/1/2018", "ICA", 30.5));
        purchases.add(new PurchaseDetails("1/2/2018", "MAXI", 20.5));
        purchases.add(new PurchaseDetails("1/1/2018", "ICA", 30.5));
        purchases.add(new PurchaseDetails("1/2/2018", "MAXI", 20.5));

        purchases.add(new PurchaseDetails("1/1/2018", "ICA", 30.5));
        purchases.add(new PurchaseDetails("1/2/2018", "MAXI", 20.5));
        purchases.add(new PurchaseDetails("1/1/2018", "ICA", 30.5));
        purchases.add(new PurchaseDetails("1/2/2018", "MAXI", 20.5));
        purchases.add(new PurchaseDetails("1/1/2018", "ICA", 30.5));
        purchases.add(new PurchaseDetails("1/2/2018", "MAXI", 20.5));


    }

    }