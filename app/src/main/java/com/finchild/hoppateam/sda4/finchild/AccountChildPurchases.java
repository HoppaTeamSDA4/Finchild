package com.finchild.hoppateam.sda4.finchild;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.adapter.ItemAdapter;
import com.finchild.hoppateam.sda4.finchild.modules.ChildAccount;
import com.finchild.hoppateam.sda4.finchild.modules.Expense;
import com.finchild.hoppateam.sda4.finchild.modules.Item;
import com.finchild.hoppateam.sda4.finchild.session.Session;
import com.google.firebase.database.ChildEventListener;
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

}