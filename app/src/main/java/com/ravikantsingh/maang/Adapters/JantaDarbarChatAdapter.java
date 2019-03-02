package com.ravikantsingh.maang.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ravikantsingh.maang.ModalClass.BaseMessage;
import com.ravikantsingh.maang.R;

import java.util.ArrayList;

public class JantaDarbarChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<BaseMessage> list;
    Context context;

    public JantaDarbarChatAdapter(ArrayList<BaseMessage> list, Context context) {
        this.list = list;
        this.context = context;
    }

    class ViewHolder0 extends RecyclerView.ViewHolder {

        TextView messageSent, messageSentTime;

        public ViewHolder0(View itemView) {
            super(itemView);
            messageSent = itemView.findViewById(R.id.text_message_body);
            messageSentTime = itemView.findViewById(R.id.text_message_time);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView messageRecieved, messageRecievedTime, messageSenderName;
        SimpleDraweeView messagerPhoto;

        public ViewHolder2(View itemView) {
            super(itemView);
            messageRecieved = itemView.findViewById(R.id.text_message_body);
            messageRecievedTime = itemView.findViewById(R.id.text_message_time);
            messageSenderName = itemView.findViewById(R.id.text_message_name);
            messagerPhoto = itemView.findViewById(R.id.image_message_profile);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 1: {
                holder = (new ViewHolder0(inflater.inflate(R.layout.item_message_sent, viewGroup, false)));
                break;
            }
            case 2: {
                holder = (new ViewHolder2(inflater.inflate(R.layout.item_message_received, viewGroup, false)));
                break;
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 1:
                ViewHolder0 viewHolder0 = (ViewHolder0) holder;
                viewHolder0.messageSent.setText(list.get(position).getMessage());
                viewHolder0.messageSentTime.setText(list.get(position).getTime());
                break;

            case 2:
                ViewHolder2 viewHolder2 = (ViewHolder2) holder;
                viewHolder2.messageSenderName.setText(list.get(position).getName());
                viewHolder2.messageRecievedTime.setText(list.get(position).getTime());
                viewHolder2.messageRecieved.setText(list.get(position).getMessage());
                break;
        }
    }
}


