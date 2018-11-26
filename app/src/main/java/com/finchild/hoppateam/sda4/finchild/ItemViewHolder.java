package com.finchild.hoppateam.sda4.finchild;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


public class ItemViewHolder extends ChildViewHolder {

    TextView itemTV;
     TextView qtyTV;
     TextView priceTV;
     LinearLayout item_linearLayout;
     ItemAdapter adapter;


    public ItemViewHolder(View itemView, ItemAdapter itemAdapter ) {
        super(itemView);
        itemTV = itemView.findViewById(R.id.item_view);
        qtyTV = itemView.findViewById(R.id.QTY_view);
        priceTV = itemView.findViewById(R.id.price_view);
        item_linearLayout = (LinearLayout)itemView.findViewById(R.id.item_expenses);
        this.adapter = itemAdapter;

    }

    public void bind(Item item){

        itemTV.setText(item.item_name);
        qtyTV.setText(String.valueOf(item.item_qty));
        priceTV.setText(String.valueOf(item.item_price));

    }


}
