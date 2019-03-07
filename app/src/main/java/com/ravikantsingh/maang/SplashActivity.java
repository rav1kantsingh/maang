package com.ravikantsingh.maang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ravikantsingh.maang.Authentication.LoginActivity;
import com.ravikantsingh.maang.Registration.RegistrationActivity;



public class SplashActivity extends AppCompatActivity {

    SharedPreferences preferences;
    ImageView maangSplash, splashLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        maangSplash = (ImageView) findViewById(R.id.maang_splash) ;
        splashLine = (ImageView) findViewById(R.id.splash_line) ;

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        maangSplash.startAnimation(myanim);
        splashLine.startAnimation(myanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadUserDetails();
            }
        }, 3000);
    }

    void LoadUserDetails() {

        preferences = this.getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE, MODE_PRIVATE);
        SharedPreferences preferences_login = getSharedPreferences("UserLoggedIn", MODE_PRIVATE);

        if (preferences_login.getInt("UserLoggedIn", 0) == 1) {
            // user is login.

            if (preferences.getInt("registered", 0) == 1) {

                if(preferences.getString("userType","1").equals("2")){
                    Intent i = new Intent(SplashActivity.this, mpDashboard.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
                else if(preferences.getString("userType","1").equals("1")){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }

                // user is registered.

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
