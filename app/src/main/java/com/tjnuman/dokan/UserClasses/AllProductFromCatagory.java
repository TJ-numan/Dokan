package com.tjnuman.dokan.UserClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tjnuman.dokan.R;
import com.tjnuman.dokan.UserClasses.Adapter.CartListRecyclerViewAdapter;
import com.tjnuman.dokan.UserClasses.Adapter.ProductCategoryAdapter;
import com.tjnuman.dokan.UserClasses.Adapter.VerticalRecyclerViewAdapter;
import com.tjnuman.dokan.UserClasses.Model.HorizontalModel;
import com.tjnuman.dokan.UserClasses.Model.ProductCategoryModel;
import com.tjnuman.dokan.UserClasses.Model.VerticalModel;

import java.util.ArrayList;

public class AllProductFromCatagory extends AppCompatActivity {
    TextView catagory;

    String catagorysaved;

    RecyclerView recyclerView;
     ProductCategoryAdapter productCategoryAdapter;
    ArrayList<ProductCategoryModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product_from_catagory);
        catagory = findViewById(R.id.allproductcatagory);
        catagorysaved = getIntent().getStringExtra("Catagory");
        recyclerView = findViewById(R.id.allproductrecyclerview);
        arrayList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false));
        productCategoryAdapter = new ProductCategoryAdapter(this,arrayList);
        recyclerView.setAdapter(productCategoryAdapter);
        getDataFromServer();
        catagory.setText(catagorysaved);

    }

    private void getDataFromServer() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("Products").orderByChild("category").equalTo(catagorysaved);//is working
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot snp:snapshot.getChildren()){
                        String name = snp.child("pname").getValue().toString();
                        String price = snp.child("price").getValue().toString();
                        String image = snp.child("image").getValue().toString();
                        String description = snp.child("description").getValue().toString();
                        String pid = snp.child("pid").getValue().toString();

                        ProductCategoryModel model = new ProductCategoryModel(name,price,description,image,pid);
                        arrayList.add(model);
                        productCategoryAdapter.notifyDataSetChanged();
                    }
                }
                else {
                    Toast.makeText(AllProductFromCatagory.this, "There is no Product under this category", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}