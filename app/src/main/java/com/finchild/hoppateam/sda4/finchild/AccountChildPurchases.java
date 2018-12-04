package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.adapter.ItemAdapter;
import com.finchild.hoppateam.sda4.finchild.modules.ChildAccount;
import com.finchild.hoppateam.sda4.finchild.modules.Expense;
import com.finchild.hoppateam.sda4.finchild.modules.Item;
import com.finchild.hoppateam.sda4.finchild.notification.Notification;
import com.finchild.hoppateam.sda4.finchild.session.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AccountChildPurchases extends ElementsBottomBarNav implements View.OnClickListener {

    private ImageView backBtn;
    private ImageView btnSettings;
    private TextView tvChildPurchase, tvBalancePurchase;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<Expense> expenseList = new ArrayList<>();
    private List<Item> itemsList = new ArrayList<>();
    private BottomNavigationView mMainNav;
    Session session;
    private ChildAccount childAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        session = new Session(getApplicationContext());
        if (session.getChildAccNo().equals("") || session.getChildAccNo() == null) {
            session.setChildAccNo(getIntent().getStringExtra("childAccNo"));
        }

        tvChildPurchase = (TextView) findViewById(R.id.tvChildPurchase);
        tvBalancePurchase = (TextView) findViewById(R.id.tvBalancePurchase);
        backBtn = (ImageView) findViewById(R.id.ivBack);

        // to set the back button instead of the logout
        backBtn.setImageResource(R.drawable.back_button);
        backBtn.setOnClickListener(this);
        btnSettings = (ImageView) findViewById(R.id.ivSettings);
        btnSettings.setImageResource(0);



        //pass here name from the home activity the name and the balance of the child
        tvChildPurchase.setText(session.getChildName());
        tvBalancePurchase.setText(session.getChildAccBalance());


        //Create recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // create an adapter and supply the data to be displayed

        //Give the recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //purchases = new ArrayList<Purchase_Details>();
        initialiseData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initialiseData();
    }

    @Override
    public void onClick(View v) {
        if (v == backBtn) {
            goHome();
        }
    }

    @Override
    int getContentViewId() {
        return R.layout.account_child_purchases;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.nav_account;
    }

    // Method of Initiating Data in the list, to be called for the RecyclerView
    public void initialiseData() {
        childAccount = new ChildAccount();
        String parentAcc = session.getParentAcc();
        final String childAcc = session.getChildAccNo();
        DatabaseReference childAccRef = FirebaseDatabase.getInstance().getReference().child("child").child(parentAcc);
        childAccRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if (childSnapshot.child("accountNo").getValue().toString().equals(childAcc)) {
                        childAccount = childSnapshot.getValue(ChildAccount.class);
                        String childName = childAccount.getName();
                        double balance = childAccount.getBalance();
                        tvChildPurchase.setText(childName);
                        tvBalancePurchase.setText(Double.toString(balance));
                        if (session.getChildAccBalance().equals("") || session.getChildAccBalance() == null) {
                            session.setChildAccBalance(Double.toString(balance));
                        }

                        if (session.getChildName().equals("") || session.getChildName() == null) {
                            session.setChildName(childName);
                        }
                    }
                }
                String dailylimit = session.getChildDailyLimitAmount();
                System.out.println(dailylimit);
                final double dailylimitAmount = Double.parseDouble(dailylimit);
                System.out.println(dailylimitAmount);
                DatabaseReference childExpenseRef = FirebaseDatabase.getInstance().getReference().child("expenses").child(childAcc);
                childExpenseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        itemsList.clear();
                        expenseList.clear();
                        for (DataSnapshot expenseSnapshot : dataSnapshot.getChildren()) {
                            itemsList = new ArrayList<>();

                            String expenseId = expenseSnapshot.getKey();
                            String expenseStore = expenseSnapshot.child("store").getValue().toString();
                            String expenseDate = expenseSnapshot.child("date").getValue().toString();
                            Double expenseTotalAmount = Double.parseDouble(expenseSnapshot.child("totalAmount").getValue().toString());
                            for (DataSnapshot itemsSnapShot : expenseSnapshot.child("items").getChildren()) {
                                String itemName = itemsSnapShot.child("name").getValue().toString();
                                String itemcategory = itemsSnapShot.child("category").getValue().toString();
                                Double itemQuantity = Double.parseDouble(itemsSnapShot.child("quantity").getValue().toString());
                                Double itemPrice = Double.parseDouble(itemsSnapShot.child("price").getValue().toString());
                                itemsList.add(new Item(itemcategory, itemName, itemQuantity, itemPrice));
                            }

                            // checking if limit of total purchase for today has exceeded
                            Date currDate = Calendar.getInstance().getTime();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            String formattedDate = df.format(currDate);
                            if (formattedDate.equals(expenseDate)) {
                                Double expenseForParticularDay = 0.0;
                                expenseForParticularDay += expenseTotalAmount;
                                if (expenseForParticularDay > dailylimitAmount) {
                                    String notify = session.getNotificationSent();
                                    if (!notify.equals("sent")) {
                                        session.setNotificationSent("sent");
                                        Log.d("condition for expenses", "####################################");
                                        Notification.createNotificationChannel(getApplicationContext(), childAccount.getName(), dailylimitAmount, "limitNotif");
                                    }
                                }
                            }
                            expenseList.add(new Expense(expenseId, expenseStore, expenseDate, expenseTotalAmount, itemsList));
                            ItemAdapter adapter = new ItemAdapter(expenseList);
                            mRecyclerView.setAdapter(adapter);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void goHome() {
        // Create an Intent to start the Home activity
        Intent intent = new Intent(this, HomeActivity.class);
        // Start the activity.
        startActivity(intent);
    }


}