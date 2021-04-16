package com.tjnuman.dokan.AdminClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tjnuman.dokan.R;

public class AdminAddNewProductActivity extends AppCompatActivity {
    String CatagoryName;
    ImageView addNewProductimage;
    Button addButton;
    EditText productName,productPrice,getProductDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product2);
        CatagoryName = getIntent().getExtras().get("Catagory").toString();
        Toast.makeText(this, CatagoryName, Toast.LENGTH_SHORT).show();
        addButton = findViewById(R.id.addbtn);
        addNewProductimage = findViewById(R.id.addimage);
        productName = findViewById(R.id.productname);
        getProductDescription = findViewById(R.id.productdescription);
        productPrice = findViewById(R.id.productprice);
    }
}