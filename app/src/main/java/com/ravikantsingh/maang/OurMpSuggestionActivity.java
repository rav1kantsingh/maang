package com.ravikantsingh.maang;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
public class OurMpSuggestionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SuggestionAdapter mAdapter;
    private List<ModalClass> modalClassList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        mRecyclerView = findViewById(R.id.suggestionRV);
        mAdapter = new SuggestionAdapter(modalClassList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        setdatattolist();
    }

    private void setdatattolist() {

        try {
//Todo change the reference from posts to my mp posts.
            DatabaseReference mRefrence = FirebaseDatabase.getInstance().getReference().child("suggestions");
            mRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    modalClassList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        modalClassList.add(new ModalClass(String.valueOf(ds.child("related-sector").getValue()),
                                String.valueOf(ds.child("related-schemes").getValue()),
                                String.valueOf(ds.child("likes").getValue()),
                                String.valueOf(ds.child("comments").getValue()),
                                String.valueOf(ds.child("imglink").getValue()),
                                String.valueOf(ds.child("pdflink").getValue()),
                                String.valueOf(ds.child("description").getValue()),
                                String.valueOf(ds.child("timestamp").getValue()),
                                String.valueOf(ds.child("uid").getValue())));
                    }
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e){}
    }
}
