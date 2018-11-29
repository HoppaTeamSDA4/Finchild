package com.finchild.hoppateam.sda4.finchild.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finchild.hoppateam.sda4.finchild.R;
import com.finchild.hoppateam.sda4.finchild.modules.Expense;
import com.finchild.hoppateam.sda4.finchild.modules.Item;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ItemAdapter extends ExpandableRecyclerViewAdapter<PurchaseViewHolder, ItemViewHolder> {

    List<Item> purchased_items;



    public ItemAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public PurchaseViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_row_layout, parent, false);
        return new PurchaseViewHolder(v);
    }

    @Override
    public ItemViewHolder onCreateChildViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(v, this);
    }

    @Override
    public void onBindChildViewHolder(ItemViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        final Item item = (Item) group.getItems().get(childIndex);
        holder.bind(item);

    }

    @Override
    public void onBindGroupViewHolder(PurchaseViewHolder holder, int flatPosition, ExpandableGroup group) {
        final Expense exp = (Expense) group;
        holder.bind(exp);
    }
}
