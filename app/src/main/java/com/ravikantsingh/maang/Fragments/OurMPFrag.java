package com.ravikantsingh.maang.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.ravikantsingh.maang.OurMpPostActivity;
import com.ravikantsingh.maang.OurMpSuggestionActivity;
import com.ravikantsingh.maang.R;

public class OurMPFrag extends Fragment implements View.OnClickListener {

    public OurMPFrag() {
    }

    LinearLayout mpPost, mpSuggestion,mFundreleased,mExpenditure,mWorkrecommended,mWorkcompleted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_our_mp, container, false);
        mpPost = v.findViewById(R.id.our_mp_posts);
        mpSuggestion = v.findViewById(R.id.our_mp_suggestion);
        mFundreleased = v.findViewById(R.id.fundreleased);
        mExpenditure = v.findViewById(R.id.expenditure);
        mWorkrecommended = v.findViewById(R.id.workrecommended);
        mWorkcompleted = v.findViewById(R.id.workcompleted);
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
            case R.id.fundreleased:
                fundreleasedialog();
                break;
            case R.id.expenditure:
                expendituredialog();
                break;
            case R.id.workrecommended:
                workrecomendeddialog();
                break;
            case R.id.workcompleted:
                workcompleateddialog();
                break;
        }
    }

    private void fundreleasedialog() {
        final Dialog mDialog = new Dialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(true);
        mDialog.setContentView(R.layout.activity_fund_release);
        mDialog.show();
    }

    private void expendituredialog() {
        final Dialog mDialog = new Dialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(true);
        mDialog.setContentView(R.layout.pop_up_expenditure);
        mDialog.show();
    }



    private void workrecomendeddialog() {
        final Dialog mDialog = new Dialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(true);
        mDialog.setContentView(R.layout.pop_up_recommended);
        mDialog.show();
    }

    private void workcompleateddialog() {
        final Dialog mDialog = new Dialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(true);
        mDialog.setContentView(R.layout.activity_fund_release);
        mDialog.show();
    }
}
