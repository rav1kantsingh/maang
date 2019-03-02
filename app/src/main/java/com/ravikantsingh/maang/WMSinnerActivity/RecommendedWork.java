package com.ravikantsingh.maang.WMSinnerActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.Adapters.RecommendedWorkAdapter;
import com.ravikantsingh.maang.ModalClass.WMSModelClass;
import com.ravikantsingh.maang.R;
import com.ravikantsingh.maang.StringVariables;

import java.util.ArrayList;

public class RecommendedWork extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<WMSModelClass> list;
    DatabaseReference reference;
    RecommendedWorkAdapter adapter;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_work);
        list = new ArrayList<>();
        init();
        loadAndUpdateUI();
    }

    void init() {
        recyclerView = findViewById(R.id.recommend_recycler);
        list = new ArrayList<>();
        adapter = new RecommendedWorkAdapter(this, list);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference().child(StringVariables.WMS_REPORT).child("hajipur").child(StringVariables.WMS_RECOMMENDED);
    }

    void loadAndUpdateUI() {
        try {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    list.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        list.add(new WMSModelClass(
                                String.valueOf(snapshot.child(StringVariables.WMS_RECOMMENDATION_DATE).getValue()),
                                String.valueOf(snapshot.child(StringVariables.SANCTION_AMOUNT).getValue()),
                                String.valueOf(snapshot.child(StringVariables.SECTOR).getValue()),
                                String.valueOf(snapshot.child(StringVariables.SCHEME).getValue()),
                                String.valueOf(snapshot.child(StringVariables.IMPLEMENTING_AGENCY).getValue())));
                    }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                adapter.notifyDataSetChanged();
            }
        });
    }catch (Exception e){

        }
}}
