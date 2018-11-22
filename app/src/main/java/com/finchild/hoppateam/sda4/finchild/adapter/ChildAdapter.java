package com.finchild.hoppateam.sda4.finchild.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.R;
import com.finchild.hoppateam.sda4.finchild.modules.ChildDetails;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    Context context;
    private final LayoutInflater mInflater;
    List<ChildDetails> children;
    private int currentPosition = 0;


    public static class ChildViewHolder extends RecyclerView.ViewHolder {

        ImageView ivChild;
        TextView tvName, tvBalance;
        CardView cvChild;

        ChildAdapter mAdapter;

        public ChildViewHolder(@NonNull View itemView, ChildAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
            cvChild = (CardView) itemView.findViewById(R.id.cvChild);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvBalance = (TextView) itemView.findViewById(R.id.tvBalance);
            ivChild = (ImageView) itemView.findViewById(R.id.ivChild);
        }
    }


    public ChildAdapter(Context context, List<ChildDetails> list) {
        mInflater = LayoutInflater.from(context);
        this.children = list;
    }


    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.child_home_row_layout, viewGroup, false);
        return new ChildViewHolder (view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {
        ChildDetails child = children.get(position);
        childViewHolder.tvName.setText(child.getName());
        childViewHolder.tvBalance.setText(child.getBalance());

        if(child.getIdPicture() == 0){
            childViewHolder.ivChild.setImageResource(R.drawable.kid1);
        } else {
            childViewHolder.ivChild.setImageResource(R.drawable.kid2);
        }


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
        return children.size();
    }

}
