package android.com.kisannetwork.fragments;

import android.com.kisannetwork.R;
import android.com.kisannetwork.activities.ContactDetailsActivity;
import android.com.kisannetwork.adapters.MessagesAdapter;
import android.com.kisannetwork.database.MessagesHistory;
import android.com.kisannetwork.listeners.ClickListener;
import android.com.kisannetwork.model.Contact;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shobhit on 21/7/17.
 */

public class SentSMSFragment extends Fragment implements ClickListener {

    ArrayList<Contact> contacts;

    public SentSMSFragment() {

    }

    public static SentSMSFragment newInstance() {
        return new SentSMSFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        loadData();

        loadDataFromDb();

        MessagesAdapter adapter = new MessagesAdapter(getContext(), contacts, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

    private void loadDataFromDb() {
        MessagesHistory.MessagesDbHelper dbHelper = new MessagesHistory.MessagesDbHelper(getContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                MessagesHistory.MessageEntry._ID,
                MessagesHistory.MessageEntry.COLUMN_NAME_CONTACT_NAME,
                MessagesHistory.MessageEntry.COLUMN_NAME_OTP,
                MessagesHistory.MessageEntry.COLUMN_NAME_TIME
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                MessagesHistory.MessageEntry.COLUMN_NAME_TIME + " DESC";

        Cursor cursor = db.query(
                MessagesHistory.MessageEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MessagesHistory.MessageEntry._ID));
            itemIds.add(itemId);
        }
        cursor.close();

        Toast.makeText(getContext(), itemIds.size() + "", Toast.LENGTH_SHORT).show();
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
