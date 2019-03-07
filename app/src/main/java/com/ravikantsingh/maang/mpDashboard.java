package com.ravikantsingh.maang;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.MP.MPJantaDarbar;

import java.util.HashMap;

public class mpDashboard extends AppCompatActivity {

        private LinearLayout myconstituencylinear,notificationlinear,myfeedlinear,jantadarbarlinear;
        private Button broadcastbtn;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mp_dashboard);

            myconstituencylinear = findViewById(R.id.myconstituencylinear);
            notificationlinear = findViewById(R.id.notificationlinear);
            myfeedlinear = findViewById(R.id.myfeedlinear);
            jantadarbarlinear = findViewById(R.id.jantadarbarlinear);

            broadcastbtn = findViewById(R.id.broadcastbutton);

            myconstituencylinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postintent = new Intent(mpDashboard.this,mpPostActivity.class);
                    startActivity(postintent);
                }
            });

            myfeedlinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mpDashboard.this,mpComplaintActivity.class);
                    intent.putExtra("MpClicked","1");
                    startActivity(intent);
                }
            });

            jantadarbarlinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postintent = new Intent(mpDashboard.this, MPJantaDarbar.class);
                    startActivity(postintent);
                }
            });

            notificationlinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postintent = new Intent(mpDashboard.this,mpPostActivity.class);
                    startActivity(postintent);
                }
            });

            broadcastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog();
                }
            });



        }

        private void openDialog() {

            final Dialog mDialog = new Dialog(this);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mDialog.setContentView(R.layout.activity_mp_broadcast);

            final EditText text = mDialog.findViewById(R.id.messagetext);
            final Button broadcasttoconstituency = mDialog.findViewById(R.id.broadcasttoconstituency);
            final Button broadcasttoda = mDialog.findViewById(R.id.broadcasttoda);

            mDialog.show();

            broadcasttoconstituency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    broadcasttoda.setClickable(false);
                    if(text.getText() != null){

                        final HashMap<String,String> messagemap = new HashMap<>();
                        messagemap.put("message",text.getText().toString());
                        messagemap.put("broadcasttype",broadcasttoconstituency.getText().toString());

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("MPnode")
                                .child("hajipur").child("MPmessages");

                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                mDatabase.push().setValue(messagemap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        mDialog.dismiss();
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }
            });

            broadcasttoda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    broadcasttoconstituency.setClickable(false);
                    if(text.getText() != null){

                        final HashMap<String,Object> messagemap1 = new HashMap<>();
                        messagemap1.put("message",text.getText());
                        messagemap1.put("broadcasttype",broadcasttoda.getText().toString());

                        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("MPnode")
                                .child("hajipur").child("MPmessages");

                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                mDatabase.push().setValue(messagemap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        mDialog.dismiss();
                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }
            });

        }
    }