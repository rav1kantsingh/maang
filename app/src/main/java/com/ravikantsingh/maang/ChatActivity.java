package com.ravikantsingh.maang;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.Adapters.JantaDarbarChatAdapter;
import com.ravikantsingh.maang.ModalClass.BaseMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    EditText writeMessage;
    Button send;
    RecyclerView chatRecycler;
    DatabaseReference chatRefference;
    String constituency;
    String gotIntent;
    String date, user;
    ArrayList<BaseMessage> list = new ArrayList<>();
    private JantaDarbarChatAdapter mAdapter;
    String userUID;


    @Nullable
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);
        userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        chatRecycler = findViewById(R.id.reyclerview_message_list);
        mAdapter = new JantaDarbarChatAdapter(list, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        chatRecycler.setLayoutManager(mLayoutManager);
        chatRecycler.setAdapter(mAdapter);

        writeMessage = findViewById(R.id.edittext_chatbox);
        gotIntent = getIntent().getStringExtra("user");
        Log.e("here", gotIntent);

        user = gotIntent.split("#!#")[0];
        date = gotIntent.split("#!#")[1];

        constituency = "Hazipur";

        chatRefference = FirebaseDatabase.getInstance().getReference().child("janta-darbar").child(constituency).child(date).child(user);

        chatRefference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                Log.e("here",dataSnapshot.toString() );
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.e("here",ds.toString() );
                    list.add(new BaseMessage(String.valueOf(ds.child("message").getValue()),
                            String.valueOf(ds.child("time").getValue()),
                            String.valueOf(ds.child("name").getValue()),
                            Integer.valueOf(String.valueOf(ds.child("type").getValue()))));
                    Log.e("here",String.valueOf(ds.child("type").getValue()));
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mAdapter.notifyDataSetChanged();
            }
        });

        SharedPreferences preferences = getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE, MODE_PRIVATE);
        final String name = preferences.getString("name", "");

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd-MM-YYYY");
        Date d = new Date();
        final String date = sdf.format(d);

        send = findViewById(R.id.button_chatbox_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = writeMessage.getText().toString();
                HashMap<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("time", date);
                map.put("type", "1");
                map.put("message", message);

                writeMessage.setText("");
                chatRefference.push().setValue(map);
            }
        });

    }
}