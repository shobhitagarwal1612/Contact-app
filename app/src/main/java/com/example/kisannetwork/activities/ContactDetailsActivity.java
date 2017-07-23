package com.example.kisannetwork.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.kisannetwork.R;
import com.example.kisannetwork.model.Contact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shobhit on 21/7/17.
 */

public class ContactDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.company)
    TextView company;

    private Contact contact;

    @OnClick(R.id.message)
    public void sendOTP(View view) {
        // send message to the contact
        Intent intent = new Intent(ContactDetailsActivity.this, SendMessageActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.contact_details_title);
        setSupportActionBar(toolbar);

        // getting the extras from the intent
        contact = getIntent().getExtras().getParcelable("contact");

        updateUI();
    }

    private void updateUI() {
        name.setText(contact.getName());
        phone.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
        address.setText(contact.getAddress());
        age.setText(String.valueOf(contact.getAge()));
        company.setText(contact.getCompany());
    }
}
