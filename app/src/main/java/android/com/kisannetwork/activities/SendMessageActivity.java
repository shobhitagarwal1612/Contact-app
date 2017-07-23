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
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shobhit on 22/7/17.
 */

public class SendMessageActivity extends AppCompatActivity {

    private static final String ACCOUNT_SID = "ACe0b238b269adc92973d35be7397373c3";
    private static final String AUTH_TOKEN = "5bfefc3693e495edde3019892cc00fc2";
    OkHttpClient client = new OkHttpClient();
    private EditText message;
    private Contact contact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        contact = getIntent().getExtras().getParcelable("contact");

        message = (EditText) findViewById(R.id.message);
        Button sendButton = (Button) findViewById(R.id.button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Send OTP: " + contact.getName());
        setSupportActionBar(toolbar);

        final String randomNumber = getRandomSixDigits();

        message.setText("Hi. Your OTP is: " + randomNumber);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP(randomNumber);
            }
        });
    }

    private void sendOTP(final String randomNumber) {

        post(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to send OTP :(", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "OTP Sent to +919971792703!", Toast.LENGTH_LONG).show();
                        saveToDB(randomNumber);
                        SendMessageActivity.this.finish();
                    }
                });
            }
        });
    }

    private void saveToDB(String randomNumber) {
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

    private String getRandomSixDigits() {
        int random = (int) (100000 + Math.random() * 900000);
        return String.valueOf(random);
    }

    Call post(Callback callback) {
        String url = "https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/SMS/Messages";
        String base64EncodedCredentials = "Basic " + Base64.encodeToString((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP);

        RequestBody formBody = new FormBody.Builder()
                .add("From", "+13014175933")
                .add("To", "+919412721584")
                .add("Body", message.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .header("Authorization", base64EncodedCredentials)
                .build();

        Call response = client.newCall(request);
        response.enqueue(callback);
        return response;
    }
}
