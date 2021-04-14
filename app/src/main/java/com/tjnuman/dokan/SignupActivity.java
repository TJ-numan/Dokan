package com.tjnuman.dokan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText userName, userPassword, userPhone, retypepassword;
    LottieAnimationView progressbar;
    Button signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = findViewById(R.id.fullname);
        userPhone = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        signupbtn = findViewById(R.id.signupbtn);
        progressbar = findViewById(R.id.progresslogin);
        retypepassword = findViewById(R.id.retypepassword);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String phone = userPhone.getText().toString();
        if(name.isEmpty())
        {
            userName.setError("Full Name is required");
            userName.requestFocus();
            return;
        }
        else if(!Patterns.PHONE.matcher(phone).matches())
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
            userPassword.setError("Password can't be empty");
            userPassword.requestFocus();
            return;
        }
        else if(password.length()<6)
        {
            userPassword.setError("Password length should be 6 charectars!");
            userPassword.requestFocus();
            return;
        }
        else if(retypepassword.equals(password))
        {
            retypepassword.setError("Password didn't match");
            userPassword.requestFocus();
            return;
        }
        else
        {
            progressbar.setVisibility(View.VISIBLE);
            userName.setEnabled(false);
            userPassword.setEnabled(false);
            userPhone.setEnabled(false);
            ValidatephoneNumber(name,phone,password);
        }


    }

    private void ValidatephoneNumber(String name, String phone, String password) {
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone",phone);
                    userdataMap.put("password",password);
                    userdataMap.put("name", name);

                    rootref.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {

                                    if(task.isSuccessful()){
                                        Toast.makeText(SignupActivity.this, "Congratulations! you account has been created", Toast.LENGTH_LONG).show();

                                        progressbar.setVisibility(View.GONE);
                                        userName.setEnabled(true);
                                        userPassword.setEnabled(true);
                                        userPhone.setEnabled(true);
                                        Intent intent = new Intent(SignupActivity.this,DashBoardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(SignupActivity.this, "Network error please try again", Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.GONE);
                                        userName.setEnabled(true);
                                        userPassword.setEnabled(true);
                                        userPhone.setEnabled(true);
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "An account with "+phone+"is already exist. please try with new one", Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.GONE);
                    userName.setEnabled(true);
                    userPassword.setEnabled(true);
                    userPhone.setEnabled(true);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}