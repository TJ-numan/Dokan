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


        clotthings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Clothing ");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        household.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Household");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        gadget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Gadgets");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        jewellery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","jewellery");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Medicine");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Grocery");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        bikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Bikes");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Properties");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Sports");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });

        smartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Smartphones");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });
        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Pets");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });
        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Electronics");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });
        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Laptops");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });


        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Cars");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Tickets");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCatagoryActivity.this,AdminAddNewProductActivity.class);
                Bundle data1 = new Bundle();
                data1.putString("Catagory","Others");
                intent.putExtras(data1);
                startActivity(intent);
            }
        });


    }
}