package com.example.travelcar.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelcar.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Calendar;

public class SignUp2ndClass extends AppCompatActivity {
    private ShapeableImageView backBTN;
    private Button next, login;
    private TextView titleText;
    private RadioGroup radioGroup;
    private RadioButton selectGender;
    private DatePicker agePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd_class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Hooks
        backBTN = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title);
        radioGroup = findViewById(R.id.radio_group);
        agePicker = findViewById(R.id.age_picker);
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = agePicker.getYear();
        int isAgeValid = currentYear - userAge;
        if (isAgeValid < 17) {
            Toast.makeText(this, "Your age is too young to use the program.", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }
    public void call3dSignupScreen(View view) {
        if (!validateAge() | !validateGender()) {
            return;
        }

        selectGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String _gender = selectGender.getText().toString();
        int day = agePicker.getDayOfMonth();
        int month = agePicker.getMonth();
        int year = agePicker.getYear();

        String _date = day+"/"+month+"/"+year;

        String name = getIntent().getStringExtra("name");
        String user = getIntent().getStringExtra("user");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String city = getIntent().getStringExtra("city");

        Intent intent = new Intent(getApplicationContext(), SignUp3rdClass.class);
        intent.putExtra("date", _date);
        intent.putExtra("gender", _gender);
        intent.putExtra("name", name);
        intent.putExtra("username", user);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("city", city);

        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(next, "transition_next_btn");
        pairs[1] = new Pair<View, String>(titleText, "transition_title_text");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }

    public void callLoginFrom2nd(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(login, "transition_login_BTN");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }
    public void callSignUp1From2nd(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp1tClass.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(backBTN, "transition_back_btn");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }
}