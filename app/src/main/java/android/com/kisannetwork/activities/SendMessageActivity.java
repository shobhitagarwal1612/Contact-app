package android.com.kisannetwork.activities;

import android.com.kisannetwork.R;
import android.com.kisannetwork.database.MessagesHistory;
import android.com.kisannetwork.model.Contact;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        toolbar.setTitle("Send OTP: " + contact.getName());
        setSupportActionBar(toolbar);

        final String randomNumber = getRandomSixDigits();

        message.setText("Hi. Your OTP is: " + randomNumber);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessagesHistory.MessagesDbHelper dbHelper = new MessagesHistory.MessagesDbHelper(getBaseContext());

                // Gets the data repository in write mode
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(MessagesHistory.MessageEntry.COLUMN_NAME_CONTACT_NAME, contact.getName());
                values.put(MessagesHistory.MessageEntry.COLUMN_NAME_OTP, randomNumber);

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(MessagesHistory.MessageEntry.TABLE_NAME, null, values);

                if (newRowId != -1) {
                    Toast.makeText(getBaseContext(), "Message saved to history", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getRandomSixDigits() {
        int random = (int) (100000 + Math.random() * 900000);
        return String.valueOf(random);
    }
}
