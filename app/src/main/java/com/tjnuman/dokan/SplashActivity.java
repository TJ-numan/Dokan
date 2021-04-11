package com.tjnuman.dokan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {
    Animation topAnimation, bottomAnimation;
    LottieAnimationView animationView;
    ImageView imageView;
    Button joinbutton,loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animationView = findViewById(R.id.animationView);
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.toptodown);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.downtotop);
        imageView = findViewById(R.id.image);
        joinbutton = findViewById(R.id.joinbutton);
        loginbutton = findViewById(R.id.loginbutton);
        imageView.setAnimation(bottomAnimation);
        joinbutton.setAnimation(bottomAnimation);
        loginbutton.setAnimation(bottomAnimation);
        animationView.setAnimation(topAnimation);

        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(SplashActivity.this,SignupActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        });


    }
    }