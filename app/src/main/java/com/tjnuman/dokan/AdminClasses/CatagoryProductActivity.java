package com.tjnuman.dokan.AdminClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tjnuman.dokan.R;
import com.tjnuman.dokan.UserClasses.AllProductFromCatagory;

public class CatagoryProductActivity extends AppCompatActivity {
    String parendb;
    LinearLayout clothing,household,gadgets,jewellery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        household = findViewById(R.id.householdadmin);
        gadgets = findViewById(R.id.gadgetsadmin);
        jewellery = findViewById(R.id.jewelleryadmin);
        clothing = findViewById(R.id.clothingadmin);

        parendb = getIntent().getStringExtra("ParentDB");

        clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatagoryProductActivity.this, AllProductFromCatagory.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        household.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatagoryProductActivity.this,AllProductFromCatagory.class);
                intent.putExtra("Catagory","Household");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });
        gadgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatagoryProductActivity.this,AllProductFromCatagory.class);
                intent.putExtra("Catagory","Gadgets");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        jewellery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatagoryProductActivity.this,AllProductFromCatagory.class);
                intent.putExtra("Catagory","jewellery");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });


    }
}