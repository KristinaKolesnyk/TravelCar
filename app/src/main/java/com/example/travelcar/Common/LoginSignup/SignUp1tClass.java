package com.example.travelcar.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.travelcar.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp1tClass extends AppCompatActivity {
    private TextInputEditText createName, createUsername, createEmail, createPassword;
    //Variables
    private ShapeableImageView backBTN;
    private Button next, login;
    private TextView titleText;
    private AutoCompleteTextView citiesAutocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1st_class);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Hooks
        backBTN = findViewById(R.id.profile_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title);
        createName = findViewById(R.id.create_name);
        createUsername = findViewById(R.id.create_username);
        createEmail = findViewById(R.id.create_email);
        createPassword = findViewById(R.id.create_password);
        citiesAutocomplete = findViewById(R.id.cities_autocomplete);
        myCitiesList();
    }

    private boolean validateName() {
        String val = createName.getText().toString().trim();
        if (val.isEmpty()) {
            createName.setError("Field can't be empty");
            return false;
        } else {
            createName.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = createUsername.getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()) {
            createUsername.setError("Field can't be empty");
            return false;
        } else if (val.length() > 20) {
            createUsername.setError("Username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            createUsername.setError("No spaces are allowed!");
            return false;
        } else {
            createUsername.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = createEmail.getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if (val.isEmpty()) {
            createEmail.setError("Field can't be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            createEmail.setError("Invalid Email!");
            return false;
        } else {
            createEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = createPassword.getText().toString().trim();
        if (val.isEmpty()) {
            createPassword.setError("Field can't be empty");
            return false;
        } else {
            createPassword.setError(null);
            return true;
        }
    }

    private void myCitiesList() {
        String[] cities = new String[]
                {
                        "Hadera", "Netanya", "Tel Aviv", "Ramat Gan", "Ashkelon", "Nahariya", "Haifa", "Eilat", "Caesarea", "Bat Yam"
                };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.dropdown_item, cities
        );
        citiesAutocomplete.setAdapter(adapter);
    }

    public void call2ndSignupScreen(View view) {

        if (!validateName() | !validateUsername() | !validateEmail() | !validatePassword()) {
            return;
        }

        String getName = createName.getText().toString();
        String getUser = createUsername.getText().toString();
        String getEmail = createEmail.getText().toString();
        String getPassword = createPassword.getText().toString();
        String getCity = citiesAutocomplete.getText().toString();
        //Pass data to 2nd screen
        Intent intent = new Intent(SignUp1tClass.this, SignUp2ndClass.class);
        intent.putExtra("name", getName);
        intent.putExtra("username", getUser);
        intent.putExtra("email", getEmail);
        intent.putExtra("password", getPassword);
        intent.putExtra("city", getCity);

        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(next, "transition_next_btn");
        pairs[1] = new Pair<View, String>(titleText, "transition_title_text");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp1tClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }

    public void callLoginScr(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(login, "transition_login_btn");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp1tClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }

    public void callStartUp1t(View view) {
        Intent intent = new Intent(getApplicationContext(), StartUpScreen.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(backBTN, "transition_back_btn");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp1tClass.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }
}
