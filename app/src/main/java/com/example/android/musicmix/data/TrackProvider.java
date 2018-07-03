package com.example.android.musicmix.data;

import android.content.ContentProvider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class TrackProvider extends ContentProvider {

    public static final String LOG_TAG = TrackProvider.class.getSimpleName();
    private static final int TRACKS = 100;
    private static final int TRACKS_ID = 101;
    private static final int ARTISTS = 200;
    private static final int ARTISTS_ID = 201;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI("com.example.android.musicmix", "tracks", TRACKS);
        sUriMatcher.addURI("com.example.android.musicmix", "tracks/#", TRACKS_ID);
        sUriMatcher.addURI("com.example.android.musicmix", "artists", ARTISTS);
        sUriMatcher.addURI("com.example.android.musicmix", "artists/#", ARTISTS_ID);

    }

    private TrackDbHelper mDbHelper;
    private ArtistDbHelper DbHelper;


    @Override
    public boolean onCreate() {
        mDbHelper = new TrackDbHelper(getContext());
        DbHelper = new ArtistDbHelper(getContext());

        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        SQLiteDatabase mdatabase = DbHelper.getReadableDatabase();
        Cursor cursor = null;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case TRACKS:

                cursor = database.query(TrackContract.TrackEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case TRACKS_ID:

                selection = TrackContract.TrackEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(TrackContract.TrackEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case ARTISTS:

                cursor = mdatabase.query(ArtistContract.ArtistEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case ARTISTS_ID:

                selection = ArtistContract.ArtistEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = mdatabase.query(ArtistContract.ArtistEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TRACKS:
                return insertTrack(uri, contentValues);
            case ARTISTS:
                return insertArtist(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertArtist(Uri uri, ContentValues values) {
        String name = values.getAsString(ArtistContract.ArtistEntry.COLUMN_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Artist requires a title");
        }
        String country = values.getAsString(ArtistContract.ArtistEntry.COLUMN_COUNTRY);
        if (country == null) {
            throw new IllegalArgumentException("Artist requires a country");
        }

        String genre = values.getAsString(ArtistContract.ArtistEntry.COLUMN_GENRE);
        if (genre == null) {
            throw new IllegalArgumentException("Artist requires a genre");
        }
        String rating = values.getAsString(ArtistContract.ArtistEntry.COLUMN_RATING);
        if (rating == null) {
            throw new IllegalArgumentException("Artist requires a rating");
        }

        SQLiteDatabase mdatabase = DbHelper.getWritableDatabase();

        long id = mdatabase.insert(ArtistContract.ArtistEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }


    private Uri insertTrack(Uri uri, ContentValues values) {
        String title = values.getAsString(TrackContract.TrackEntry.COLUMN_TITLE);
        if (title == null) {
            throw new IllegalArgumentException("Track requires a title");
        }
        String artist = values.getAsString(TrackContract.TrackEntry.COLUMN_ARTIST);
        if (artist == null) {
            throw new IllegalArgumentException("Track requires a artist");
        }
        String album = values.getAsString(TrackContract.TrackEntry.COLUMN_ALBUM);
        if (album == null) {
            throw new IllegalArgumentException("Track requires a album");
        }
        String genre = values.getAsString(TrackContract.TrackEntry.COLUMN_GENRE);
        if (genre == null) {
            throw new IllegalArgumentException("Track requires a genre");
        }
        String yearofrelease = values.getAsString(TrackContract.TrackEntry.COLUMN_YEAROFRELEASE);
        if (yearofrelease == null) {
            throw new IllegalArgumentException("Track requires a Year of release");
        }
        String lyrics = values.getAsString(TrackContract.TrackEntry.COLUMN_LYRICS);
        if (lyrics == null) {
            throw new IllegalArgumentException("Track requires a lyrics");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(TrackContract.TrackEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TRACKS:
                return updateTrack(uri, contentValues, selection, selectionArgs);
            case TRACKS_ID:

                selection = TrackContract.TrackEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateTrack(uri, contentValues, selection, selectionArgs);
            case ARTISTS:
                return updateArtist(uri, contentValues, selection, selectionArgs);
            case ARTISTS_ID:

                selection = ArtistContract.ArtistEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateArtist(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);

        }
    }

    private int updateArtist(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(ArtistContract.ArtistEntry.COLUMN_NAME)) {
            String name = values.getAsString(ArtistContract.ArtistEntry.COLUMN_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Artist requires a title");
            }

        }
        if (values.containsKey(ArtistContract.ArtistEntry.COLUMN_COUNTRY)) {
            String country = values.getAsString(ArtistContract.ArtistEntry.COLUMN_COUNTRY);
            if (country == null) {
                throw new IllegalArgumentException("Artist requires a country");
            }
        }


        if (values.containsKey(ArtistContract.ArtistEntry.COLUMN_GENRE)) {
            String genre = values.getAsString(ArtistContract.ArtistEntry.COLUMN_GENRE);
            if (genre == null) {
                throw new IllegalArgumentException("Artist requires a genre");
            }
        }


        if (values.containsKey(ArtistContract.ArtistEntry.COLUMN_RATING)) {
            String rating = values.getAsString(ArtistContract.ArtistEntry.COLUMN_RATING);
            if (rating == null) {
                throw new IllegalArgumentException("Artist requires a rating");
            }

        }
        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase mdatabase = DbHelper.getWritableDatabase();
        int rowsUpdated = mdatabase.update(ArtistContract.ArtistEntry.TABLE_NAME, values, selection, selectionArgs);


        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;

    }

    private int updateTrack(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(TrackContract.TrackEntry.COLUMN_TITLE)) {
            String title = values.getAsString(TrackContract.TrackEntry.COLUMN_TITLE);
            if (title == null) {
                throw new IllegalArgumentException("Track requires a title");
            }

        }
        if (values.containsKey(TrackContract.TrackEntry.COLUMN_ARTIST)) {
            String artist = values.getAsString(TrackContract.TrackEntry.COLUMN_ARTIST);
            if (artist == null) {
                throw new IllegalArgumentException("Track requires a artist");
            }

        }


        if (values.containsKey(TrackContract.TrackEntry.COLUMN_ALBUM)) {
            String album = values.getAsString(TrackContract.TrackEntry.COLUMN_ALBUM);
            if (album == null) {
                throw new IllegalArgumentException("Track requires a album");
            }
        }


        if (values.containsKey(TrackContract.TrackEntry.COLUMN_GENRE)) {
            String genre = values.getAsString(TrackContract.TrackEntry.COLUMN_GENRE);
            if (genre == null) {
                throw new IllegalArgumentException("Track requires a genre");
            }
        }
        if (values.containsKey(TrackContract.TrackEntry.COLUMN_YEAROFRELEASE)) {
            String yearofrelease = values.getAsString(TrackContract.TrackEntry.COLUMN_YEAROFRELEASE);
            if (yearofrelease == null) {
                throw new IllegalArgumentException("Track requires a yearofrelease");
            }
        }
        if (values.containsKey(TrackContract.TrackEntry.COLUMN_LYRICS)) {
            String lyrics = values.getAsString(TrackContract.TrackEntry.COLUMN_LYRICS);
            if (lyrics == null) {
                throw new IllegalArgumentException("Track requires a lyrics");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsUpdated = database.update(TrackContract.TrackEntry.TABLE_NAME, values, selection, selectionArgs);


        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;

    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        SQLiteDatabase mdatabase = DbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TRACKS:
                rowsDeleted = database.delete(TrackContract.TrackEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case TRACKS_ID:
                selection = TrackContract.TrackEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(TrackContract.TrackEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ARTISTS:
                rowsDeleted = mdatabase.delete(ArtistContract.ArtistEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ARTISTS_ID:
                selection = ArtistContract.ArtistEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = mdatabase.delete(ArtistContract.ArtistEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);

        }
        return rowsDeleted;

    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TRACKS:
                return TrackContract.TrackEntry.CONTENT_LIST_TYPE;
            case TRACKS_ID:
                return TrackContract.TrackEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
