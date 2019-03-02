package com.ravikantsingh.maang.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.Adapters.SuggestionAdapter;
import com.ravikantsingh.maang.R;
import com.ravikantsingh.maang.StringVariables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeeSubmission extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SubbmissionAdapter mAdapter;
    private List<ModalClass1> modalClassList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        mRecyclerView = findViewById(R.id.suggestionRV);
        mAdapter = new SubbmissionAdapter(modalClassList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        setdatattolist();
    }

    private void setdatattolist() {

        try {

            DatabaseReference mRefrence = FirebaseDatabase.getInstance().getReference().child("onemp-oneidea").child("submissions");
            mRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d("DataSnapshot", dataSnapshot.getValue().toString());
                    modalClassList.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        modalClassList.add(new ModalClass1(String.valueOf(ds.child("innovator-name").getValue()),
                                String.valueOf(ds.child("innovation-name").getValue()),
                                String.valueOf(ds.child("idea").getValue()),
                                String.valueOf(ds.child("submissionid").getValue()) ));
                    }
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
        }
    }
}

 class ModalClass1{

    String name,innovationname,idea,key;

    public ModalClass1(){}
    public ModalClass1(String name, String innovationname, String idea, String key){
        this.name = name;
        this.innovationname = innovationname;
        this.idea = idea;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInnovationname() {
        return innovationname;
    }

    public void setInnovationname(String innovationname) {
        this.innovationname = innovationname;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

 class SubbmissionAdapter extends RecyclerView.Adapter<SubbmissionAdapter.MyViewHolder> {

    private List<ModalClass1> modalClassList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, innovationname, ideaname, endorse,nominate,endorsecount,nomineecount;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            innovationname = view.findViewById(R.id.innovation_name);
            ideaname = view.findViewById(R.id.detailofinnovation);
            endorse = view.findViewById(R.id.endorse);
            nominate = view.findViewById(R.id.nominate);
            endorsecount = view.findViewById(R.id.endorsecount);
            nomineecount = view.findViewById(R.id.nommineecount);
        }

    }


    public SubbmissionAdapter(List<ModalClass1> modalClassList, Context context) {
        this.modalClassList = modalClassList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_onemp_oneidea, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ModalClass1 modalClass = modalClassList.get(position);
        holder.name.setText(modalClass.getName());
        holder.innovationname.setText(modalClass.getInnovationname());
        holder.ideaname.setText(modalClass.getIdea());

        final String key = modalClass.getKey();
        holder.endorse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference mref2 = FirebaseDatabase.getInstance().getReference().child("onemp-oneidea").child("submissions").child(key).child("endorse");
                Map<String, Object> map = new HashMap<>();
               String currentUserid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                map.put(currentUserid,0);
                mref2.updateChildren(map, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(context,"updated bro",Toast.LENGTH_LONG).show();
                        mref2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int childcount = (int) dataSnapshot.getChildrenCount();
                                holder.endorsecount.setText(String.valueOf(childcount));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                });

            }
        });

        holder.nominate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference mref2 = FirebaseDatabase.getInstance().getReference().child("onemp-oneidea").child("submissions").child(key).child("nomminate");
                Map<String, Object> map = new HashMap<>();
                String currentUserid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                map.put(currentUserid,0);
                mref2.updateChildren(map, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(context,"updated bro",Toast.LENGTH_LONG).show();
                        mref2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int childcount = (int) dataSnapshot.getChildrenCount();
                                holder.nomineecount.setText(String.valueOf(childcount));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return modalClassList.size();
    }
}
