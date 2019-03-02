package com.ravikantsingh.maang.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ravikantsingh.maang.MainActivity;
import com.ravikantsingh.maang.R;
import com.ravikantsingh.maang.Registration.RegistrationActivity;
import com.ravikantsingh.maang.StringVariables;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity.java";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;
    ConstraintLayout googleSignupButton;
    private FirebaseAuth mAuth;
    private ProgressDialog signInDialog;
    DatabaseReference databaseReference;
    String userUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        init();
        initAuth();
    }

    public void init() {
        googleSignupButton = findViewById(R.id.signup_button);
        signInDialog = new ProgressDialog(this);

        googleSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                mGoogleApiClient.clearDefaultAccountAndReconnect();
            }
        });
    }

    public void initAuth() {
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        ;
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        signInDialog.setMessage("Please Wait");
        signInDialog.show();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");

                            try {
                                userUID = mAuth.getCurrentUser().getUid();
                            } catch (Exception e) {
                            }

                            SharedPreferences preferences = getSharedPreferences("UserLoggedIn", MODE_PRIVATE);
                            final SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("UserLoggedIn", 1);
                            editor.apply();

                            databaseReference = FirebaseDatabase.getInstance().getReference().child(StringVariables.USERS);
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (String.valueOf(dataSnapshot.child(userUID).getValue()).equals("") || String.valueOf(dataSnapshot.child(userUID).getValue()).equals("null")) {
                                        Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    } else {

                                        SharedPreferences preferences1 = getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE, MODE_PRIVATE);
                                        SharedPreferences.Editor editor1 = preferences1.edit();
                                        String name = String.valueOf(dataSnapshot.child(userUID).child("name")),
                                                aadhar = String.valueOf(dataSnapshot.child(userUID).child("aadhar")),
                                                dob = String.valueOf(dataSnapshot.child(userUID).child("DOB")),
                                                gender = String.valueOf(dataSnapshot.child(userUID).child("gender")),
                                                contactNo = String.valueOf(dataSnapshot.child(userUID).child("phoneNo")),
                                                whatsappNo = String.valueOf(dataSnapshot.child(userUID).child("whatsapp"));

                                        editor1.putString("name", name);
                                        editor1.putString("aadhar", aadhar);
                                        editor1.putString("DOB", dob);
                                        editor1.putString("gender", gender);
                                        editor1.putString("phoneNo", contactNo);
                                        editor1.putString("whatsapp", whatsappNo);
                                        editor1.putString("userUID",userUID);
                                        editor1.putInt("registered", 1);
                                        editor.apply();
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication Failed.", Toast.LENGTH_LONG).show();
                        }

                        if (signInDialog.isShowing()) {
                            signInDialog.dismiss();
                        }
                    }
                });
    }
}
