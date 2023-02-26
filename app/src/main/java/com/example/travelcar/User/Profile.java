package com.example.travelcar.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.travelcar.Common.LoginSignup.SignUp1tClass;
import com.example.travelcar.Common.LoginSignup.StartUpScreen;
import com.example.travelcar.Databases.SessionManager;
import com.example.travelcar.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class Profile extends AppCompatActivity {
    private String fullName, username, email, city;
    private ShapeableImageView backBTN;
    private TextView name, user, mail, cityUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.profile_user_name);
        user = findViewById(R.id.profile_user_username);
        mail = findViewById(R.id.profile_user_email);
        cityUser = findViewById(R.id.profile_user_city);
        backBTN = findViewById(R.id.profile_back_button);

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> userDetails = sessionManager.getUserDetailFromSession();

        fullName = userDetails.get(SessionManager.KEY_NAME);
        username = userDetails.get(SessionManager.KEY_USER);
        email = userDetails.get(SessionManager.KEY_EMAIL);
        city = userDetails.get(SessionManager.KEY_CITY);

        name.setText(fullName);
        user.setText(username);
        mail.setText(email);
        cityUser.setText(city);
    }

    public void callMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(backBTN, "transition_back_btn");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Profile.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }
}