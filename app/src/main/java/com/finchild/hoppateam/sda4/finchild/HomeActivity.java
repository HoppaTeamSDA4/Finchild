package com.finchild.hoppateam.sda4.finchild;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    private final String CHANNEL_ID="personal Notification";
    private FirebaseAuth firebaseAuth;
    private ImageView backBtn;
    private ImageView btnSettings;
    private Button btnAddChild;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<ChildAccount> childAccList = new ArrayList<>();
    private String userId;
    private Button btnNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        SystemClock.sleep(1000);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        btnNotification=(Button) findViewById(R.id.button_Not);
        btnSettings = (ImageView) findViewById(R.id.ivSettings);  
        btnAddChild = (Button) findViewById(R.id.btnAddChild);
        backBtn = (ImageView) findViewById(R.id.ivBack);
        backBtn.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnAddChild.setOnClickListener(this);
        btnNotification.setOnClickListener(this);
        initialiseData();

    }

    @Override
    protected void onStart() {
        super.onStart();
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
                                if(childAccount.getBalance()>1000){
                                    System.out.println("testing notification");
                                    Toast.makeText(getApplicationContext(),"testing notification",Toast.LENGTH_SHORT).show();

                                    createNotificationChannel(childAccount.getName());
                                }

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

    public void sendNotification() {

        Toast.makeText(getApplicationContext(),"testing notification",Toast.LENGTH_SHORT).show();





        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//


           //     mNotificationManager.notify(001, mBuilder.build());
    }

    public void createNotificationChannel(String childName) {
        Toast.makeText(getApplicationContext(),"Inside create Channel",Toast.LENGTH_SHORT).show();
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "char for notification";
            String description = "string for notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
          //  sendNotification();



            Intent resultIntent= new Intent(getApplicationContext(),MainActivity.class);

            PendingIntent resultPendingIntent=PendingIntent.getActivity(getApplicationContext(),2,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);

            builder.setSmallIcon(R.drawable.childaccount)
                    .setContentTitle("Child Purchase")
                    .setContentText(childName +" has exceeded the limit of 1000 for this week")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(001,builder.build());

        }
    }


}
