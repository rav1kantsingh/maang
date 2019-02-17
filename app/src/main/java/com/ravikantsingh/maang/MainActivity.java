package com.ravikantsingh.maang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.ravikantsingh.maang.Authentication.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLoginStatus();
        checkRegistrationStatus();

    }

    public void checkLoginStatus(){
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
    public void checkRegistrationStatus(){
        //Todo check firebase for user registration.
        //if not registerd open registration activity and onsuccess finish that activity.
    }
}
