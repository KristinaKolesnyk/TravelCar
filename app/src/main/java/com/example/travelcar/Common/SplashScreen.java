package com.example.travelcar.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.travelcar.Common.LoginSignup.StartUpScreen;
import com.example.travelcar.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Stack;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIMER = 4000; //4sec
    //Variables
    private ShapeableImageView backgroundImage;
    private TextView travelCar;
    //Animations
    private Animation topAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        //Hooks
        backgroundImage = findViewById(R.id.background_image);
        travelCar = findViewById(R.id.travel_car);
        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //set Animations on element
        backgroundImage.setAnimation(topAnim);
        travelCar.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, StartUpScreen.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }
}