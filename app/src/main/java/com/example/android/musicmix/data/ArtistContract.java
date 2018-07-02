package com.example.android.musicmix.data;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public final class ArtistContract {
    public static final String CONTENT_AUTHORITY = "com.example.android.musicmix";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_ARTIST = "artists";


    private ArtistContract() {
    }

    public static final class ArtistEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ARTIST);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ARTIST;


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ARTIST;


        public final static String TABLE_NAME = "artists";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_NAME = "name";

        public final static String COLUMN_COUNTRY = "country";


        public final static String COLUMN_GENRE = "genre";

        public final static String COLUMN_RATING = "rating";


    }

}
