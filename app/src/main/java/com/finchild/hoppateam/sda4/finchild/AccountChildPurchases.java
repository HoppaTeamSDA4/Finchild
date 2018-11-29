package com.finchild.hoppateam.sda4.finchild;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.finchild.hoppateam.sda4.finchild.adapter.ItemAdapter;
import com.finchild.hoppateam.sda4.finchild.modules.Expense;
import com.finchild.hoppateam.sda4.finchild.modules.Item;
import com.finchild.hoppateam.sda4.finchild.session.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AccountChildPurchases extends AppCompatActivity {

    private ImageView backBtn;
    private TextView tvChildPurchase, tvBalancePurchase;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<Expense> expenseList = new ArrayList<>();
    private List<Item> itemsList=new ArrayList<>();
    private final String CHANNEL_ID="personal Notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_child_purchases);
        Session session = new Session(AccountChildPurchases.this);
        System.out.println(session.getParentAcc());

        tvChildPurchase = (TextView) findViewById(R.id.tvChildPurchase);
        tvBalancePurchase = (TextView) findViewById(R.id.tvBalancePurchase);
        backBtn = (ImageView) findViewById(R.id.ivBack);
        // to set the back button instead of the logout
        backBtn.setImageResource(R.drawable.back_button);


        //pass here name from the home activity the name and the balance of the child
        tvChildPurchase.setText(getIntent().getStringExtra("childName"));
        tvBalancePurchase.setText(Double.toString(getIntent().getDoubleExtra("childAccBalance", 50.0)));


        //Create recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // create an adapter and supply the data to be displayed

        //Give the recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //purchases = new ArrayList<Purchase_Details>();
        initialiseData();

    }

    // Method of Initiating Data in the list, to be called for the RecyclerView
    public void initialiseData() {
        ArrayList<Expense> expenses = new ArrayList<>();
        ArrayList<Item> hemkopsItemps = new ArrayList<>();
        Session session = new Session(AccountChildPurchases.this);
        String parentAcc = session.getParentAcc();
        String childAcc = getIntent().getStringExtra("childAccNo");
        //Getting the spending limits of child from childAdapter
       String dailylimit=session.getChildDailyLimit();
       final double dailylimitAmount=Double.parseDouble(dailylimit);
        final String childName =session.getChildName();
      //  final double dailylimitAmount = Double.parseDouble(getIntent().getStringExtra("childAccDailyLimit"));
       System.out.println(dailylimitAmount);
       // boolean dailyLimitStatus=getIntent().getExtras().getBoolean("childAccDailyLimitStat");
      //  final String childName=getIntent().getStringExtra("childName");
        DatabaseReference childAccRef = FirebaseDatabase.getInstance().getReference().child("expenses").child(childAcc);
        childAccRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemsList.clear();
                expenseList.clear();
                for (DataSnapshot expenseSnapshot : dataSnapshot.getChildren()) {
                    String expenseId = expenseSnapshot.getKey();
                    String expenseStore=expenseSnapshot.child("store").getValue().toString();
                    String expenseDate=expenseSnapshot.child("date").getValue().toString();
                    Double expenseTotalAmount=Double.parseDouble(expenseSnapshot.child("totalAmount").getValue().toString());
                    for(DataSnapshot itemsSnapShot: expenseSnapshot.child("items").getChildren()) {
                        String itemName= itemsSnapShot.child("name").getValue().toString();
                        String itemcategory = itemsSnapShot.child("category").getValue().toString();
                        Double itemQuantity = Double.parseDouble(itemsSnapShot.child("quantity").getValue().toString());
                        Double itemPrice = Double.parseDouble(itemsSnapShot.child("price").getValue().toString());
                        itemsList.add(new Item(itemcategory,itemName,itemQuantity,itemPrice));
                    }
                    if(expenseTotalAmount>dailylimitAmount){
                        Log.d("condition for expenses","####################################");
                        createNotificationChannel(childName);
                    }

                   expenseList.add(new Expense(expenseId,expenseStore,expenseDate,expenseTotalAmount,itemsList));
                   ItemAdapter adapter = new ItemAdapter(expenseList);
                   mRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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



            Intent resultIntent= new Intent(getApplicationContext(),AccountChildPurchases.class);

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