package com.example.android.musicmix.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ArtistDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ArtistDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "artists.db";

    private static final int DATABASE_VERSION = 1;

    public ArtistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ARTISTS_TABLE = "CREATE TABLE " + ArtistContract.ArtistEntry.TABLE_NAME + " ("
                + ArtistContract.ArtistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ArtistContract.ArtistEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + ArtistContract.ArtistEntry.COLUMN_COUNTRY + " TEXT NOT NULL, "
                + ArtistContract.ArtistEntry.COLUMN_GENRE + " TEXT NOT NULL, "
                + ArtistContract.ArtistEntry.COLUMN_RATING + " TEXT NOT NULL );";

        db.execSQL(SQL_CREATE_ARTISTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
