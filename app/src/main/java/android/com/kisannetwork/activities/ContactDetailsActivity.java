package android.com.kisannetwork.activities;

import android.com.kisannetwork.R;
import android.com.kisannetwork.model.Contact;
import android.content.Intent;
import android.net.Uri;
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

        Contact contact = getIntent().getExtras().getParcelable("contact");

        TextView name = (TextView) findViewById(R.id.name);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView email = (TextView) findViewById(R.id.email);
        TextView address = (TextView) findViewById(R.id.address);
        TextView age = (TextView) findViewById(R.id.age);
        TextView company = (TextView) findViewById(R.id.company);
        LinearLayout message = (LinearLayout) findViewById(R.id.message);

        name.setText(contact.getName());
        phone.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
        address.setText(contact.getAddress());
        age.setText(String.valueOf(contact.getAge()));
        company.setText(contact.getCompany());

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW");
                Uri data = Uri.parse("sms:");
                intent.setData(data);
                startActivity(intent);
            }
        });
    }
}
