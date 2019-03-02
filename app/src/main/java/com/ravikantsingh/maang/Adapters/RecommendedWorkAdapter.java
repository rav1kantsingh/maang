package com.ravikantsingh.maang.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ravikantsingh.maang.AddComplainActivity;
import com.ravikantsingh.maang.AddSuggestionActivity;
import com.ravikantsingh.maang.ModalClass.WMSModelClass;
import com.ravikantsingh.maang.R;

import java.util.List;

public class RecommendedWorkAdapter extends
        RecyclerView.Adapter<RecommendedWorkAdapter.ViewHolder> implements View.OnClickListener {

    List<WMSModelClass> list;
    Context context;

    public RecommendedWorkAdapter(Context context, List<WMSModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(inflater.inflate(R.layout.element_work_detail, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WMSModelClass object = list.get(i);

        viewHolder.sector.setText(object.getSector());
        viewHolder.scheme.setText(object.getScheme());
        viewHolder.implimentingAgencies.setText(object.getImplementingAgency());
        viewHolder.recommendDate.setText(object.getRecommendationDate());
        viewHolder.amount.setText(object.getAmount());
        viewHolder.complainBtn.setOnClickListener(this);
        viewHolder.suggestionBtn.setOnClickListener(this);
        viewHolder.like.setOnClickListener(this);
        viewHolder.rate.setOnClickListener(this);
        viewHolder.comments.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.complain_btn: {
                context.startActivity(new Intent(context, AddComplainActivity.class));
                break;
            }
            case R.id.suggestion_btn: {
                context.startActivity(new Intent(context, AddSuggestionActivity.class));
                break;
            }
            case R.id.like: {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                break;
            }
<<<<<<< HEAD
            case R.id.share: {
                showDialog();
                break;
=======
            case R.id.rate: {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("RATE YOUR MP(On scale of 5)");
                alertDialog.setMessage("Enter Password");
                final EditText input = new EditText(context);
                alertDialog.setView(input);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(context,"Rated", Toast.LENGTH_SHORT).show();
                            }
                        });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
>>>>>>> ec3b3a52bf4ba265b533a2260d3a15627c26c216
            }
            case R.id.comments: {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

    private void showDialog() {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView sector, scheme, implimentingAgencies, recommendDate, amount, like, rate, comments;
        Button complainBtn, suggestionBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sector = itemView.findViewById(R.id.sector);
            scheme = itemView.findViewById(R.id.scheme);
            implimentingAgencies = itemView.findViewById(R.id.implimentingAgencies);
            recommendDate = itemView.findViewById(R.id.recommend_date);
            amount = itemView.findViewById(R.id.amount);
            like = itemView.findViewById(R.id.like);
            rate = itemView.findViewById(R.id.rate);
            comments = itemView.findViewById(R.id.comments);
            complainBtn = itemView.findViewById(R.id.complain_btn);
            suggestionBtn = itemView.findViewById(R.id.suggestion_btn);


        }
    }
}