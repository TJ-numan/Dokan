package com.tjnuman.dokan.AdminClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tjnuman.dokan.AdminClasses.Adapters.AllOrderAdapter;
import com.tjnuman.dokan.AdminClasses.Models.AllOrderModel;
import com.tjnuman.dokan.R;

import java.util.ArrayList;

public class AllOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AllOrderAdapter allOrderAdapter;
    ArrayList<AllOrderModel>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        recyclerView = findViewById(R.id.customerorderrecyclerView);
        arrayList =  new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        allOrderAdapter = new AllOrderAdapter(this,arrayList);
        recyclerView.setAdapter(allOrderAdapter);
        getDatafromserver();

    }

    private void getDatafromserver() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("Users");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    for(DataSnapshot snp:snapshot.getChildren()) {

                        String name = snp.child("name").getValue().toString();
                        String address = snp.child("address").getValue().toString();
                        String phone = snp.child("phone").getValue().toString();
                        AllOrderModel model = new AllOrderModel(address,name,phone);
                        arrayList.add(model);
                        allOrderAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}