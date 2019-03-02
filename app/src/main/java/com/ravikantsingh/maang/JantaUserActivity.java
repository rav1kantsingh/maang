package com.ravikantsingh.maang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.Adapters.JantaUserAdapter;

import java.util.ArrayList;

public class JantaUserActivity extends AppCompatActivity {

    RecyclerView jantaDateRecycler;
    String constitution;
    JantaUserAdapter mAdapter;
    ArrayList<String> list = new ArrayList();
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janta_user);
        constitution = "Hazipur";

        Intent intent = getIntent();
        final String getIntent = intent.getStringExtra("date");

        Log.e("log", getIntent);

        jantaDateRecycler = findViewById(R.id.janta_user);

        mAdapter = new JantaUserAdapter(this, list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        jantaDateRecycler.setLayoutManager(mLayoutManager);
        jantaDateRecycler.setAdapter(mAdapter);

        reference = FirebaseDatabase.getInstance().getReference().child("janta-darbar").child(constitution).child(getIntent);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    list.add(String.valueOf(ds.getKey())+"#!#"+getIntent);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
