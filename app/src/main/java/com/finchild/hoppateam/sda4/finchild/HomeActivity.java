package com.finchild.hoppateam.sda4.finchild;



import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.finchild.hoppateam.sda4.finchild.adapter.ChildAdapter;
import com.finchild.hoppateam.sda4.finchild.login.LoginActivity;
import com.finchild.hoppateam.sda4.finchild.modules.ChildAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private ImageView backBtn;
    private ImageView btnSettings;
    private Button btnAddChild;
    private Fragment topImageFrag;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<ChildAccount> childAccList = new ArrayList<>();
    private String userId;
    private MyGestureListener mgListener;
    private GestureDetector mDetector;
    private final static String TAG = "MyGesture";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        SystemClock.sleep(9000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(getApplicationContext(), mgListener);
        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
         FirebaseUser user = firebaseAuth.getCurrentUser(); 
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activitybtnSettings = (ImageView) findViewById(R.id.ivSettings);
            finish();
            //starting login activitybackBtn.setOnClickListener(this);
            startActivity(new Intent(this, LoginActivity.class));
        } else{
            userId = user.getUid();
        }

        topImageFrag=this.getSupportFragmentManager().findFragmentById(R.id.topHomeFrag);



        btnSettings = (ImageView) findViewById(R.id.ivSettings);  
        btnAddChild = (Button) findViewById(R.id.btnAddChild);
        backBtn = (ImageView) findViewById(R.id.ivBack);
        backBtn.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnAddChild.setOnClickListener(this);
        initialiseData();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }


    public void onClick(View view) {
        //if btnSettings is pressed

        if (view == btnAddChild) {
            Intent settingsIntent = new Intent(this, AddChildAcount.class);
            // Start the activity.
            startActivity(settingsIntent);

        }

        if (view == btnSettings) {
            startActivity(new Intent(this, Settings.class));
        }

        if (view == backBtn) {
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    // Method of Initiating Data in the list, to be called for the RecyclerView
    public void initialiseData() {
        DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference().child("account").child(userId);
        accountRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ParentAccountNo = "";
                for (DataSnapshot accshot : dataSnapshot.getChildren()) {
                    ParentAccountNo = accshot.getKey();
                    System.out.println(ParentAccountNo);
                }
                if (!ParentAccountNo.equals("") && ParentAccountNo != null) {
                    DatabaseReference childAccRef = FirebaseDatabase.getInstance().getReference().child("child").child(ParentAccountNo);
                    childAccRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            childAccList.clear();
                            for (DataSnapshot childAccShot : dataSnapshot.getChildren()) {
                                ChildAccount childAccount = childAccShot.getValue(ChildAccount.class);
                                childAccList.add(childAccount);

                            }
                            mRecyclerView = (RecyclerView) findViewById(R.id.childrenList);
                            // create an adapter and supply the data to be displayed
                            adapter = new ChildAdapter(HomeActivity.this, childAccList);
                            //Connect the adapter with RecyclerView
                            mRecyclerView.setAdapter(adapter);
                            //Give the recycler view a default layout manager
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private class MyGestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Log.d(TAG, "onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.d(TAG, "onScroll");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.show(topImageFrag);
            fragmentTransaction.commit();
            Log.d(TAG, "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(topImageFrag);
            fragmentTransaction.commit();
            Log.d(TAG, "onFling");
            return false;
        }
    }


}
