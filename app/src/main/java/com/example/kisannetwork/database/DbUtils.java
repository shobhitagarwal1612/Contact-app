package com.example.kisannetwork.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by shobhit on 22/7/17.
 */

public class DbUtils {

    public static Cursor getMessagesHistoryCursor(Context context) {

        MessagesHistory.MessagesDbHelper dbHelper = new MessagesHistory.MessagesDbHelper(context);

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
                MessagesHistory.MessageEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return cursor;
    }
}
