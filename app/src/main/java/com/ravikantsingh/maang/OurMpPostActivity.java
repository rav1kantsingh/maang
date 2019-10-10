package com.ravikantsingh.maang;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class OurMpPostActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SuggestionAdapter mAdapter;
    private List<ModalClass> modalClassList = new ArrayList<>();

    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mRecyclerView = findViewById(R.id.suggestionRV);

        mAdapter = new SuggestionAdapter(modalClassList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        try {
//Todo change the reference from posts to my mp posts.
            DatabaseReference mRefrence = FirebaseDatabase.getInstance().getReference().child("MPnode").child("hajipur").child("MPmessages");
            mRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    modalClassList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        modalClassList.add(new ModalClass(String.valueOf(ds.child("broadcasttype").getValue()),
                                String.valueOf(ds.child("message").getValue())));
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

