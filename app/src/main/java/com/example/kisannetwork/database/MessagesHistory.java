package com.example.kisannetwork.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by shobhit on 22/7/17.
 */

public final class MessagesHistory {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MessageEntry.TABLE_NAME + " ("
                    + MessageEntry._ID + " INTEGER PRIMARY KEY,"
                    + MessageEntry.COLUMN_NAME_CONTACT_NAME + " TEXT,"
                    + MessageEntry.COLUMN_NAME_OTP + " TEXT,"
                    + MessageEntry.COLUMN_NAME_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MessageEntry.TABLE_NAME;


    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private MessagesHistory() {
    }

    /* Inner class that defines the table contents */
    public static class MessageEntry implements BaseColumns {
        public static final String TABLE_NAME = "messages_history";
        public static final String COLUMN_NAME_CONTACT_NAME = "name";
        public static final String COLUMN_NAME_OTP = "otp";
        public static final String COLUMN_NAME_TIME = "timestamp";
    }

    public static class MessagesDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        static final int DATABASE_VERSION = 1;
        static final String DATABASE_NAME = "MessagesHistory.db";

        public MessagesDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}
