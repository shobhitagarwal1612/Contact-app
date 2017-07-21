package android.com.kisannetwork.activities;

import android.com.kisannetwork.R;
import android.com.kisannetwork.model.Contact;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by shobhit on 21/7/17.
 */

public class ContactDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        getSupportActionBar().setTitle("Contact details");

        Contact contact = getIntent().getExtras().getParcelable("contact");

        TextView name = (TextView) findViewById(R.id.name);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView email = (TextView) findViewById(R.id.email);
        TextView address = (TextView) findViewById(R.id.address);
        TextView age = (TextView) findViewById(R.id.age);
        TextView company = (TextView) findViewById(R.id.company);

        name.setText(contact.getName());
        phone.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
        address.setText(contact.getAddress());
        age.setText(String.valueOf(contact.getAge()));
        company.setText(contact.getCompany());
    }
}
