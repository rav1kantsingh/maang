package com.ravikantsingh.maang.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ravikantsingh.maang.JantaUserActivity;
import com.ravikantsingh.maang.R;

import java.util.List;

public class JantaDateAdapter extends
        RecyclerView.Adapter<JantaDateAdapter.ViewHolder> implements View.OnClickListener {

    List<String> list;
    Context context;

    public JantaDateAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(inflater.inflate(R.layout.janta_date_element, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.date.setText(list.get(i));
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = list.get(i);
                Intent intent = new Intent(context, JantaUserActivity.class);
                Log.e("intent-pass",list.get(i));
                intent.putExtra("date",date);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView date;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            card = itemView.findViewById(R.id.card);
        }
    }
}