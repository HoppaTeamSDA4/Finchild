package com.finchild.hoppateam.sda4.finchild.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finchild.hoppateam.sda4.finchild.AccountChildPurchases;
import com.finchild.hoppateam.sda4.finchild.R;
import com.finchild.hoppateam.sda4.finchild.modules.ChildAccount;
import com.finchild.hoppateam.sda4.finchild.session.Session;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    Context context;
    private final LayoutInflater mInflater;
    private List<ChildAccount> childAccList;
    private int currentPosition = 0;


    public static class ChildViewHolder extends RecyclerView.ViewHolder {

        ImageView ivChild;
        TextView tvName, tvBalance,tvStatus;
        CardView cvChild;


        //ChildAdapter mAdapter;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.mAdapter = adapter;
            cvChild = (CardView) itemView.findViewById(R.id.cvChild);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvBalance = (TextView) itemView.findViewById(R.id.tvBalance);
            ivChild = (ImageView) itemView.findViewById(R.id.ivChild);
            tvStatus=(TextView) itemView.findViewById(R.id.tvStatus);
            tvStatus.setVisibility(View.INVISIBLE);

        }
    }


    public ChildAdapter(Context context, List<ChildAccount> list) {
        mInflater = LayoutInflater.from(context);
        this.childAccList = list;
        this.context=context;
    }


    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.child_home_row_layout, viewGroup, false);
        return new ChildViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder,  final int position) {
        final ChildAccount childAccount = childAccList.get(position);
        childViewHolder.tvName.setText(childAccount.getName());
        childViewHolder.tvBalance.setText(Double.toString(childAccount.getBalance()) + " Kr ");
        if(!childAccount.isStatus()) {
            childViewHolder.tvStatus.setVisibility(View.VISIBLE);
        }

       if(position%2 == 0){
            childViewHolder.ivChild.setImageResource(R.drawable.kid1);
        } else {
            childViewHolder.ivChild.setImageResource(R.drawable.kid2);
        }

        childViewHolder.cvChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AccountChildPurchases.class);
                intent.putExtra("childName",childAccList.get(position).getName());
                intent.putExtra("childAccBalance",childAccList.get(position).getBalance());
                intent.putExtra("childAccNo",childAccList.get(position).getAccountNo());
                Session session = new Session(context);

                session.setChildAccStatus(childAccList.get(position).isStatus());

                session.setChildDailyLimitAmount(Double.toString(childAccList.get(position).getDailyLimitAmount()));
                session.setChildDailyLimitStat(Boolean.toString(childAccList.get(position).isDailyLimit()));

                session.setChildWeeklyLimitAmount(Double.toString(childAccList.get(position).getWeeklyLimitAmount()));
                session.setChildWeeklyStat(Boolean.toString(childAccList.get(position).isWeeklyLimit()));

                session.setChildMonthlyLimitAmount(Double.toString(childAccList.get(position).getMonthlyLimitAmount()));
                session.setChildMonthlyyLimitStat(Boolean.toString(childAccList.get(position).isMonthlyLimit()));

                session.setChildName(childAccList.get(position).getName());
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return childAccList.size();
    }

}
