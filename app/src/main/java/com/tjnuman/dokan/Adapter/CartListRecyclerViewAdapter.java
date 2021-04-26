package com.tjnuman.dokan.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tjnuman.dokan.CartActivity;
import com.tjnuman.dokan.Model.CartListModel;
import com.tjnuman.dokan.Model.HorizontalModel;
import com.tjnuman.dokan.Prevalent.Prevalent;
import com.tjnuman.dokan.ProductDetailActivity;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ClickListener:", "Item clicked");
                CharSequence options[] = new CharSequence[]{
                        "Edit",
                        "Remove"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cart Option:");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        if(i == 0){
                            Intent intent = new Intent(context, ProductDetailActivity.class);
                            intent.putExtra("pid",model.getPid());
                            context.startActivity(intent);
                        }
                        if(i == 1){

                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

                            rootRef.child("Cart List")
                                    .child("User View")
                                    .child(Prevalent.currentOnlineUser.getPhone())
                                    .child("Products")
                                    .child(model.getPid())
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                list.remove(position);
                                                notifyItemRemoved(position);
                                                Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });
                builder.show();
            }
        });

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
