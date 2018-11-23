package com.finchild.hoppateam.sda4.finchild;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        recyclerView.setAdapter(adapter);


    }
}
