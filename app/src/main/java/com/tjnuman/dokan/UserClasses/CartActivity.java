package com.tjnuman.dokan.UserClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tjnuman.dokan.UserClasses.Adapter.CartListRecyclerViewAdapter;
import com.tjnuman.dokan.UserClasses.Model.CartListModel;
import com.tjnuman.dokan.UserClasses.Prevalent.Prevalent;
import com.tjnuman.dokan.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartListRecyclerViewAdapter cartListAdapter;
    ArrayList<CartListModel> arrayList;
    TextView totalPrice;
    int totalprice = 0;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.recyclerViewcart);
        nextButton = findViewById(R.id.nextbutton);
        totalPrice = findViewById(R.id.totalPrice);
        arrayList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        cartListAdapter = new CartListRecyclerViewAdapter(this,arrayList);
        recyclerView.setAdapter(cartListAdapter);
        getDataFromServer();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });









    }

    private void getDataFromServer() {


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("Cart List").child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snp:snapshot.getChildren()){
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("pimage").getValue().toString();
                        String date = snp.child("date").getValue().toString();
                        String quantity = snp.child("quantity").getValue().toString();
                        String pid = snp.child("pid").getValue().toString();

                        totalprice =totalprice + (( Integer.valueOf(price))) * ( Integer.valueOf(quantity));
                        totalPrice.setText(String.valueOf(totalprice));
                        Log.d("TotalPrice",String.valueOf(totalprice));
                        CartListModel model = new CartListModel(name,date,image,price,quantity,pid);
                        arrayList.add(model);
                        cartListAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    Toast.makeText(CartActivity.this, "There is no cart list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}