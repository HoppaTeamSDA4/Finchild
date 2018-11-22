package com.finchild.hoppateam.sda4.finchild.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.R;
import com.finchild.hoppateam.sda4.finchild.Child;

import java.util.ArrayList;

public class ChildHomeAdapter extends RecyclerView.Adapter<ChildHomeAdapter.ViewHolder> {

    private ArrayList<Child> children;

    ItemClicked activity;

    public interface ItemClicked{

        void onItemClicked(int index);
    }

    public ChildHomeAdapter (Context context, ArrayList<Child> list){

        children = list;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivChild;
        TextView tvName, tvBalance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivChild = itemView.findViewById(R.id.ivChild);
            tvName = itemView.findViewById(R.id.tvName);
            tvBalance = itemView.findViewById(R.id.tvBalance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    activity.onItemClicked(children.indexOf((Child) view.getTag()));
                }
            });
        }
    }



    @NonNull
    @Override
    public ChildHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildHomeAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(children.get(i));

        viewHolder.tvName.setText(children.get(i).getName());
        viewHolder.tvBalance.setText(children.get(i).getBalance());

        // to set one pic or another
        if(children.get(i).isPicture() == true){
            viewHolder.ivChild.setImageResource(R.drawable.kid1);
        } else {
            viewHolder.ivChild.setImageResource(R.drawable.kid2);
        }
    }

    @Override
    public int getItemCount() {
        return children.size();
    }
}
