package com.tjnuman.dokan.UserClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tjnuman.dokan.UserClasses.Model.HorizontalModel;
import com.tjnuman.dokan.UserClasses.Prevalent.Prevalent;
import com.tjnuman.dokan.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView productImage;
    TextView productPrice,productDescription,productName;
    ElegantNumberButton elegantNumberButton;
    String productID = "",savedProductImage;
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
                addtoCartList();
            }
        });

    }

    private void addtoCartList() {
        String saveCurrentTime, saveCurrentDate;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        saveCurrentDate = currentDateFormat.format(c.getTime());
        SimpleDateFormat currentTimeFormat = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTimeFormat.format(c.getTime());



        DatabaseReference cartListref = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("pid",productID);
        cartMap.put("pname",productName.getText().toString());
        cartMap.put("price",productPrice.getText().toString());
        cartMap.put("pimage",savedProductImage);
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);
        cartMap.put("quantity",elegantNumberButton.getNumber());
        cartMap.put("discount","");

        cartListref
                .child("User View")
                .child(Prevalent.currentOnlineUser.getPhone())
                .child("Products")
                .child(productID)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            cartListref
                                    .child("Admin View")
                                    .child(Prevalent.currentOnlineUser.getPhone())
                                    .child("Products")
                                    .child(productID)
                                    .updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                         if(task.isSuccessful()){
                                             Toast.makeText(ProductDetailActivity.this, "Added to the cart", Toast.LENGTH_SHORT).show();
                                             Intent intent = new Intent(ProductDetailActivity.this,HomeActivity.class);
                                             startActivity(intent);

                                         }
                                        }
                                    });

                        }
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
                    savedProductImage = products.getImage();


                    Glide.with(productImage).load(savedProductImage).into(productImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}