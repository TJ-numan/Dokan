package com.tjnuman.dokan;

import android.content.Intent;
import android.os.Bundle;

import com.tjnuman.dokan.Prevalent.Sessions;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class DashBoardActivity extends AppCompatActivity {
    Button logoutbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        logoutbtn = findViewById(R.id.logoutbtn);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FirebaseAuth.getInstance().signOut();
               Sessions.isLoginUser(DashBoardActivity.this,false);
                startActivity(new Intent(DashBoardActivity.this, SplashActivity.class));
                finish();
            }
        });


    //some code here for the dash board

    }
}