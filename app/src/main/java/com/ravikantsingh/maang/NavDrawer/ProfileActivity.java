package com.ravikantsingh.maang.NavDrawer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ravikantsingh.maang.R;
import com.ravikantsingh.maang.StringVariables;

public class ProfileActivity extends AppCompatActivity {
    public ImageView camera;
    public int x = 1;
    public ImageView backprofilebtn;
    public ImageView backprofilepic;
    public int REQUEST_CAMERA = 1, SELECT_File = 0;
    public SimpleDraweeView userprofile;
    TextView userName,constName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        camera = (ImageView) findViewById(R.id.pencil);
        backprofilebtn = findViewById(R.id.pen);
        userName = findViewById(R.id.user_profile_name);
        constName = findViewById(R.id.user_profile_short_bio);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE,MODE_PRIVATE);
        userName.setText(preferences.getString("name","Not Available"));
//        constName.setText(preferences.getString("name","Not Available"));


        backprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 2;

                backprofilepic = findViewById(R.id.header_cover_image);

                selectImage();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          x = 3;

                                          userprofile = findViewById(R.id.user_profile_photo);
                                          selectImage();


                                      }
                                  }
        );
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {

                    Intent ii = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(ii, REQUEST_CAMERA);

                } else if (items[item].equals("Choose from Library")) {
                    Intent ii = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    ii.setType("image/*");

                    startActivityForResult(ii.createChooser(ii, "Select File"), SELECT_File);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onActivityResult(int Requestcode, int resultcode, Intent data) {
        super.onActivityResult(resultcode, resultcode, data);
        if (resultcode == Activity.RESULT_OK) {
            if (Requestcode == REQUEST_CAMERA) {
                Bundle bundle = data.getExtras();
                final Bitmap bitmap = (Bitmap) bundle.get("data");
                //Bitmap resized = Bitmap.createScaledBitmap(imp, 600, 600, true);
                //Bitmap conv_bm = getRoundedRectBitmap(resized, 600);
                if (x == 3) {
                    userprofile.setImageBitmap(bitmap);
                } else if (x == 2) {
                    backprofilepic.setImageBitmap(bitmap);
                }
            } else if (Requestcode == SELECT_File) {


                Uri selectImageUri = data.getData();
                if (x == 3) {
                    userprofile.setImageURI(selectImageUri);

                } else if (x == 2) {
                    backprofilepic.setImageURI(selectImageUri);
                }
            }
        }
    }

}
