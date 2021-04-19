package com.tjnuman.dokan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tjnuman.dokan.Model.HorizontalModel;
import com.tjnuman.dokan.R;

import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalViewHolder> {
    Context context;
    ArrayList<HorizontalModel> horizontalModelArrayList;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<HorizontalModel> arrayList) {
        this.context = context;
        this.horizontalModelArrayList = arrayList;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,parent,false);


        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {

        HorizontalModel horizontalModel = horizontalModelArrayList.get(position);
        holder.productName.setText(horizontalModel.getProductName());
        holder.productPrice.setText(horizontalModel.getProductPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goto product detail activity

            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalModelArrayList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productPrice;
        ImageView productImage;

        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productname);
            productPrice = itemView.findViewById(R.id.productprice);
            productImage = itemView.findViewById(R.id.productimage);
        }
    }
}
