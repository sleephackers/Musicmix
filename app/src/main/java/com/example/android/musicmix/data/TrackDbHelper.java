package com.example.android.musicmix.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TrackDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = TrackDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "tracks.db";

    private static final int DATABASE_VERSION = 1;

    public TrackDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TRACKS_TABLE = "CREATE TABLE " + TrackContract.TrackEntry.TABLE_NAME + " ("
                + TrackContract.TrackEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TrackContract.TrackEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + TrackContract.TrackEntry.COLUMN_ARTIST + " TEXT NOT NULL, "
                + TrackContract.TrackEntry.COLUMN_ALBUM + " TEXT NOT NULL, "
                + TrackContract.TrackEntry.COLUMN_GENRE + " TEXT NOT NULL, "
                + TrackContract.TrackEntry.COLUMN_YEAROFRELEASE + " TEXT NOT NULL, "
                + TrackContract.TrackEntry.COLUMN_LYRICS + " TEXT NOT NULL );";

        db.execSQL(SQL_CREATE_TRACKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
