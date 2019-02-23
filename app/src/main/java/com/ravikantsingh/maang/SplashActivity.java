package com.ravikantsingh.maang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.Authentication.LoginActivity;
import com.ravikantsingh.maang.Registration.RegistrationActivity;

public class SplashActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    String userUID = "";
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
        LoadUserDetails();

    }

    void init() {
    }

    void LoadUserDetails() {
        //user has never logged in.
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        //user has earlier logged in app.
        try {
            userUID = mAuth.getCurrentUser().getUid();
        } catch (Exception e) {
        }
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.USERS);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (String.valueOf(dataSnapshot.child(userUID).getValue()).equals("") || String.valueOf(dataSnapshot.child(userUID).getValue()).equals("null")) {
                        Intent i = new Intent(SplashActivity.this, RegistrationActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
