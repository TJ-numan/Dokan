package com.tjnuman.dokan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tjnuman.dokan.Adapter.CartListRecyclerViewAdapter;
import com.tjnuman.dokan.Adapter.VerticalRecyclerViewAdapter;
import com.tjnuman.dokan.Model.CartListModel;
import com.tjnuman.dokan.Prevalent.Prevalent;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartListRecyclerViewAdapter cartListAdapter;
    ArrayList<CartListModel> arrayList;
    TextView totalPrice;
    Button nextButton;
    String singleItemPrice;
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

                        singleItemPrice = price;
                        CartListModel model = new CartListModel(name,date,image,price,quantity);
                        arrayList.add(model);
                        cartListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}