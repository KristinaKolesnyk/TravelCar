package com.example.travelcar.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.travelcar.Common.LoginSignup.SignUp1tClass;
import com.example.travelcar.Common.LoginSignup.StartUpScreen;
import com.example.travelcar.R;

public class Rating extends AppCompatActivity {
    private RatingBar stars;
    private TextView feedback;
   private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        stars = findViewById(R.id.profile_rating);
        feedback = findViewById(R.id.rating_feedback);
        send = findViewById(R.id.rating_button);

        stars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0)
                    feedback.setText("Very dissatisfied");
                else if (rating == 1)
                    feedback.setText("Dissatisfied");
                else if (rating == 2 || rating == 3)
                    feedback.setText("OK");
                else if (rating == 4)
                    feedback.setText("Satisfied");
                else if (rating == 5)
                    feedback.setText("Very satisfied");
            }
        });
    }

    public void callDashFromRate(View view) {
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(send, "transition_feedback");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Rating.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }
}