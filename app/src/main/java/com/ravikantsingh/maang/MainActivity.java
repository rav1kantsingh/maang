package com.ravikantsingh.maang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ravikantsingh.maang.Authentication.LoginActivity;
import com.ravikantsingh.maang.Fragments.Complains_Suggestion;
import com.ravikantsingh.maang.Fragments.FeedsFrag;
import com.ravikantsingh.maang.Fragments.ForumsFrag;
import com.ravikantsingh.maang.Fragments.OurMPFrag;
import com.ravikantsingh.maang.Fragments.WMSReportFrag;
import com.ravikantsingh.maang.MP.MPJantaDarbar;
import com.ravikantsingh.maang.NavDrawer.AboutActivity;
import com.ravikantsingh.maang.NavDrawer.ProfileActivity;
import com.ravikantsingh.maang.NavDrawer.RTI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    PostFragment feedsFrag;
    ForumsFrag forumsFrag;
    Complains_Suggestion myProfileFrag;
    OurMPFrag ourMPFrag;
    WMSReportFrag wmsReportFrag;
    FragmentManager fm;
    Fragment currentFragment;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth auth;
    View headerview;
    TextView drawerHeader1, drawerHeader2, drawerHeader3, name, zone;
    ImageView drawerProfileImage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.first:
                    switchToFragment1();
                    return true;
                case R.id.second:
                    switchToFragment2();
                    return true;
                case R.id.third:
                    switchToFragment3();
                    return true;
                case R.id.fourth:
                    switchToFragment4();
                    return true;
                case R.id.fifth:
                    switchToFragment5();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    void init() {
        drawerLayout = findViewById(R.id.drawer_layout);
        fm = getSupportFragmentManager();
        FeedsFrag feedsFrag = new FeedsFrag();
        fm.beginTransaction().add(R.id.frame_container, feedsFrag).commit();
        currentFragment = feedsFrag;
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SharedPreferences preferences = getSharedPreferences(StringVariables.SHARED_PREFERENCE_FILE,MODE_PRIVATE);
        String name_1 = preferences.getString("name","");
        String zone_1 = preferences.getString("zone","");

        navigation.setSelectedItemId(R.id.first);
        navigationView = findViewById(R.id.nav_view);

        headerview = navigationView.getHeaderView(0);
        drawerHeader1 = headerview.findViewById(R.id.first);
        drawerHeader2 = headerview.findViewById(R.id.second);
        drawerHeader3 = headerview.findViewById(R.id.third);

        name = headerview.findViewById(R.id.name);
        zone = headerview.findViewById(R.id.zone);
        drawerProfileImage = headerview.findViewById(R.id.profile);

        name.setText(name_1);
        zone.setText(zone_1);

        drawerHeader1.setOnClickListener(this);
        drawerHeader2.setOnClickListener(this);
        drawerHeader3.setOnClickListener(this);


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(false);
                        drawerLayout.closeDrawers();
                        int id = menuItem.getItemId();
                        if (id == R.id.a) {
                            startActivity(new Intent(MainActivity.this, MPJantaDarbar.class));
                        } else if (id == R.id.b) {
                            startActivity(new Intent(MainActivity.this, RTI.class));
                        } else if (id == R.id.c) {
                            startActivity(new Intent(MainActivity.this, PdfViewerActivity.class));
                        } else if (id == R.id.d) {
                            startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        } else if (id == R.id.g) {
                            signOut();
                        }else if (id == R.id.h) {
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        }else if(id == R.id.l){
                            startActivity(new Intent(MainActivity.this, ChatbotActivity.class));
                        }
                        else if(id == R.id.k){
                            startActivity(new Intent(MainActivity.this, KnowYourContituency.class));
                        }

                        return true;
                    }
                });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void switchToFragment1() {
        if (feedsFrag == null) {
            feedsFrag = new PostFragment();
            fm.beginTransaction().add(R.id.frame_container, feedsFrag).hide(currentFragment).show(feedsFrag).commit();
        } else {
            fm.beginTransaction().hide(currentFragment).show(feedsFrag).commit();
        }
        currentFragment = feedsFrag;
    }

    public void switchToFragment2() {
        if (myProfileFrag == null) {
            myProfileFrag = new Complains_Suggestion();
            fm.beginTransaction().add(R.id.frame_container, myProfileFrag).hide(currentFragment).show(myProfileFrag).commit();
        } else {
            fm.beginTransaction().hide(currentFragment).show(myProfileFrag).commit();
        }
        currentFragment = myProfileFrag;
    }

    public void switchToFragment3() {
        if (wmsReportFrag == null) {
            wmsReportFrag = new WMSReportFrag();
            fm.beginTransaction().add(R.id.frame_container, wmsReportFrag).hide(currentFragment).show(wmsReportFrag).commit();
        } else {
            fm.beginTransaction().hide(currentFragment).show(wmsReportFrag).commit();
        }
        currentFragment = wmsReportFrag;
    }

    public void switchToFragment4() {
        if (ourMPFrag == null) {
            ourMPFrag = new OurMPFrag();
            fm.beginTransaction().add(R.id.frame_container, ourMPFrag).hide(currentFragment).show(ourMPFrag).commit();
        } else {
            fm.beginTransaction().hide(currentFragment).show(ourMPFrag).commit();
        }
        currentFragment = ourMPFrag;
    }

    public void switchToFragment5() {
        if (forumsFrag == null) {
            forumsFrag = new ForumsFrag();
            fm.beginTransaction().add(R.id.frame_container, forumsFrag).hide(currentFragment).show(forumsFrag).commit();
        } else {
            fm.beginTransaction().hide(currentFragment).show(forumsFrag).commit();
        }
        currentFragment = forumsFrag;
    }

    private void signOut() {
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = auth.getCurrentUser();
        if (currentuser != null) {
            auth.signOut();
            SharedPreferences preferences_login = getSharedPreferences("UserLoggedIn", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = preferences_login.edit();
            editor1.putInt("UserLoggedIn", 0);
            editor1.apply();

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first: {
                startActivity(new Intent(this,MyPost.class));
                break;
            }
            case R.id.second: {
                startActivity(new Intent(this,MySuggestion.class));
                break;
            }
            case R.id.third: {
                startActivity(new Intent(this,MyComplain.class));
                break;
            }
        }
    }
}

