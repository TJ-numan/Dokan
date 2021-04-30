package com.tjnuman.dokan.UserClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tjnuman.dokan.R;

public class PaymentActivity extends AppCompatActivity {

    Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        nextbtn = findViewById(R.id.paymentNextButton);

        //doing some function for mayment mathoods.


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this,ConfirmActivity.class);
                startActivity(intent);
            }
        });
    }
}