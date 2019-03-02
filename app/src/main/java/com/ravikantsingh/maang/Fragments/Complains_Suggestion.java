package com.ravikantsingh.maang.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ravikantsingh.maang.AddComplainActivity;
import com.ravikantsingh.maang.AddSuggestionActivity;
import com.ravikantsingh.maang.ComplaintActivity;
import com.ravikantsingh.maang.R;
import com.ravikantsingh.maang.SuggestionActivity;

public class Complains_Suggestion extends Fragment {

    Button giveYourSuggestion, seeOtherSuggestion, addYourComplaint, seeOtherComplains;

    public Complains_Suggestion() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.complains_suggestions, container, false);

        giveYourSuggestion = v.findViewById(R.id.give_your_suggestion);
        seeOtherSuggestion = v.findViewById(R.id.see_other_suggestion);
        seeOtherComplains = v.findViewById(R.id.see_other_complains);
        addYourComplaint = v.findViewById(R.id.add_your_complaint);


        giveYourSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getContext(), AddSuggestionActivity.class));
            }
        });

        seeOtherSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SuggestionActivity.class));
            }
        });


        seeOtherComplains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ComplaintActivity.class));
            }
        });

        addYourComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddComplainActivity.class));
            }
        });

        return v;

    }
}


