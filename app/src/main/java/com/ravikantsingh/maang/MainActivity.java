package com.ravikantsingh.maang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ravikantsingh.maang.Authentication.LoginActivity;
import com.ravikantsingh.maang.Fragments.FeedsFrag;
import com.ravikantsingh.maang.Fragments.ForumsFrag;
import com.ravikantsingh.maang.Fragments.Complains_Suggestion;
import com.ravikantsingh.maang.Fragments.OurMPFrag;
import com.ravikantsingh.maang.Fragments.WMSReportFrag;
import com.ravikantsingh.maang.NavDrawer.AboutActivity;
import com.ravikantsingh.maang.NavDrawer.FeedBackActivity;
import com.ravikantsingh.maang.NavDrawer.JantaDarbarActivity;
import com.ravikantsingh.maang.NavDrawer.RTI_Activity;
import com.ravikantsingh.maang.NavDrawer.SettingActivity;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FeedsFrag feedsFrag;
    ForumsFrag forumsFrag;
    Complains_Suggestion myProfileFrag;
    OurMPFrag ourMPFrag;
    WMSReportFrag wmsReportFrag;
    FragmentManager fm;
    Fragment currentFragment;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth auth;

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

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(false);
                        drawerLayout.closeDrawers();
                        int id = menuItem.getItemId();
                        if (id == R.id.a) {
                            startActivity(new Intent(MainActivity.this, JantaDarbarActivity.class));
                        } else if (id == R.id.b) {
                            startActivity(new Intent(MainActivity.this, RTI_Activity.class));
                        } else if (id == R.id.c) {
                            startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        } else if (id == R.id.d) {
                            startActivity(new Intent(MainActivity.this, FeedBackActivity.class));
                        } else if (id == R.id.e) {
                            startActivity(new Intent(MainActivity.this, SettingActivity.class));
                        } else if (id == R.id.f) {
                           signOut();
                        }
                        return true;
                    }
                });
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionbar = getSupportActionBar();
//        assert actionbar != null;
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
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
            feedsFrag = new FeedsFrag();
            fm.beginTransaction().add(R.id.frame_container, feedsFrag).hide(currentFragment).show(feedsFrag).commit();
        } else {
            fm.beginTransaction().hide(currentFragment).show(feedsFrag).commit();
        }
        currentFragment = feedsFrag;
    }

    public void switchToFragment2() {
        if (forumsFrag == null) {
            forumsFrag = new ForumsFrag();
            fm.beginTransaction().add(R.id.frame_container, forumsFrag).hide(currentFragment).show(forumsFrag).commit();
        } else {
            fm.beginTransaction().hide(currentFragment).show(forumsFrag).commit();
        }
        currentFragment = forumsFrag;
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
        if (myProfileFrag == null) {
            myProfileFrag = new Complains_Suggestion();
            fm.beginTransaction().add(R.id.frame_container, myProfileFrag).hide(currentFragment).show(myProfileFrag).commit();
        } else {
            fm.beginTransaction().hide(currentFragment).show(myProfileFrag).commit();
        }
        currentFragment = myProfileFrag;
    }

    private void signOut() {
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = auth.getCurrentUser();
        if(currentuser!=null){
            auth.signOut();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
