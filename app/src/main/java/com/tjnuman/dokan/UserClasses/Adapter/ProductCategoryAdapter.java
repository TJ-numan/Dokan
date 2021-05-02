package com.tjnuman.dokan.UserClasses.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tjnuman.dokan.AdminClasses.MyClickListener;
import com.tjnuman.dokan.R;
import com.tjnuman.dokan.UserClasses.Model.ProductCategoryModel;
import com.tjnuman.dokan.UserClasses.ProductDetailActivity;

import java.util.ArrayList;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.myViewHolder> {

    Context context;
    ArrayList<ProductCategoryModel> list;
    MyClickListener myClickListener;

    public ProductCategoryAdapter(Context context, ArrayList<ProductCategoryModel> list,MyClickListener myClickListener) {
        this.context = context;
        this.list = list;
        this.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allproductcatagory,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ProductCategoryModel  model = list.get(position);
        holder.name.setText(model.getPname());
        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myClickListener.onClickListener(position, model.getPid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.allproductitemimage);
            name = itemView.findViewById(R.id.allproductitemname);
            price = itemView.findViewById(R.id.allproductitemprice);

        }
    }
}
