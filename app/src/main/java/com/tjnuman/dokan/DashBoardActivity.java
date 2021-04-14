package com.tjnuman.dokan;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.tjnuman.dokan.Prevalent.Sessions;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
                Sessions.isLogin(DashBoardActivity.this,false);
                startActivity(new Intent(DashBoardActivity.this, SplashActivity.class));
                finish();
            }
        });


    //some code here for the dash board

    }
}