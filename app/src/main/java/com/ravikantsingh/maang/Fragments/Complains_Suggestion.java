package com.ravikantsingh.maang.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ravikantsingh.maang.R;

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

            }
        });

        seeOtherSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        seeOtherComplains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addYourComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;

    }
}


