package com.ravikantsingh.maang.Registration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ravikantsingh.maang.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText nameTv, emailTv, passwordTv, confirm_passwordTv, addharTv, dobTv, contactNoTv, whatsappNoTv;
    String name = "", email = "", password = "", confirm_password = "", addhar, dob = "", contactNo = "", whatsappNo = "", gender = "";
    Button submitBtn, male, female, other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registration);

        init();

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
                readValue();
                checkDataEntered();
            }
        });

    }

    void init() {
        nameTv = findViewById(R.id.name);
        emailTv = findViewById(R.id.email);
        passwordTv = findViewById(R.id.password);
        confirm_passwordTv = findViewById(R.id.conf_password);
        addharTv = findViewById(R.id.aadhar);
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
        email = String.valueOf(emailTv.getText());
        password = String.valueOf(passwordTv.getText());
        confirm_password = String.valueOf(confirm_passwordTv.getText());
        addhar = String.valueOf(addharTv.getText());
        dob = String.valueOf(dobTv.getText());
        contactNo = String.valueOf(contactNoTv.getText());
        whatsappNo = String.valueOf(whatsappNoTv.getText());
    }

    void checkDataEntered() {
        if (isEmpty(name)) {
            Toast.makeText(this, "Please fill name", Toast.LENGTH_LONG).show();
        } else if (isEmpty(email)) {
            Toast.makeText(this, "Please fill email", Toast.LENGTH_LONG).show();
        } else if (isEmpty(password)) {
            Toast.makeText(this, "Please fill password", Toast.LENGTH_LONG).show();
        } else if (isEmpty(confirm_password)) {
            Toast.makeText(this, "Please fill confirm password", Toast.LENGTH_LONG).show();
        } else if (isEmpty(addhar)) {
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
        }
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
