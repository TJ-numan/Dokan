package com.tjnuman.dokan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tjnuman.dokan.Model.Users;
import com.tjnuman.dokan.Prevalent.Sessions;

public class LoginActivity extends AppCompatActivity {
    EditText userPhone,userPaassword;
    Button loginbutton;
    LottieAnimationView progressbar;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userPaassword = findViewById(R.id.loginpassword);
        userPhone = findViewById(R.id.loginnumber);
        loginbutton = findViewById(R.id.loginbtn);
        progressbar = findViewById(R.id.progress);
        checkBox = findViewById(R.id.chekbox);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String password = userPaassword.getText().toString();
        String phone = userPhone.getText().toString();

        if(!Patterns.PHONE.matcher(phone).matches())
        {
            userPhone.setError("Please provide valid phone");
            userPhone.requestFocus();
            return;
        }
        else if(phone.length()<11)
        {
            userPhone.setError("Phone number should be 11 character!");
            userPhone.requestFocus();
            return;
        }
        else if(password.isEmpty())
        {
            userPaassword.setError("Password can't be empty");
            userPaassword.requestFocus();
            return;
        }
        else if(password.length()<6)
        {
            userPaassword.setError("Password length should be 6 character!");
        }
        else
        {
            progressbar.setVisibility(View.VISIBLE);
            userPhone.setEnabled(false);
            userPaassword.setEnabled(false);
            loginbutton.setEnabled(false);
            AllowAccessToAccount(phone, password);
        }

    }

    private void AllowAccessToAccount(String phone, String password) {
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(phone).exists())
                {

                    Users userData = snapshot.child("Users").child(phone).getValue(Users.class);
                    if(userData.getPhone().equals(phone)){
                        if(userData.getPassword().equals(password)){
                            if(checkBox.isChecked()){
                                Sessions.isLogin(LoginActivity.this,true);
                            }
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);
                            Intent intent = new Intent(LoginActivity.this,DashBoardActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "The password is in correct", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);
                            userPhone.setEnabled(true);
                            userPaassword.setEnabled(true);
                            loginbutton.setEnabled(true);
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Account with this "+phone+"number does not exist", Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.GONE);
                    userPhone.setEnabled(true);
                    userPaassword.setEnabled(true);
                    loginbutton.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}