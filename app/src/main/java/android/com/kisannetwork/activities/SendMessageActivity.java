package android.com.kisannetwork.activities;

import android.com.kisannetwork.R;
import android.com.kisannetwork.model.Contact;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by shobhit on 22/7/17.
 */

public class SendMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        final Contact contact = getIntent().getExtras().getParcelable("contact");

        EditText message = (EditText) findViewById(R.id.message);
        Button sendButton = (Button) findViewById(R.id.button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Send Message to " + contact.getName());
        setSupportActionBar(toolbar);

        message.setText("Hi. Your OTP is:\n" + getRandomSixDigits());
    }

    private String getRandomSixDigits() {
        int random = (int) (100000 + Math.random() * 900000);
        return String.valueOf(random);
    }
}
