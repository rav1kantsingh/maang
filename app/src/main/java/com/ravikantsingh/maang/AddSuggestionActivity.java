package com.ravikantsingh.maang;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class AddSuggestionActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner related_sector;
    private Spinner related_schemes;
    private TextView mDescriptionText, pdffile;
    private Button mUploadpdf, mSubmit, suggestion_to_mp, suggestion_to_da;
    private ImageView mContentimg;
    private DatabaseReference mRefrence1, mRefrence2, mRefrence3;
    private ArrayList<String> sectorlist, schemelist;
    private int SELECT_FILE = 1;
    private Uri filePath, pathHolder;
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference userReference;
    ArrayAdapter<String> adp2, adp1;
    String userUID;
    String pdfPath,photoPath,name;
    int flag = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_ur_suggestion);

        related_schemes = findViewById(R.id.relatedshemesspinner);
        related_sector = findViewById(R.id.relatedsectorspinner);
        mDescriptionText = findViewById(R.id.discription);
        mUploadpdf = findViewById(R.id.upload_pdf);
        mSubmit = findViewById(R.id.submit);
        mContentimg = findViewById(R.id.content_img);
        pdffile = findViewById(R.id.pdffilename);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        userReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.USERS);

        mRefrence1 = FirebaseDatabase.getInstance().getReference().child("sectors");
        mRefrence3 = FirebaseDatabase.getInstance().getReference().child("suggestions");
        try {
            SharedPreferences preferences = getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE, MODE_PRIVATE);
            userUID = preferences.getString("userUID", "hello");
            name = preferences.getString("name","");
        } catch (Exception e) {
            userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }


        addListtoSpinner();

        mContentimg.setOnClickListener(this);
        mUploadpdf.setOnClickListener(this);
        mSubmit.setOnClickListener(this);


    }

    private void addListtoSpinner() {

        sectorlist = new ArrayList();
        schemelist = new ArrayList();

        sectorlist.add("SectorA");
        sectorlist.add("SectorB");
        sectorlist.add("SectorC");

        schemelist.add("SchemeA");
        schemelist.add("SchemeB");
        schemelist.add("SchemeC");
        schemelist.add("SchemeD");


//        try {
//            mRefrence1.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    sectorlist.clear();
//                    for (DataSnapshot dp : dataSnapshot.getChildren()) {
//                        sectorlist.add(dp.getKey());
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        } catch (Exception e) {
//        }


//        related_sector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                final String sector = String.valueOf(related_sector.getSelectedItem());
//                mRefrence1.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        schemelist.clear();
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            if (sector.equals(ds.getKey())) {
//                                mRefrence2 = FirebaseDatabase.getInstance().getReference().child("sectors").child(sector);
//                                mRefrence2.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
//                                            schemelist.add(String.valueOf(ds1.getKey()));
//                                        }
//                                        adp2.notifyDataSetChanged();
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        adp1 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sectorlist);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        related_sector.setAdapter(adp1);

        adp2 = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, schemelist);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        related_schemes.setAdapter(adp2);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.content_img:
                uploadimg();
                break;

            case R.id.upload_pdf:
                uploadpdf();
                break;

            case R.id.submit:
                checkdatafilled();
                break;

        }

    }

    private void uploadimg() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

    }

    private void uploadpdf() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/msword,application/pdf");
        intent.setType("pdf/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 7);

    }

    private void checkdatafilled() {

        if (filePath != null || pathHolder != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    try {
                        StorageReference ref1 = storageReference.child("pdf/" + UUID.randomUUID().toString());
                        ref1.putFile(pathHolder).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(AddSuggestionActivity.this, "Uploaded pdf", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddSuggestionActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }catch (Exception e){

                    }

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddSuggestionActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded image" + (int) progress + "%");
                        }
                    });

        }


        if (related_sector.getSelectedItem() != null && related_schemes.getSelectedItem() != null
                && (mDescriptionText.getText() != null || mDescriptionText.getText().toString().trim() != "")
                && pdffile.getText().toString() != null) {

            final HashMap<String, Object> suggestionmap = new HashMap<>();
            suggestionmap.put("related-sector", related_sector.getSelectedItem().toString());
            suggestionmap.put("related-scheme", related_schemes.getSelectedItem().toString());
            suggestionmap.put("description", mDescriptionText.getText().toString());
            suggestionmap.put("likes", "");
            suggestionmap.put("comments", "");
            suggestionmap.put("likesBy", "");
            suggestionmap.put("commentsBy", "");
            suggestionmap.put("name",name);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
            Date d = new Date();
            String date = sdf.format(d);
            suggestionmap.put("Time",date);
            try {
                suggestionmap.put("imglink", filePath.toString());
            }catch (Exception e){

            }
            try {
                suggestionmap.put("pdflink", pathHolder.toString());
            }catch (Exception e){

            }
            suggestionmap.put("suggestion-type", flag);
            suggestionmap.put("userUID", userUID);

            mRefrence3.push().setValue(suggestionmap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    mDescriptionText.setText("");
                    pdffile.setText("Upload pdf file related to complaint");
                    mContentimg.setImageBitmap(null);
                    mContentimg.setBackgroundResource(R.drawable.ic_camera_alt_black_24dp);
                    related_schemes.setSelection(0);
                    related_sector.setSelection(0);
                    flag = 0;
                    suggestionmap.clear();
                    Toast.makeText(AddSuggestionActivity.this, "Suggestion Submitted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == 7) {
                pathHolder = data.getData();
                try {
                    String PathHolder = data.getData().getPath();
                    pdffile.setText(PathHolder);
                } catch (Exception e) {
                }
            }
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            filePath = data.getData();
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mContentimg.setImageBitmap(bm);
    }
}