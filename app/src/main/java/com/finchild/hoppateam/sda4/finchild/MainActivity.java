package com.finchild.hoppateam.sda4.finchild;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wordlistSize =mWordList.size();
                //add the new word to the list
                mWordList.addLast("+ Purchase " + wordlistSize);
                // Notify the adapter, that the data has changed
                mRecyclerView.getAdapter().notifyItemInserted(wordlistSize);
                //Scroll to the button
                mRecyclerView.smoothScrollToPosition(wordlistSize);
            }
        });


        // put data into the wordlist
        for (int i = 0; i < 20; i++) {
            mWordList.addLast(" Purchase " + i);
        }
        //Create recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // create an adapter and supply the data to be displayed
        mAdapter = new MyAdapter(this, mWordList);
        //Connect the adapter with REcycler View
        mRecyclerView.setAdapter(mAdapter);
        //Give the recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}




