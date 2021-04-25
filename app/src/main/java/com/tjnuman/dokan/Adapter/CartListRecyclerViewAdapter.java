package com.tjnuman.dokan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tjnuman.dokan.Model.CartListModel;
import com.tjnuman.dokan.R;

import java.util.ArrayList;

public class CartListRecyclerViewAdapter extends RecyclerView.Adapter<CartListRecyclerViewAdapter.myViewholder> {

    Context context;
    ArrayList<CartListModel> list;

    public CartListRecyclerViewAdapter(Context context, ArrayList<CartListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_single_item,parent,false);

        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {
        CartListModel model = list.get(position);
        holder.name.setText(model.getPname());
        holder.price.setText(model.getPrice());
        holder.date.setText(model.getDate());
        holder.quantity.setText(model.getQunatity());
        Glide.with(holder.img.getContext()).load(model.getPimage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewholder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name,price,date,quantity;
        public myViewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.cartlistproductimage);
            name = itemView.findViewById(R.id.cartlistproductname);
            price = itemView.findViewById(R.id.cartlistproductprice);
            date = itemView.findViewById(R.id.cartlistproductdate);
            quantity = itemView.findViewById(R.id.cartlistquantity);
        }
    }
}
