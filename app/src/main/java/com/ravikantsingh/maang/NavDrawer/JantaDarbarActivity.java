package com.ravikantsingh.maang.NavDrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.Adapters.JantaDarbarChatAdapter;
import com.ravikantsingh.maang.ModalClass.BaseMessage;
import com.ravikantsingh.maang.R;

import java.util.ArrayList;

public class JantaDarbarActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<BaseMessage> list;
    DatabaseReference reference;
    JantaDarbarChatAdapter adapter;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janta_darbar);

        init();
        loadAndUpdateUI();
    }

    void init() {
        recyclerView = findViewById(R.id.reyclerview_message_list);
        adapter = new JantaDarbarChatAdapter(list, this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
//        reference = FirebaseDatabase.getInstance().getReference().child(StringVariables.WMS_REPORT).child("hajipur").child(StringVariables.WMS_REGISTERED_WORK);
    }

    void loadAndUpdateUI() {
        try {
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    list.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        list.add(new WMSModelClass(
//                                String.valueOf(snapshot.child(StringVariables.WMS_RECOMMENDATION_DATE).getValue()),
//                                String.valueOf(snapshot.child(StringVariables.SANCTION_AMOUNT).getValue()),
//                                String.valueOf(snapshot.child(StringVariables.SECTOR).getValue()),
//                                String.valueOf(snapshot.child(StringVariables.SCHEME).getValue()),
//                                String.valueOf(snapshot.child(StringVariables.IMPLEMENTING_AGENCY).getValue())));
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
        }
    }

}
