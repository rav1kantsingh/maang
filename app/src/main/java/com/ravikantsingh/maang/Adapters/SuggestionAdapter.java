package com.ravikantsingh.maang.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ravikantsingh.maang.ModalClass.ModalClass;
import com.ravikantsingh.maang.R;

import java.util.HashMap;
import java.util.List;


public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.MyViewHolder> {

    private List<ModalClass> modalClassList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, time, description, relatedsector, relatedschemes,likescount,commentcount,rate;
        public SimpleDraweeView contentimg;
        public RelativeLayout like, comment, reportasspam;

        public MyViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.username);
            time = view.findViewById(R.id.timestamp);
            description = view.findViewById(R.id.descriptiontext);
            contentimg = view.findViewById(R.id.contentimg);
            like = view.findViewById(R.id.likelayout);
            comment = view.findViewById(R.id.commentlayout);
            reportasspam = view.findViewById(R.id.reportasspam);
            relatedschemes = view.findViewById(R.id.relatedschemes);
            relatedsector = view.findViewById(R.id.relatedsector);
            likescount = view.findViewById(R.id.likecount);
            commentcount = view.findViewById(R.id.commentcount);


        }

    }


    public SuggestionAdapter(List<ModalClass> modalClassList, Context context) {
        this.modalClassList = modalClassList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_post_suggestion_complain, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ModalClass modalClass = modalClassList.get(position);
        holder.relatedsector.setText(modalClass.getRelatedsector());
        try{
        if(modalClass.getDescription().equals("null")){
            modalClass.getDescription().equals("");
        }
        if(modalClass.getTextHash().equals("null")){
            modalClass.getTextHash().equals("");
        }}catch (Exception e){

        }
        String temp = modalClass.getDescription()+"\n\n"+modalClass.getTextHash();
        holder.relatedschemes.setText(modalClass.getRelatedscheme());
        holder.description.setText(temp);
        holder.time.setText(modalClass.getTimestamp());
        final String key = modalClass.getUid();

        final String currentuserUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        holder.username.setText(modalClass.getUserName());
        try {
            String imgurl = modalClass.getImglink();

            StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(imgurl);
            if(!(imgurl.equals("")||imgurl.equals("null"))){
                holder.contentimg.setImageURI(imgurl);
            }else {
                holder.contentimg.setVisibility(View.GONE);
            }


        } catch (Exception e) {

        }
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put(currentuserUid,0);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Suggestions").child(key).child("likesBy");
                mDatabase.updateChildren(map, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(context,"Updated",Toast.LENGTH_LONG).show();
                    }
                });
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        holder.likescount.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put(currentuserUid,0);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Suggestions").child(key).child("commentsBy");
                mDatabase.updateChildren(map, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(context,"Updated",Toast.LENGTH_LONG).show();
                    }
                });
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        holder.commentcount.setText(String.valueOf(dataSnapshot.getChildrenCount()));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        holder.reportasspam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] reportcount = new int[1];

                HashMap<String,Object> map = new HashMap<>();
                map.put(currentuserUid,0);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Suggestions").child(key).child("reportasSpam");
                mDatabase.updateChildren(map, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(context,"Reported",Toast.LENGTH_LONG).show();
                    }
                });
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        reportcount[0] = (int) dataSnapshot.getChildrenCount();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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