package com.ravikantsingh.maang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ravikantsingh.maang.Authentication.LoginActivity;
import com.ravikantsingh.maang.Registration.RegistrationActivity;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LoadUserDetails();
    }

    void LoadUserDetails() {

        preferences = this.getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE, MODE_PRIVATE);
        SharedPreferences preferences_login = getSharedPreferences("UserLoggedIn", MODE_PRIVATE);

        if (preferences_login.getInt("UserLoggedIn", 0) == 1) {
            // user is login.

            if (preferences.getInt("registered", 0) == 1) {
                // user is registered.
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            } else {
                //user is not registered.
                Intent i = new Intent(SplashActivity.this, RegistrationActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }

        } else {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
