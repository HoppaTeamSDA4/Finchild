package com.finchild.hoppateam.sda4.finchild.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;

import com.finchild.hoppateam.sda4.finchild.PurchaseDetails;
import com.finchild.hoppateam.sda4.finchild.R;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    Context context;
    private final LayoutInflater mInflater;
    List<PurchaseDetails> Purchases;
    private int currentPosition = 0;


    public static class PurchaseViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        EditText textView_purchase_date;
        TextView textView_store;
        EditText textView_amount;
        PurchaseAdapter mAdapter;

        public PurchaseViewHolder(@NonNull View itemView, PurchaseAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            cardView = (CardView) itemView.findViewById(R.id.purchase_cardview);
            textView_purchase_date = (EditText) itemView.findViewById(R.id.purchase_date);
            textView_store = (TextView) itemView.findViewById(R.id.store);
            textView_amount = (EditText) itemView.findViewById(R.id.amount);
        }
    }


    public PurchaseAdapter(Context context, List<PurchaseDetails> Purchases) {
        mInflater = LayoutInflater.from(context);
        this.Purchases = Purchases;
    }


    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.purchase_row_layout, viewGroup, false);
        return new PurchaseViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder purchaseViewHolder, int position) {
        PurchaseDetails purchase = Purchases.get(position);
        purchaseViewHolder.textView_purchase_date.setText(purchase.getPurchase_date());
        purchaseViewHolder.textView_store.setText(purchase.getStore());
        purchaseViewHolder.textView_amount.setText(String.valueOf(purchase.getAmount()));

      /*  purchaseViewHolder.linearLayout.setVisility(View.GONE);

        // if the position is equale to the item position that want to expand
        if (currentPosition == position) {
            // creating visibility
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.item_animation);
            //toggling visbilty
            purchaseViewHolder.linearLayout.setVisibility(View.VISIBLE);
            //adding sliding effect
            purchaseViewHolder.linearLayout.startAnimation(slideDown);
        }*/

    }
    @Override
    public int getItemCount() {
        return Purchases.size();
    }

}

