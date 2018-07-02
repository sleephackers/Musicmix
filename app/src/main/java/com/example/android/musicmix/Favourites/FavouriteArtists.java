package com.example.android.musicmix.Favourites;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.musicmix.R;
import com.example.android.musicmix.data.ArtistContract;

public class FavouriteArtists extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int ARTIST_LOADER = 0;
    FavouritesCursorAdapter2 mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_tracks);

        ListView trackList = (ListView) findViewById(R.id.trackList);

        mCursorAdapter = new FavouritesCursorAdapter2(this, null);
        trackList.setAdapter(mCursorAdapter);
        getLoaderManager().initLoader(ARTIST_LOADER, null, this);

        trackList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Uri currentUri = ContentUris.withAppendedId(ArtistContract.ArtistEntry.CONTENT_URI, id);

                Intent intent = new Intent(FavouriteArtists.this, FavouriteArtistInfo.class);
                intent.setData(currentUri);
                startActivity(intent);

            }
        });


    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        //Define a projection of columns we care abt
        String[] projection = {ArtistContract.ArtistEntry._ID, ArtistContract.ArtistEntry.COLUMN_NAME};

        return new CursorLoader(this, ArtistContract.ArtistEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

    }

}