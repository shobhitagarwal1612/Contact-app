package com.example.kisannetwork.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kisannetwork.R;
import com.example.kisannetwork.activities.ContactDetailsActivity;
import com.example.kisannetwork.adapters.ContactsAdapter;
import com.example.kisannetwork.listeners.ClickListener;
import com.example.kisannetwork.model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by shobhit on 21/7/17.
 */

public class ContactsFragment extends Fragment implements ClickListener {

    ArrayList<Contact> contacts;

    public ContactsFragment() {
    }

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        loadData();

        ContactsAdapter adapter = new ContactsAdapter(getContext(), contacts, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

    private void loadData() {
        try {
            contacts = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());

            Contact contact;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                contact = new Contact();
                contact.setAddress(object.getString("address"));
                contact.setPhoneNumber(object.getString("phone"));
                contact.setEmail(object.getString("email"));
                contact.setCompany(object.getString("company"));
                contact.setFirstName(object.getJSONObject("name").getString("first"));
                contact.setLastName(object.getJSONObject("name").getString("last"));
                contact.setAge(object.getInt("age"));
                contact.setImage(object.getString("picture"));
                contact.setIndex(object.getInt("index"));

                contacts.add(contact);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getActivity().getAssets().open("contacts.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onClick(int position) {
        Contact contact = contacts.get(position);
        Intent intent = new Intent(getActivity(), ContactDetailsActivity.class);
        intent.putExtra("contact", contact);
        getActivity().startActivity(intent);
    }
}
