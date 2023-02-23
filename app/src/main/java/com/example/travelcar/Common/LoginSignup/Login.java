package com.example.travelcar.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelcar.R;
import com.example.travelcar.User.UserDashboard;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private TextInputEditText phoneNumber, password;
    Button logBTN, createBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);
        logBTN = findViewById(R.id.login_log_btn);
        createBTN = findViewById(R.id.login_create_account);
    }

    public void callDashScreen(View view) {

        if (!validateFields()) {
            return;
        }
        Intent intent = new Intent(Login.this, UserDashboard.class);
        String getPhone = phoneNumber.getText().toString().trim();
        String getPassword = password.getText().toString().trim();

        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNo").equalTo(getPhone);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    phoneNumber.setError((null));
                    phoneNumber.setEnabled(false);

                    String systemPassword = snapshot.child(getPhone).child("password").getValue(String.class);
                    if (systemPassword.equals(getPassword)) {
                        password.setError((null));
                        password.setEnabled(false);

                        String _fullname = snapshot.child(getPhone).child("fullName").getValue(String.class);
                        String _username = snapshot.child(getPhone).child("username").getValue(String.class);
                        String _city = snapshot.child(getPhone).child("city").getValue(String.class);
                        String _date = snapshot.child(getPhone).child("date").getValue(String.class);
                        String _email = snapshot.child(getPhone).child("email").getValue(String.class);
                        String _gender = snapshot.child(getPhone).child("gender").getValue(String.class);

                        Toast.makeText(Login.this, _fullname + "\n" + _email + "\n" + _username + "\n" + _city + "\n" + _date + "\n" + _gender, Toast.LENGTH_SHORT);

                    } else {
                        Toast.makeText(Login.this, "Password does not match", Toast.LENGTH_SHORT);
                    }
                } else {
                    Toast.makeText(Login.this, "User does not exist", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(logBTN, "transition_dash_screen");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }

    private boolean validateFields() {
        String _password = password.getText().toString().trim();
        String _phone = phoneNumber.getText().toString().trim();
        if (_password.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (_phone.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else {
            password.setError(null);
            phoneNumber.setError(null);
            return true;
        }
    }

    public void callCreateScreen(View view) {
        Intent intent = new Intent(Login.this, SignUp1tClass.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(createBTN, "transition_create_screen");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intent, options.toBundle());
        } else startActivity(intent);
    }

}