package com.ravikantsingh.maang.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ravikantsingh.maang.OurMpPostActivity;
import com.ravikantsingh.maang.OurMpSuggestionActivity;
import com.ravikantsingh.maang.R;

public class OurMPFrag extends Fragment implements View.OnClickListener {

    public OurMPFrag() {
    }

    LinearLayout mpPost, mpSuggestion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_our_mp, container, false);
        mpPost = v.findViewById(R.id.our_mp_posts);
        mpSuggestion = v.findViewById(R.id.our_mp_suggestion);
        mpPost.setOnClickListener(this);
        mpSuggestion.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.our_mp_posts:{
                startActivity(new Intent(getContext(), OurMpPostActivity.class));
                break;
            }
            case R.id.our_mp_suggestion:{
                startActivity(new Intent(getContext(), OurMpSuggestionActivity.class));
                break;
            }
        }
    }
}
