package com.ravikantsingh.maang.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ravikantsingh.maang.R;

public class ForumsFrag extends Fragment {
    RelativeLayout intro,detailintro,purpose,purposedetail,selectioncommittee,detailofsc,guideline4applicant,
            guideline4nomminee,guideline4applicantdetail,guideline4nommineedeatail,aboutaward,aboutawarddetail;

    Button apply4competition,seeSubmissionbtn;
    public ForumsFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.frag_onemp_oneidea, container, false);
        intro = v.findViewById(R.id.introrelative);
        detailintro = v.findViewById(R.id.detailofintrorelative);
        purpose = v.findViewById(R.id.purpose1relative);
        purposedetail = v.findViewById(R.id.purposedetail1relative);
        selectioncommittee = v.findViewById(R.id.purposerelative);
        detailofsc = v.findViewById(R.id.purposedetailrelative);
        guideline4applicant = v.findViewById(R.id.guidelineforapplicantrelative);
        guideline4applicantdetail = v.findViewById(R.id.guidelineforapplicantdetailrelative);
        guideline4nomminee = v.findViewById(R.id.guidelinefornomineerelative);
        guideline4nommineedeatail = v.findViewById(R.id.guidelinefornomineedetailrelative);
        aboutaward = v.findViewById(R.id.aboutaward);
        aboutawarddetail = v.findViewById(R.id.aboutawarddetail);

        apply4competition = v.findViewById(R.id.applybutton);
        seeSubmissionbtn = v.findViewById(R.id.seesubmission);

        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                detailintro.setVisibility(View.VISIBLE);
                if(detailofsc.getVisibility()== View.VISIBLE || purposedetail.getVisibility() == View.VISIBLE || guideline4nommineedeatail.getVisibility() == View.VISIBLE
                        || guideline4applicantdetail.getVisibility() == View.VISIBLE || aboutawarddetail.getVisibility() == View.VISIBLE){
                    detailofsc.setVisibility(View.GONE);
                    detailintro.setVisibility(View.GONE);
                    guideline4nommineedeatail.setVisibility(View.GONE);
                    guideline4applicantdetail.setVisibility(View.GONE);
                    aboutawarddetail.setVisibility(View.GONE);

                }

            }
        });

        purpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                purposedetail.setVisibility(View.VISIBLE);

                if(detailofsc.getVisibility()== View.VISIBLE || detailintro.getVisibility() == View.VISIBLE || guideline4nommineedeatail.getVisibility() == View.VISIBLE
                        || guideline4applicantdetail.getVisibility() == View.VISIBLE ||  aboutawarddetail.getVisibility() == View.VISIBLE){
                    detailofsc.setVisibility(View.GONE);
                    detailintro.setVisibility(View.GONE);
                    guideline4nommineedeatail.setVisibility(View.GONE);
                    guideline4applicantdetail.setVisibility(View.GONE);
                    aboutawarddetail.setVisibility(View.GONE);

                }

            }
        });

        selectioncommittee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                detailofsc.setVisibility(View.VISIBLE);

                if(purposedetail.getVisibility()== View.VISIBLE || detailintro.getVisibility() == View.VISIBLE || guideline4nommineedeatail.getVisibility() == View.VISIBLE
                        || guideline4applicantdetail.getVisibility() == View.VISIBLE||  aboutawarddetail.getVisibility() == View.VISIBLE){
                    purposedetail.setVisibility(View.GONE);
                    detailintro.setVisibility(View.GONE);
                    guideline4nommineedeatail.setVisibility(View.GONE);
                    guideline4applicantdetail.setVisibility(View.GONE);
                    aboutawarddetail.setVisibility(View.GONE);

                }

            }
        });

        guideline4applicant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guideline4applicantdetail.setVisibility(View.VISIBLE);
                if (detailintro.getVisibility()== View.VISIBLE || detailofsc.getVisibility() == View.VISIBLE || purposedetail.getVisibility()==View.VISIBLE
                        || guideline4nommineedeatail.getVisibility()==View.VISIBLE ||  aboutawarddetail.getVisibility() == View.VISIBLE){

                    purposedetail.setVisibility(View.GONE);
                    detailintro.setVisibility(View.GONE);
                    guideline4nommineedeatail.setVisibility(View.GONE);
                    detailofsc.setVisibility(View.GONE);
                    aboutawarddetail.setVisibility(View.GONE);

                }

            }
        });

        guideline4nomminee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guideline4nommineedeatail.setVisibility(View.VISIBLE);
                if (detailintro.getVisibility()== View.VISIBLE || detailofsc.getVisibility() == View.VISIBLE || purposedetail.getVisibility()==View.VISIBLE
                        || guideline4applicantdetail.getVisibility()==View.VISIBLE ||  aboutawarddetail.getVisibility() == View.VISIBLE){

                    purposedetail.setVisibility(View.GONE);
                    detailintro.setVisibility(View.GONE);
                    guideline4applicantdetail.setVisibility(View.GONE);
                    detailofsc.setVisibility(View.GONE);
                    aboutawarddetail.setVisibility(View.GONE);

                }

            }
        });

        aboutaward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aboutawarddetail.setVisibility(View.VISIBLE);
                if (detailintro.getVisibility()== View.VISIBLE || detailofsc.getVisibility() == View.VISIBLE || purposedetail.getVisibility()==View.VISIBLE
                        || guideline4applicantdetail.getVisibility()==View.VISIBLE ||  guideline4nommineedeatail.getVisibility() == View.VISIBLE){

                    purposedetail.setVisibility(View.GONE);
                    detailintro.setVisibility(View.GONE);
                    guideline4applicantdetail.setVisibility(View.GONE);
                    detailofsc.setVisibility(View.GONE);
                    guideline4nommineedeatail.setVisibility(View.GONE);

                }

            }
        });

        apply4competition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent applyintent = new Intent(getContext(),ApplyForCompetition.class);
                getContext().startActivity(applyintent);
            }
        });

        seeSubmissionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seeintent = new Intent(getContext(),SeeSubmission.class);
                getContext().startActivity(seeintent);
            }
        });

        return v;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

}