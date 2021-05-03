package com.tjnuman.dokan.AdminClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tjnuman.dokan.R;

import androidx.appcompat.app.AppCompatActivity;

public class DashBoardActivity extends AppCompatActivity {

    Button allcatagory,customerlist,allproudct;
    String parentDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        allcatagory = findViewById(R.id.uploadProducts);
        customerlist = findViewById(R.id.orderfromallcustomer);
        allproudct = findViewById(R.id.manageallproduct);
        parentDB = getIntent().getStringExtra("ParentDB");

        allproudct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,CatagoryProductActivity.class);
                intent.putExtra("ParentDB",parentDB);
                startActivity(intent);
            }
        });


        allcatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(DashBoardActivity.this,AdminCatagoryActivity.class);
                intent.putExtra("ParentDB",parentDB);
                startActivity(intent);
            }
        });

        customerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent2 = new Intent(DashBoardActivity.this,AllOrderActivity.class);
                intent2.putExtra("ParentDB",parentDB);
                startActivity(intent2);
            }
        });

    //some code here for the dash board

    }
}