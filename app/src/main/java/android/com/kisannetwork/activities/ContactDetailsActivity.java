package android.com.kisannetwork.activities;

import android.com.kisannetwork.R;
import android.com.kisannetwork.model.Contact;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shobhit on 21/7/17.
 */

public class ContactDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contact details");
        setSupportActionBar(toolbar);

        final Contact contact = getIntent().getExtras().getParcelable("contact");

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(contact.getName());

        TextView phone = (TextView) findViewById(R.id.phone);
        phone.setText(contact.getPhoneNumber());

        TextView email = (TextView) findViewById(R.id.email);
        email.setText(contact.getEmail());

        TextView address = (TextView) findViewById(R.id.address);
        address.setText(contact.getAddress());

        TextView age = (TextView) findViewById(R.id.age);
        age.setText(String.valueOf(contact.getAge()));

        TextView company = (TextView) findViewById(R.id.company);
        company.setText(contact.getCompany());

        LinearLayout sendMessage = (LinearLayout) findViewById(R.id.message);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDetailsActivity.this, SendMessageActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });
    }
}
