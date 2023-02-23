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
import android.widget.ScrollView;

import com.example.travelcar.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;


public class SignUp3rdClass extends AppCompatActivity {
    private ScrollView scrollView;
    private Button login;
    private ShapeableImageView back;
    private TextInputEditText phoneNumber;
    //CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd_class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        scrollView = findViewById(R.id.signup_3rd_screen);
        phoneNumber = findViewById(R.id.signup_phone_number_edit_text);
        login = findViewById(R.id.signup_login_button);
        back = findViewById(R.id.signup_back_button);

        //countryCodePicker = findViewById(R.id.country_code);
    }

    public void callVerifyOTPScreen(View view) {


        String _fullName = getIntent().getStringExtra("name");
        String _email = getIntent().getStringExtra("email");
        String _username = getIntent().getStringExtra("username");
        String _password = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");
        String _city = getIntent().getStringExtra("city");

        String _phoneNo = phoneNumber.getText().toString();

        Intent intent = new Intent(getApplicationContext(), VerifyOTP.class);
        intent.putExtra("name", _fullName);
        intent.putExtra("email", _email);
        intent.putExtra("username", _username);
        intent.putExtra("password", _password);
        intent.putExtra("date", _date);
        intent.putExtra("gender", _gender);
        intent.putExtra("phoneNo", _phoneNo);
        intent.putExtra("city", _city);

        //Add Transition
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(scrollView, "transition_OTP_screen");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
    public void callLoginFrom3d(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(login, "transition_login_BTN");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }
    public void callSignUp2From3(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(back, "transition_back_btn");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }
}