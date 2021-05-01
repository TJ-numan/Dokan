package com.tjnuman.dokan.AdminClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tjnuman.dokan.R;

public class AdminCatagoryActivity extends AppCompatActivity {
    ImageView clotthings,household,gadget,jewellery,medicine,grocery,
            bikes,properties,sports,smartphone,pets,electronics,laptops,cars,tickets,others;
    String parendb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);
        clotthings = findViewById(R.id.clotthing);
        household = findViewById(R.id.household);
        gadget = findViewById(R.id.gadgets);
        jewellery = findViewById(R.id.jewellery);
        medicine = findViewById(R.id.medicine);
        grocery = findViewById(R.id.groccery);
        bikes = findViewById(R.id.bikes);
        properties = findViewById(R.id.properties);
        sports = findViewById(R.id.sports);
        smartphone = findViewById(R.id.smartphone);
        pets = findViewById(R.id.pets);
        electronics = findViewById(R.id.electronics);
        laptops = findViewById(R.id.laptop);
        cars = findViewById(R.id.cars);
        tickets = findViewById(R.id.tickets);
        others = findViewById(R.id.others);
        parendb = getIntent().getStringExtra("ParentDB");


        clotthings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        household.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        gadget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        jewellery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        bikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });

        smartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });
        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });


        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("Catagory","Clothing ");
                intent.putExtra("ParentDB",parendb);
                startActivity(intent);
            }
        });


    }
}