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
        EditText purchase_date;
        TextView store;
        EditText amount;
        RVAdapter mAdapter;

        public PurchaseViewHolder(@NonNull View itemView, RVAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            cardView = (CardView) itemView.findViewById(R.id.purchase_cardview);
            purchase_date = (EditText) itemView.findViewById(R.id.purchase_date);
            store = (TextView) itemView.findViewById(R.id.store);
            amount = (EditText) itemView.findViewById(R.id.amount);
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
        purchaseViewHolder.purchase_date.setText(Purchases.get(i).getPurchase_date());
        purchaseViewHolder.store.setText(Purchases.get(i).getStore());
        purchaseViewHolder.amount.setText(String.valueOf(Purchases.get(i).getAmount()));
    }

    @Override
    public int getItemCount() {
        return Purchases.size();
    }

}
