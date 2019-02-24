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

    LinearLayout reccomendedWork, completedWork, nonProgressive, workDetail, statusWork, workRegister;

    public WMSReportFrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wms_report, container, false);
        reccomendedWork = v.findViewById(R.id.recomended_work);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
