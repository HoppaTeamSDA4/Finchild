package com.finchild.hoppateam.sda4.finchild.adapter;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.R;
import com.finchild.hoppateam.sda4.finchild.modules.Item;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


public class ItemViewHolder extends ChildViewHolder {

    TextView itemTV;
    TextView qtyTV;
    TextView priceTV;
    CardView itemCV;
    ItemAdapter itemAdapter;



    public ItemViewHolder(View itemView, ItemAdapter itemAdapter) {
        super(itemView);
        itemTV = (TextView) itemView.findViewById(R.id.item_view);
        qtyTV = (TextView) itemView.findViewById(R.id.QTY_view);
        priceTV = (TextView) itemView.findViewById(R.id.price_view);
        itemCV = (CardView) itemView.findViewById(R.id.itemCv);
        this.itemAdapter=itemAdapter;

    }

    public void bind(Item item) {

        itemTV.setText(item.getName());
        qtyTV.setText(String.valueOf(item.getQuantity()));
        priceTV.setText(String.valueOf(item.getPrice()) + " Kr ");

    }


}
