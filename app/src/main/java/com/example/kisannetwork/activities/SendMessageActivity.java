package com.example.kisannetwork.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kisannetwork.Constants;
import com.example.kisannetwork.R;
import com.example.kisannetwork.database.MessagesHistory;
import com.example.kisannetwork.model.Contact;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @BindView(R.id.message)
    EditText message;

    OkHttpClient client = new OkHttpClient();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Contact contact;
    private String randomNumber;

    @OnClick(R.id.button)
    public void sendMessage(View view) {

        post(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), R.string.otp_failure, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), R.string.otp_success, Toast.LENGTH_LONG).show();
                        saveToDB(randomNumber);
                        SendMessageActivity.this.finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ButterKnife.bind(this);

        // get the extras from the intent
        contact = getIntent().getExtras().getParcelable("contact");

        toolbar.setTitle(String.format(getString(R.string.otp_title), contact.getName()));
        setSupportActionBar(toolbar);

        randomNumber = getRandomSixDigits();

        message.setText(String.format(getString(R.string.otp_body), randomNumber));
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
            Toast.makeText(getBaseContext(), R.string.message_saved, Toast.LENGTH_SHORT).show();
        }
    }

    private String getRandomSixDigits() {
        int random = (int) (100000 + Math.random() * 900000);
        return String.valueOf(random);
    }

    Call post(Callback callback) {
        String url = "https://api.twilio.com/2010-04-01/Accounts/" + Constants.ACCOUNT_SID + "/SMS/Messages";
        String base64EncodedCredentials = "Basic "
                + Base64.encodeToString((Constants.ACCOUNT_SID + ":" + Constants.AUTH_TOKEN).getBytes(), Base64.NO_WRAP);

        RequestBody formBody = new FormBody.Builder()
                .add("From", "+13014175933")
                .add("To", "+919971792703")
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
