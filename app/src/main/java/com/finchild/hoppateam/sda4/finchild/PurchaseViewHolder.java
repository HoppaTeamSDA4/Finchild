package com.finchild.hoppateam.sda4.finchild;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class PurchaseViewHolder extends GroupViewHolder {
    TextView purchase_dateTV;
    TextView storeTV;
    TextView amountTV;
    LinearLayout linearLayout;

    public PurchaseViewHolder(View itemView) {
        super(itemView);
        purchase_dateTV = itemView.findViewById(R.id.purchase_date);
        storeTV = itemView.findViewById(R.id.store);
        amountTV = itemView.findViewById(R.id.amount);
        linearLayout = itemView.findViewById(R.id.purchase_details);


    }

    public void bind(Purchase purchase){
        purchase_dateTV.setText(purchase.getPurchase_date());
        storeTV.setText(purchase.getStore());
        amountTV.setText(String.valueOf(purchase.getAmount()));
    }
}


