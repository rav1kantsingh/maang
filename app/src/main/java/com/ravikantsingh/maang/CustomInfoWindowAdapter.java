package com.ravikantsingh.maang;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Ravikant Singh on 03,March,2019
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;
    TextView tvTitle,tvrating,suggest;

    public CustomInfoWindowAdapter(Context context) {
        mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void rendowWindowText(Marker marker, View view){
        tvTitle =  view.findViewById(R.id.title);

        final String title = marker.getTitle();
        String snippet = marker.getSnippet();

        if(!title.equals("")){
            tvTitle.setText(title);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("suggest---","clicked");
                 Intent intent = new Intent(mContext,AddComplainActivity.class);
                 intent.putExtra("Title",title);
////                intent.putExtra("lat",.latitude);
////                intent.putExtra("lng",latLng.longitude);
//                mContext.startActivity(intent);
            }
        });

    }



    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }
}
