package com.finchild.hoppateam.sda4.finchild.adapter;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.R;
import com.finchild.hoppateam.sda4.finchild.modules.Expense;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class PurchaseViewHolder extends GroupViewHolder {
    TextView purchase_dateTV;
    TextView storeTV;
    TextView amountTV;
    CardView purchaseCV;

    public PurchaseViewHolder(View itemView) {
        super(itemView);
        purchase_dateTV = itemView.findViewById(R.id.purchase_date);
        storeTV = itemView.findViewById(R.id.store);
        amountTV = itemView.findViewById(R.id.amount);
        purchaseCV = itemView.findViewById(R.id.purchase_cardview);


    }

    public void bind(Expense expense){
        purchase_dateTV.setText(expense.getDate());
        storeTV.setText(expense.getStore().toUpperCase());
        amountTV.setText(Double.toString(expense.getTotalAmount()) + " Kr ");
    }
}


