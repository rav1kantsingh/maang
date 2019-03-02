package com.ravikantsingh.maang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class JantaUserActivity extends AppCompatActivity {

    RecyclerView jantaUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janta_user);

        jantaUser = findViewById(R.id.janta_user);

    }
}
