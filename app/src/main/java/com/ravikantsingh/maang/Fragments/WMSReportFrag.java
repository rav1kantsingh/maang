package com.ravikantsingh.maang.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ravikantsingh.maang.R;

public class WMSReportFrag extends Fragment {

    LinearLayout reccomendedWork, completedWork, nonProgressive, workDetail, statusWork, workRegister, fundReleaseStatement;

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

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reccomendedWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        completedWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        nonProgressive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        workDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        statusWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        workRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fundReleaseStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
