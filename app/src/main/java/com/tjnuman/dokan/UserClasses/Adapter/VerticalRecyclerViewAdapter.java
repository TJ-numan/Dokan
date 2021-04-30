package com.tjnuman.dokan.UserClasses.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tjnuman.dokan.UserClasses.AllProductFromCatagory;
import com.tjnuman.dokan.UserClasses.Model.VerticalModel;
import com.tjnuman.dokan.R;

import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VRVviewHolder> {
    Context context;
    ArrayList<VerticalModel> verticalModelArrayList;

    public VerticalRecyclerViewAdapter(Context context, ArrayList<VerticalModel> verticalModelArrayList) {
        this.context = context;
        this.verticalModelArrayList = verticalModelArrayList;
    }

    @NonNull
    @Override
    public VerticalRecyclerViewAdapter.VRVviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical,parent,false);
        return new VRVviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRecyclerViewAdapter.VRVviewHolder holder, int position) {

        VerticalModel verticalModel = verticalModelArrayList.get(position);
        String category = verticalModel.getCategory();
        //FirebaseRecyclerOptions<HorizontalModel> options = verticalModel.getOptions();
        holder.categoryTextView.setText(category);

        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(context, verticalModel.getMyList());

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);
        holder.categoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ekhane click korle ei catagory er sob gula product
                // sow korbe eikhaner recycler view er proti ta item e
                // duita kore product show korebe

                Intent intent = new Intent(context, AllProductFromCatagory.class);
                intent.putExtra("Catagory",category);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return verticalModelArrayList.size();
    }

    public static class VRVviewHolder extends RecyclerView.ViewHolder{
        TextView categoryTextView;
        RecyclerView recyclerView;
        public VRVviewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.category);
            recyclerView = itemView.findViewById(R.id.horizontalRecyclerview);


        }
    }
}
