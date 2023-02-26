package com.example.travelcar.Databases;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "isLoggedIn";

    public static final String KEY_NAME = "name";
    public static final String KEY_USER = "user";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_CITY = "city";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DATE = "date";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_PHONE = "phoneNo";

    public SessionManager(Context _context) {
        context = _context;
        userSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession.edit();

    }

    public void createLoginSession(String name, String username, String email, String phoneNo, String date, String password, String gender, String city) {
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, name);
        editor.putString(KEY_USER, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phoneNo);
        editor.putString(KEY_DATE, date);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_CITY, city);

        editor.commit();
    }

    public HashMap<String, String> getUserDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();
        userData.put(KEY_NAME, userSession.getString(KEY_NAME, null));
        userData.put(KEY_USER, userSession.getString(KEY_USER, null));
        userData.put(KEY_EMAIL, userSession.getString(KEY_EMAIL, null));
        userData.put(KEY_PHONE, userSession.getString(KEY_PHONE, null));
        userData.put(KEY_DATE, userSession.getString(KEY_DATE, null));
        userData.put(KEY_PASSWORD, userSession.getString(KEY_PASSWORD, null));
        userData.put(KEY_GENDER, userSession.getString(KEY_GENDER, null));
        userData.put(KEY_CITY, userSession.getString(KEY_CITY, null));

        return userData;
    }

    public boolean checkLogin() {
        if (userSession.getBoolean(IS_LOGIN, false)) {
            return true;
        } else
            return false;
    }

    public void logoutUserFromSession() {
        editor.clear();
        editor.commit();
    }
}
