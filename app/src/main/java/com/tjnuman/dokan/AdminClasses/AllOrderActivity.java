package com.tjnuman.dokan.AdminClasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tjnuman.dokan.R;

public class AllOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        recyclerView = findViewById(R.id.customerorderrecyclerView);
    }
}