package com.finchild.hoppateam.sda4.finchild;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class AccountChildPurchases extends AppCompatActivity {

    private ImageView backBtn;
    private TextView tvChildPurchase, tvBalancePurchase;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;



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

        //Give the recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    // Method of Initiating Data in the list, to be called for the RecyclerView
    public void initialiseData() {
        ArrayList< Purchase>  purchases = new ArrayList<>();
        ArrayList<Item> items_list = new ArrayList<>();

        items_list.add(new Item(" Apple", 3 , 23.5));
        items_list.add(new Item(" Kitkat", 1, 12));
        items_list.add(new Item(" Orange Juice", 2, 23));
        items_list.add(new Item(" Coco drink", 1, 10.3));

        Purchase purchase_list = new Purchase("ICA","1/3" , 234,  items_list);
        purchases.add(purchase_list);

        ArrayList< Item>  HamKopItems = new ArrayList<>();
        HamKopItems.add(new Item("Orange",4, 12));
        HamKopItems.add(new Item("milk", 1, 15));
        HamKopItems.add(new Item("Dairy Milk", 2, 13.5));
        HamKopItems.add(new Item("Red Boll", 1, 30));

        Purchase HamKop = new Purchase( "HamKop","4/3", 23,  HamKopItems);
        purchases.add(HamKop);

        ItemAdapter adapter = new ItemAdapter(purchases);
        mRecyclerView.setAdapter(adapter);


    }

    }