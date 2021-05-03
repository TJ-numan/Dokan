package com.tjnuman.dokan.AdminClasses.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tjnuman.dokan.AdminClasses.Models.AllOrderModel;
import com.tjnuman.dokan.R;
import com.tjnuman.dokan.UserClasses.CartActivity;
import com.tjnuman.dokan.UserClasses.HomeActivity;

import java.util.ArrayList;

public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderAdapter.myViewHolder> {
    Context context;
    ArrayList<AllOrderModel> list;

    public AllOrderAdapter(Context context, ArrayList<AllOrderModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        AllOrderModel model = list.get(position);
        holder.name.setText(model.getName());
        holder.address.setText(model.getAddress());
        holder.phone.setText(model.getPhone());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartActivity.class);
                intent.putExtra("AdminorUser","Admin");
                intent.putExtra("UserPhone",model.getPhone());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView name,address,phone;
        Button button;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ordername);
            address = itemView.findViewById(R.id.orderaddress);
            phone = itemView.findViewById(R.id.orderphon);
            button = itemView.findViewById(R.id.orderbutton);
        }
    }
}
