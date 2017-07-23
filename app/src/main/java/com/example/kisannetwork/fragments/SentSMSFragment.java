package com.example.kisannetwork.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kisannetwork.R;
import com.example.kisannetwork.adapters.MessagesAdapter;
import com.example.kisannetwork.database.DbUtils;
import com.example.kisannetwork.database.MessagesHistory;
import com.example.kisannetwork.listeners.DataUpdated;
import com.example.kisannetwork.model.MessageHistory;

import java.util.ArrayList;

/**
 * Created by shobhit on 21/7/17.
 */

public class SentSMSFragment extends Fragment implements DataUpdated {

    ArrayList<MessageHistory> messageList;
    private RecyclerView recyclerView;
    private MessagesAdapter adapter;

    public SentSMSFragment() {

    }

    public static SentSMSFragment newInstance() {
        return new SentSMSFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MessagesAdapter(getContext(), messageList);
        loadDataFromDb(getContext());
        return rootView;
    }

    private void loadDataFromDb(Context context) {
        messageList = new ArrayList<>();

        Cursor cursor = DbUtils.getMessagesHistoryCursor(context);
        try {
            while (cursor.moveToNext()) {

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(MessagesHistory.MessageEntry.COLUMN_NAME_CONTACT_NAME));
                String otp = cursor.getString(
                        cursor.getColumnIndexOrThrow(MessagesHistory.MessageEntry.COLUMN_NAME_OTP));
                String timeStamp = cursor.getString(
                        cursor.getColumnIndexOrThrow(MessagesHistory.MessageEntry.COLUMN_NAME_TIME));

                MessageHistory messageHistory = new MessageHistory();
                messageHistory.setName(name);
                messageHistory.setOtp("OTP: " + otp);
                messageHistory.setTimeStamp("Sent at: " + timeStamp);

                messageList.add(messageHistory);
            }

            adapter.setList(messageList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            if (!cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    @Override
    public void update(Context baseContext) {
        loadDataFromDb(baseContext);
    }
}