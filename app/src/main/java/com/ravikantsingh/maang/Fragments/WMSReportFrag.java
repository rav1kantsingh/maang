package com.ravikantsingh.maang.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.ravikantsingh.maang.R;
import com.ravikantsingh.maang.WMSinnerActivity.CompletedWorkActivity;
import com.ravikantsingh.maang.WMSinnerActivity.NonProgressiveWorkActivity;
import com.ravikantsingh.maang.WMSinnerActivity.RecommendedWork;
import com.ravikantsingh.maang.WMSinnerActivity.StatusWorkActivity;
import com.ravikantsingh.maang.WMSinnerActivity.WorkDetailActivity;
import com.ravikantsingh.maang.WMSinnerActivity.WorkRegisterActivity;

public class WMSReportFrag extends Fragment {

    LinearLayout reccomendedWork, completedWork, nonProgressive, workDetail, statusWork, workRegister;
    Button fundReleaseStatement;

    public WMSReportFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wms_report, container, false);
        reccomendedWork = v.findViewById(R.id.recomended_work);
        completedWork = v.findViewById(R.id.completed_work);
        nonProgressive = v.findViewById(R.id.progressive_work);
        workDetail = v.findViewById(R.id.work_detail);
        statusWork = v.findViewById(R.id.status_work);
        workRegister = v.findViewById(R.id.register_work);
        fundReleaseStatement = v.findViewById(R.id.fund_release_statement);

        reccomendedWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), RecommendedWork.class));
            }
        });

        completedWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), CompletedWorkActivity.class));
            }
        });

        nonProgressive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), NonProgressiveWorkActivity.class));
            }
        });

        workDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), WorkDetailActivity.class));
            }
        });

        statusWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), StatusWorkActivity.class));
            }
        });

        workRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), WorkRegisterActivity.class));
            }
        });

        fundReleaseStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return v;
    }

}
