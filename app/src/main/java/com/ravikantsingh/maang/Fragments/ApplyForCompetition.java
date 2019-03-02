package com.ravikantsingh.maang.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ravikantsingh.maang.R;
import com.ravikantsingh.maang.StringVariables;

import java.util.HashMap;

public class ApplyForCompetition extends AppCompatActivity {

    private EditText name,innovationname,idea,howusable;
    Button submit;
    String name1,idea1,innovationname1,howusable1,userUID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addyourapplication);

        name = findViewById(R.id.editname);
        innovationname = findViewById(R.id.editinnovationname);
        idea = findViewById(R.id.editexplainidea);
        howusable = findViewById(R.id.howusableexplain);
        submit = findViewById(R.id.submitideabutton);

        final DatabaseReference mref1 = FirebaseDatabase.getInstance().getReference().child("onemp-oneidea").child("submissions");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFilleddata();
            }

            private void checkFilleddata() {

                name1 = name.getText().toString();
                idea1 = idea.getText().toString();
                innovationname1 = innovationname.getText().toString();
                howusable1 = howusable.getText().toString();

                if ((name1 != null || name1 == "") && (idea1 != null || idea1 != "") && (innovationname1 != null || innovationname1 != "")
                && (howusable1 != null || howusable1 != "")){

                    HashMap<String,Object> map = new HashMap<>();
                    map.put("innovator-name",name1);
                    map.put("innovation-name",innovationname1);
                    map.put("idea",idea1);
                    map.put("howusable",howusable1);
                    map.put("nomminate",0);
                    map.put("endorse",0);



                    SharedPreferences preferences = getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE,MODE_PRIVATE);
                    userUID = preferences.getString("userUID","0");

                    map.put("UUID",userUID);
                   String key =  mref1.push().getKey();
                   map.put("submissionid",key);
                   mref1.child(key).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(ApplyForCompetition.this,"Uploaded Bro..",Toast.LENGTH_LONG).show();
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ApplyForCompetition.this,"Fail Bro..",Toast.LENGTH_LONG).show();


                        }
                    });



                }

            }
        });


    }
}
