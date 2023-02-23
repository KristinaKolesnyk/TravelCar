package com.example.travelcar.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.travelcar.Common.LoginSignup.StartUpScreen;
import com.example.travelcar.Common.SplashScreen;
import com.example.travelcar.R;
import com.google.android.material.imageview.ShapeableImageView;

public class JoinScreen extends AppCompatActivity {
    private static int SPLASH_TIMER = 6000; //6sec
    private ShapeableImageView successImage;
    private Animation topAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        successImage = findViewById(R.id.success_image);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);

        successImage.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(JoinScreen.this, UserDashboard.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }
}