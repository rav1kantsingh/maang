package com.ravikantsingh.maang.Registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ravikantsingh.maang.MainActivity;
import com.ravikantsingh.maang.R;
import com.ravikantsingh.maang.StringVariables;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    EditText nameTv, emailTv, passwordTv, confirm_passwordTv, aadharTv, dobTv, contactNoTv, whatsappNoTv;
    String name = "", email = "", password = "", confirm_password = "", aadhar, dob = "", contactNo = "", whatsappNo = "", gender = "";
    Button submitBtn, male, female, other;
    DatabaseReference databaseReference;
    String userUID = "";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registration);

        init();
        try {
            userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        } catch (Exception e) {
        }
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "other";
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo check for gender validity.
                boolean isAllDataRight;
                databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.USERS);
                readValue();
                isAllDataRight = checkDataEntered();
                if (isAllDataRight) {

                    progressDialog = new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setMessage("Creating User");
                    progressDialog.show();

                    HashMap<String, String> map = new HashMap<>();
                    map.put("name", name);
                    map.put("aadhar", aadhar);
                    map.put("DOB", dob);
                    map.put("gender", gender);
                    map.put("phoneNo", contactNo);
                    map.put("whatsapp", whatsappNo);

                    SharedPreferences preferences = getApplicationContext().getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE,MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name",name);
                    editor.putString("aadhar", aadhar);
                    editor.putString("DOB", dob);
                    editor.putString("gender", gender);
                    editor.putString("phoneNo", contactNo);
                    editor.putString("whatsapp", whatsappNo);
                    editor.apply();

                    databaseReference.child(userUID).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Intent i = new Intent(RegistrationActivity.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }
                    });
                }
            }
        });

    }

    void init() {
        nameTv = findViewById(R.id.name);
        aadharTv = findViewById(R.id.aadhar);
        dobTv = findViewById(R.id.dob);
        contactNoTv = findViewById(R.id.contactNo);
        whatsappNoTv = findViewById(R.id.whatsapp);
        submitBtn = findViewById(R.id.btn_submit);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);
    }

    void readValue() {
        name = String.valueOf(nameTv.getText());
        aadhar = String.valueOf(aadharTv.getText());
        dob = String.valueOf(dobTv.getText());
        contactNo = String.valueOf(contactNoTv.getText());
        whatsappNo = String.valueOf(whatsappNoTv.getText());
    }

    boolean checkDataEntered() {
        boolean isAllRight = false;
        if (isEmpty(name)) {
            Toast.makeText(this, "Please fill name", Toast.LENGTH_LONG).show();
        } else if (isEmpty(aadhar)) {
            Toast.makeText(this, "Please fill addhar number", Toast.LENGTH_LONG).show();
        } else if (isEmpty(dob)) {
            Toast.makeText(this, "Please fill Date of Birth", Toast.LENGTH_LONG).show();
        } else if (isEmpty(contactNo)) {
            Toast.makeText(this, "Please fill contact No", Toast.LENGTH_LONG).show();
        } else if (isEmpty(whatsappNo)) {
            Toast.makeText(this, "Please fill whatsapp no", Toast.LENGTH_LONG).show();
        } else if (!isValidMobile(contactNo)) {
            Toast.makeText(this, "Please enter a valid Mobile No", Toast.LENGTH_LONG).show();
        } else if (!isValidMobile(whatsappNo)) {
            Toast.makeText(this, "Please enter a valid Whatsapp No", Toast.LENGTH_LONG).show();
        } else {
            isAllRight = true;
        }
        return isAllRight;
    }

    boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}
