package com.example.kisannetwork.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kisannetwork.R;
import com.example.kisannetwork.model.MessageHistory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shobhit on 21/7/17.
 */

// Create the basic adapter extending from RecyclerView.Adapter for message history recycler view
public class MessagesAdapter extends
        RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private ArrayList<MessageHistory> arrayList;

    public MessagesAdapter(ArrayList<MessageHistory> arrayList) {
        this.arrayList = arrayList;
    }

    public void setList(ArrayList<MessageHistory> list) {
        arrayList = list;
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_message_history, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MessagesAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        MessageHistory messageHistory = arrayList.get(position);

        // Set item views based on your views and data model
        viewHolder.name.setText(messageHistory.getName());
        viewHolder.otp.setText(messageHistory.getOtp());
        viewHolder.timeStamp.setText(messageHistory.getTimeStamp());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.otp)
        TextView otp;
        @BindView(R.id.time)
        TextView timeStamp;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}