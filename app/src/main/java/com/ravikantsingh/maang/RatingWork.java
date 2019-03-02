package com.ravikantsingh.maang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingWork extends AppCompatActivity {

    RatingBar rb;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_work);

        rb = (RatingBar) findViewById(R.id.rating_bar);
        tv = (TextView) findViewById(R.id.text_view2);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                tv.setText("" + v);

            }
        });

    }
}
