package com.ravikantsingh.maang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.Adapters.SuggestionAdapter;
import com.ravikantsingh.maang.ModalClass.ModalClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravikant Singh on 27,February,2019
 */
public class MyPost extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SuggestionAdapter mAdapter;
    private List<ModalClass> modalClassList = new ArrayList<>();
    FloatingActionButton fab;
    String userUID;
    ArrayList<String> postList;
    ArrayList<ModalClass> postList2;

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mRecyclerView = findViewById(R.id.suggestionRV);

        mAdapter = new SuggestionAdapter(modalClassList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        fab = findViewById(R.id.fab);
        postList = new ArrayList<>();
        postList2 = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE, MODE_PRIVATE);
        userUID = preferences.getString("userUID", "");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyPost.this,AddPostActivity.class));
            }
        });

        try {
            DatabaseReference mRefrence = FirebaseDatabase.getInstance().getReference().child(StringVariables.USERS).child(userUID).child("posts");

            mRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    postList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        postList.add(ds.getKey());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
        }

        try {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("posts");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    postList2.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        postList2.add(new ModalClass(String.valueOf(ds.child("related-sector").getValue()),
                                String.valueOf(ds.child("related-schemes").getValue()),
                                String.valueOf(ds.child("likes").getValue()),
                                String.valueOf(ds.child("comments").getValue()),
                                String.valueOf(ds.child("imglink").getValue()),
                                String.valueOf(ds.child("pdflink").getValue()),
                                String.valueOf(ds.child("description").getValue()),
                                String.valueOf(ds.child("timestamp").getValue()),
                                String.valueOf(ds.child("uid").getValue())));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {

        }
    }

}