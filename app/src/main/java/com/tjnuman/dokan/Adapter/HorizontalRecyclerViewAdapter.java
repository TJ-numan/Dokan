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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.tjnuman.dokan.Model.HorizontalModel;
import com.tjnuman.dokan.R;

import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.myViewholder>{


//    public HorizontalRecyclerViewAdapter(Context context, @NonNull FirebaseRecyclerOptions<HorizontalModel> options) {
//        super(options);
//    }
    Context context;
    ArrayList<HorizontalModel> list;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<HorizontalModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,parent,false);
        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {
        HorizontalModel model = list.get(position);
        holder.name.setText(model.getPname());
        holder.price.setText(model.getPrice());
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,price;
        public myViewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.itemimage);
            name = itemView.findViewById(R.id.itemname);
            price = itemView.findViewById(R.id.itemprice);

        }
    }
}
