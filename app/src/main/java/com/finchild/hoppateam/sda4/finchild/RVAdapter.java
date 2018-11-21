package com.finchild.hoppateam.sda4.finchild;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PurchaseViewHolder> {

    Context context;
    private final LayoutInflater mInflater;
    List<Purchase_Details> Purchases;


    public static class PurchaseViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        EditText textView_purchase_date;
        TextView textView_store;
        EditText textView_amount;
        RVAdapter mAdapter;

        public PurchaseViewHolder(@NonNull View itemView, RVAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            cardView = (CardView) itemView.findViewById(R.id.purchase_cardview);
            textView_purchase_date = (EditText) itemView.findViewById(R.id.purchase_date);
            textView_store = (TextView) itemView.findViewById(R.id.store);
            textView_amount = (EditText) itemView.findViewById(R.id.amount);
        }
    }


    public RVAdapter(Context context, List<Purchase_Details> Purchases) {
        mInflater = LayoutInflater.from(context);
        this.Purchases = Purchases;
    }


    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.purchase_view, viewGroup, false);
        return new PurchaseViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder purchaseViewHolder, int i) {
        Purchase_Details purchase = Purchases.get(i);
        purchaseViewHolder.textView_purchase_date.setText(purchase.getPurchase_date());
        purchaseViewHolder.textView_store.setText(purchase.getStore());
        purchaseViewHolder.textView_amount.setText(String.valueOf(purchase.getAmount()));
    }

    @Override
    public int getItemCount() {
        return Purchases.size();
    }

}
