package com.ravikantsingh.maang.NavDrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.Adapters.JantaDateAdapter;
import com.ravikantsingh.maang.R;

import java.util.ArrayList;

public class JantaDarbarActivity extends AppCompatActivity {

    RecyclerView jantaDateRecycler;
    String constitution;
    JantaDateAdapter mAdapter;
    ArrayList<String> list = new ArrayList();
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janta_darbar);

        jantaDateRecycler = findViewById(R.id.dates_janta);
        constitution = "Hazipur";
        //Todo get Constitution name.
        /*
         * get constitution name.
         *
         * */

        mAdapter = new JantaDateAdapter(this, list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        jantaDateRecycler.setLayoutManager(mLayoutManager);
        jantaDateRecycler.setAdapter(mAdapter);

        reference = FirebaseDatabase.getInstance().getReference().child("janta-darbar").child(constitution);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    list.add(String.valueOf(ds.getKey()));
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
