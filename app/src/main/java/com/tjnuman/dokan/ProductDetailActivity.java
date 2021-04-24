package com.tjnuman.dokan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tjnuman.dokan.Model.HorizontalModel;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView productImage;
    TextView productPrice,productDescription,productName;
    ElegantNumberButton elegantNumberButton;
    String productID = "";
    Button addtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productID = getIntent().getStringExtra("pid");
        addtocart = findViewById(R.id.addtocart);
        productImage = findViewById(R.id.detailproductimage);
        productName = findViewById(R.id.prudctnameindetailpage);
        elegantNumberButton = findViewById(R.id.quantity);
        productPrice = findViewById(R.id.itemprice);
        productDescription = findViewById(R.id.prudctdetailindetailpage);


        productDetail();


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetailActivity.this, "added to the cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void productDetail() {

        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Products");
        productRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    HorizontalModel products = snapshot.getValue(HorizontalModel.class);

                    productName.setText(products.getPname());
                    productPrice.setText(products.getPrice());
                    productDescription.setText(products.getDescription());

                    Glide.with(productImage).load(products.getImage()).into(productImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}