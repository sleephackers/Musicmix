package com.example.android.musicmix.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public final class TrackContract {
    public static final String CONTENT_AUTHORITY = "com.example.android.musicmix";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TRACK = "tracks";


    private TrackContract() {
    }

    public static final class TrackEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TRACK);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRACK;


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRACK;


        public final static String TABLE_NAME = "tracks";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_TITLE = "title";

        public final static String COLUMN_ARTIST = "artist";

        public final static String COLUMN_ALBUM = "album";

        public final static String COLUMN_GENRE = "genre";

        public final static String COLUMN_YEAROFRELEASE = "yearofrelease";

        public final static String COLUMN_LYRICS = "lyrics";


    }

}
